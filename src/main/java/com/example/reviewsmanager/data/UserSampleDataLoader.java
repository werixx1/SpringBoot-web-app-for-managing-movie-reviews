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
            User user3 = new User("moviehater1", "ihatemovies@gmail.com", "nottelling1",
                    ProfileIcon.FROWN, Role.USER);
            User user4 = new User("movielover2", "ilovemovies@gmail.com","password_",
                    ProfileIcon.SMILE, Role.USER);
            User user5 = new User("im_not_active", "goodmorning@gmail.com", "123321",
                    ProfileIcon.WINK, Role.USER);
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
            userRepository.save(user4);
            userRepository.save(user5);
            System.out.println("Sample users added to database");
        }
        else
        {
            System.out.println("Users already present in database, skipping sample data load.");
        }
    }
}
