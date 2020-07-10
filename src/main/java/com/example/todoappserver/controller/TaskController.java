package com.example.todoappserver.controller;

import com.example.todoappserver.model.Task;
import com.example.todoappserver.model.User;
import com.example.todoappserver.repository.TaskRepository;
import com.example.todoappserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todo_list")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/tasks/{id}")
    public List<Task> findAllById(@PathVariable Integer id) {
        return taskRepository.findAllById(id);
    }

    @PutMapping("/task/{id}")
    public Task updateTask(@RequestBody Task newTask, @PathVariable Integer id) {
        return taskRepository.findById(id).map(task -> {
            task.setTask(newTask.getTask());
            task.setActive(newTask.isActive());
            return taskRepository.save(task);
        }).orElseGet(() -> {
            newTask.setId(id);
            return taskRepository.save(newTask);
        });
    }

    @PostMapping("/task/{id}")
    public Task createTODOItem(@RequestBody Task task, @PathVariable Integer id) throws ParseException {
        Optional<User> user = userRepository.findById(id);
        task.setUser(user.get());
        task.setActive(true);
        return taskRepository.save(task);
    }
}
