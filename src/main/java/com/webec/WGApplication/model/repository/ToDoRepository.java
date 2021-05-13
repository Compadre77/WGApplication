package com.webec.WGApplication.model.repository;

import com.webec.WGApplication.model.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ToDoRepository extends JpaRepository<ToDo, Integer>{
    Optional<ToDo> findByCurrentAssignee(int currentAssignee);
}
