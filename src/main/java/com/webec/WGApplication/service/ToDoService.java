package com.webec.WGApplication.service;

import com.webec.WGApplication.model.ToDoEntry;
import com.webec.WGApplication.model.entity.ToDo;
import com.webec.WGApplication.model.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ToDoService {
    private final ToDoRepository repo;

    public ToDoService(ToDoRepository repo) { this.repo = repo; }

    public List<ToDoEntry> getAllTodos() {
        return repo.findAll().stream().map(t -> createToDoEntry(t))
                .collect(toList());
    }
    public List<ToDoEntry> getToDosByAssginee(int id) {
        return repo.findByCurrentAssignee(id).stream().map(t -> createToDoEntry(t))
                .collect(toList());
    }

    private ToDoEntry createToDoEntry(ToDo t){
        return new ToDoEntry(
                t.getId(),
                t.getDescription(),
                t.getDays(),
                t.getCurrentAssignee(),
                t.getCurrentDeadline(),
                t.getUserIDs());
    }

    public ToDo add(String description, int days, int currentAssignee, Date currentDeadline, int userID) {
        List<Integer> userIDs = new ArrayList<>();
        userIDs.add(userID);
        var toDo = new ToDo();
        return repo.save(toDo); // 'save' might return new object
    }

}
