package com.example.reviewsmanager.controller;

import com.example.reviewsmanager.model.Movie;
import com.example.reviewsmanager.model.User;
import com.example.reviewsmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController
{
    private final UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user)
    {
        return ResponseEntity.ok(userService.addUser(user));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id)
    {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent())
        {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username)
    {
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isPresent())
        {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> deleteUserById(@PathVariable Long id)
    {
        boolean deleted = userService.deleteUserById(id);
        if (deleted)
        {
            return ResponseEntity.ok().build();
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable Long id, @RequestBody User newUserData)
    {
        User updateUser = userService.updateUser(id, newUserData);

        if (updateUser != null)
        {
            return ResponseEntity.ok(updateUser);
        }

        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user-with-most-reviews")
    public User getUserWithMostReviews()
    {
        return userService.getUserWithMostReviews();
    }

    @GetMapping("/inactive-users")
    public ResponseEntity<List<User>> getInactiveUsers()
    {
        return ResponseEntity.ok(userService.getInactiveUsers());
    }
}
