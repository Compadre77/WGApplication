package com.webec.WGApplication.service;

import com.webec.WGApplication.model.ToDoEntry;
import com.webec.WGApplication.model.entity.ToDo;
import com.webec.WGApplication.model.entity.User;
import com.webec.WGApplication.model.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class ToDoService {
    private final ToDoRepository repo;
    private final UserService userService;

    public ToDoService(ToDoRepository repo, UserService userService) {
        this.repo = repo;
        this.userService = userService;
    }

    public List<ToDo> findAll(){
        return repo.findAll();
    }

    public List<ToDoEntry> getToDosByAssginee(int id) {
        return repo.findByCurrentAssignee(id).stream().map(t -> createTodoEntry(t)).collect(toList());
    }

    public List<ToDoEntry> getAllToDos() {
        return repo.findAll().stream()
                .map(t -> createTodoEntry(t))
                .collect(Collectors.toList());
    }

    public List<ToDoEntry> getTodosByCurrentAssignee(int id){
        return repo.findByCurrentAssignee(id).stream()
                .map(t -> createTodoEntry(t))
                .collect(Collectors.toUnmodifiableList());
    }

    private ToDoEntry createTodoEntry(ToDo t){
        var entry = new ToDoEntry(
                t.getId(),
                t.getDescription(),
                t.getDays(),
                t.getCurrentAssignee(),
                t.getCurrentDeadline(),
                t.getUserIDs());
        entry.users = new ArrayList<>();
        for (int i = 0; i < t.getUserIDs().size(); i++){
            entry.users.add(userService.getUserById(t.getUserIDs().get(i)));
        }
        entry.currentAssignee = userService.getUserById(t.getCurrentAssignee());
        return entry;
    }

    public ToDo add(
            String description,
            int days,
            Date currentDeadline,
            int curreentAssignee,
            int[] userIDs
    ) {
        List<Integer> users = new ArrayList<>();
        users.add(curreentAssignee);
        for (int i = 0; i < userIDs.length; i++) {
            users.add(userIDs[i]);
        }
        var todo = new ToDo();
        todo.setDescription(description);
        todo.setDays(days);
        todo.setCurrentAssignee(curreentAssignee);
        todo.setCurrentDeadline(currentDeadline);
        todo.setUserIDs(users);
        return repo.save(todo);
    }

    public Optional<ToDo> findToDo(int id) { return repo.findById(id); }

    public List<ToDo> findByCurrenAssignee(int id) { return repo.findByCurrentAssignee(id); }

    public void delete(ToDo todo) { repo.delete(todo); }

    public void updateTodo(ToDo todo) {
        // Save userIds in correct order
        List<Integer> userIds = todo.getUserIDs();
        for (int i = 0; i < userIds.size(); i++) {
            int id = userIds.get(i);
            if (id == todo.getCurrentAssignee() && userIds.size() - 1 > i) {
                todo.setCurrentAssignee(userIds.get(i + 1));
                break;
            }
            else if (id == todo.getCurrentAssignee()) {
                todo.setCurrentAssignee(userIds.get(0));
                break;
            }
        }

        // Set new Deadline
        Calendar c = Calendar.getInstance();
        c.setTime(todo.getCurrentDeadline());
        c.add(Calendar.DATE, todo.getDays());

        todo.setCurrentDeadline(c.getTime());
        todo.setUserIDs(userIds);
        repo.save(todo);
    }

}
