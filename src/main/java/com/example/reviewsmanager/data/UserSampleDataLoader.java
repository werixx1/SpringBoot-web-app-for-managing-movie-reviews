package com.example.reviewsmanager.data;

import com.example.reviewsmanager.model.ProfileIcon;
import com.example.reviewsmanager.model.Role;
import com.example.reviewsmanager.model.User;
import com.example.reviewsmanager.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class UserSampleDataLoader implements CommandLineRunner
{
    private final UserRepository userRepository;

    public UserSampleDataLoader(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception
    {
        if (userRepository.count() == 0)
        {
            User user1 = new User("maklowicz", "maklowicz.wielki@gmail.com", "maslo123",
                    ProfileIcon.CHILL, Role.USER);
            User user2 = new User("podsiadlo", "podsiadlo.usiadlo@gmail.com", "haslomaslo",
                    ProfileIcon.SILLY, Role.USER);
            userRepository.save(user1);
            userRepository.save(user2);
            System.out.println("Sample users added to database");
        }
        else
        {
            System.out.println("Users already present in database, skipping sample data load.");
        }
    }
}
