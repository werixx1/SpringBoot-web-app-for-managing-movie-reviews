package com.example.reviewsmanager.service;

import com.example.reviewsmanager.model.Movie;
import com.example.reviewsmanager.model.User;
import com.example.reviewsmanager.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public User addUser(User user)
    {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id)
    {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    public boolean deleteUserById(Long id)
    {
        if (userRepository.existsById(id))
        {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public User updateUser (Long id, User newUserData)
    {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent())
        {
            User user = optionalUser.get();
            // updating everything but email and role (it cant be changed once
            // created)
            user.setEmail(newUserData.getEmail());
            user.setPassword(newUserData.getPassword());
            user.setProfileIcon(newUserData.getProfileIcon());
            return userRepository.save(user);
        }
        throw new EntityNotFoundException("User with id " + id + " not found");
    }

    public User getUserWithMostReviews()
    {
        return userRepository.findUserWithMostReviews();
    }

    public List<User> getInactiveUsers()
    {
        return userRepository.findInactiveUsers();
    }
}
