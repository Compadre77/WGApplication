package com.webec.WGApplication.model.repository;

import com.webec.WGApplication.model.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ToDoRepository extends JpaRepository<ToDo, Integer>{
    List<ToDo> findByCurrentAssignee(int currentAssignee);
}
