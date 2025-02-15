package com.example.todoapp.controller;

import com.example.todoapp.domain.TodoClass;
import com.example.todoapp.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TodoController {
    private final TodoRepository todoRepository;

    @GetMapping("/")
    public String index(Model model) {
        // find all in db
        List<TodoClass> allTodoClass = todoRepository.findAll();
        model.addAttribute("allTodoClass", allTodoClass);
        return "mainPage";
    }

    @PostMapping("/addTodo")
    public String addTodo(@RequestParam("task") String task) {
        // Save to DB
        TodoClass newTodoClass = new TodoClass();
        newTodoClass.setTask(task);
        todoRepository.save(newTodoClass);
        // ane redirect to "/"
        return "redirect:/";
    }

    @PostMapping("/deleteTodo")
    public String deleteTodo(@RequestParam("id") Long id) {
        todoRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/editTodo")
    public String editTodo(@RequestParam("id") Long id, @RequestParam("task") String updatedTask) {
        // Retrieve the task by id
        TodoClass todo = todoRepository.findById(id).orElse(null);
        if (todo != null) {
            // Update task content
            todo.setTask(updatedTask);
            // Save the updated task to the database
            todoRepository.save(todo);
        }
        return "redirect:/";
    }

}
