package com.example.reviewsmanager.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "app_user")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    @JsonIgnore // because password is sensitive information
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "profile_icon")
    private ProfileIcon profileIcon;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviewsPosted = new ArrayList<>();

    // todo: something like private Integer numberOfReviews = reviewsPosted.size();
    // maybe not in constructor?

    public User(String username, String email, String password, ProfileIcon profileIcon, Role role)
    {
        this.username = username;
        this.email = email;
        this.password = password;
        this.profileIcon = profileIcon;
        this.role = role;
    }
}
