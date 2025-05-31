package com.example.reviewsmanager.controller;

import com.example.reviewsmanager.dto.UserDTO;
import com.example.reviewsmanager.model.User;
import com.example.reviewsmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class UserPageController
{
    @Autowired
    private final UserRepository userRepository;
    public UserPageController(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @GetMapping({"", "/"})
    public String getUsers(Model model)
    {
        var users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users/index";
    }

    @GetMapping("/create")
    public String createUser(Model model)
    {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        return "users/create";
    }

    @PostMapping("/create")
    public String createUser(UserDTO userDTO, BindingResult bindingResult)
    {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setProfileIcon(userDTO.getProfileIcon());
        user.setRole(userDTO.getRole());
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String editUser(Model model, @RequestParam Long id)
    {
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
        {
            return "redirect:/users";
        }
        UserDTO userDTO = new UserDTO();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setProfileIcon(userDTO.getProfileIcon());
        user.setRole(userDTO.getRole());

        model.addAttribute("userDTO", userDTO);
        model.addAttribute("user", user);

        return "users/edit";
    }

    @PostMapping("/edit")
    public String editUser(Model model, @RequestParam Long id,
                            UserDTO userDTO, BindingResult bindingResult)
    {
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
        {
            return "redirect:/users";
        }
        model.addAttribute("user", user);

        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setProfileIcon(userDTO.getProfileIcon());
        user.setRole(userDTO.getRole());
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam Long id)
    {
        User user = userRepository.findById(id).orElse(null);
        if (user != null)
        {
            userRepository.delete(user);
        }
        return "redirect:/users";
    }
}
