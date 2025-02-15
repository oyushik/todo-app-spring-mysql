package com.example.todoapp.repository;

import com.example.todoapp.domain.TodoClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoClass, Long> {
}
