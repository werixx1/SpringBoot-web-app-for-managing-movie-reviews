package com.example.reviewsmanager.dto;

import com.example.reviewsmanager.model.ProfileIcon;
import com.example.reviewsmanager.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO
{
    private String username;
    private String email;
    private ProfileIcon profileIcon;
    private Role role;
}
