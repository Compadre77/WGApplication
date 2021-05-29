package com.webec.WGApplication.service;

import com.webec.WGApplication.model.ToDoEntry;
import com.webec.WGApplication.model.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ToDoService {
    private final ToDoRepository repo;

    public ToDoService(ToDoRepository repo) { this.repo = repo; }

    public List<ToDoEntry> getAllTodos() {
        return repo.findAll().stream().map(t -> new ToDoEntry(
                    t.getId(),
                    t.getDescription(),
                    t.getDays(),
                    t.getCurrentAssignee(),
                    t.getCurrentDeadline(),
                    t.getUserIDs()))
                .collect(toList());
    }
}
