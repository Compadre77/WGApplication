package com.webec.WGApplication.service;

import com.webec.WGApplication.model.ToDoEntry;
import com.webec.WGApplication.model.entity.ToDo;
import com.webec.WGApplication.model.entity.User;
import com.webec.WGApplication.model.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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

    public List<ToDoEntry> getAllToDos() {
        return repo.findAll().stream()
                .map(t -> createTodoEntry(t))
                .collect(Collectors.toList());
    }

    private ToDoEntry createTodoEntry(ToDo t){
        var entry = new ToDoEntry(
                t.getId(),
                t.getDescription(),
                t.getDays(),
                t.getCurrentAssignee(),
                t.getCurrentDeadline(),
                t.isDone(),
                t.getUserIDs());
        entry.users = new ArrayList<>();
        for (int i = 0; i < t.getUserIDs().size(); i++){
            entry.users.add(userService.getUserById(t.getUserIDs().get(i)));
        }
        entry.currentAssignee = userService.getUserById(t.getCurrentAssignee());
        return entry;
    }

    public ToDo add(
            int id,
            String description,
            int days,
            int currentAssigneeId,
            Date currentDeadline,
            boolean done,
            int[] userIDs
    ) {
        List<Integer> users = new ArrayList<>();
        for (int i = 0; i < userIDs.length; i++) {
            users.add(userIDs[i]);
        }
        var todo = new ToDo();
        todo.setDescription(description);
        todo.setDays(days);
        todo.setCurrentAssignee(currentAssigneeId);
        todo.setCurrentDeadline(currentDeadline);
        todo.setDone(done);
        todo.setUserIDs(users);
        return repo.save(todo);
    }

    public Optional<ToDo> findToDo(int id) { return repo.findById(id); }

    public void delete(ToDo todo) { repo.delete(todo); }
}
