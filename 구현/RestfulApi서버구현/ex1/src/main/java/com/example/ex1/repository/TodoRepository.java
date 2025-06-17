package com.example.ex1.repository;

import com.example.ex1.entity.ToDoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<ToDoEntity, Long> {

}
