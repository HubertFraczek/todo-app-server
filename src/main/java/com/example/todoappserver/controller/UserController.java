package com.example.todoappserver.controller;

import com.example.todoappserver.model.User;
import com.example.todoappserver.repository.UserRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todo_list")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public Optional<User> find(@PathVariable Integer id) {
        return userRepository.findById(id);
    }

    @GetMapping("/user/{username}/{password}")
    public User findByUsernameAndPassword(@PathVariable String username, @PathVariable String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @PostMapping("/user")
    public JSONObject insert(@RequestBody User user) {
        int resp =  userRepository.insert(user.getUsername(), user.getPassword());
        System.out.println("RESPONSE" + resp);
        JSONObject object = new JSONObject();
        try {
            object.put("resp", Integer.toString(resp));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    @PutMapping("/user/{id}")
    public User update(@RequestBody User newUser, @PathVariable Integer id) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(user.getUsername());
            user.setPassword(user.getPassword());
            return userRepository.save(user);
        }).orElseGet(() -> {
            newUser.setId(id);
            return userRepository.save(newUser);
        });
    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }
}
