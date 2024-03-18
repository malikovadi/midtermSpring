package com.kg.alatoo.midtermSpring;

import com.kg.alatoo.midtermSpring.entities.User;
import com.kg.alatoo.midtermSpring.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveAndFindUser() {
        // Create a user entity
        User user = new User();
        user.setName("John Doe");
        user.setUsername("johndoe");
        user.setEmail("john@example.com");

        // Save the user entity
        userRepository.save(user);

        // Find the saved user entity
        User foundUser = userRepository.findById(user.getId()).orElse(null);

        // Assert that the found user is not null and has the correct properties
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getName()).isEqualTo("John Doe");
        assertThat(foundUser.getUsername()).isEqualTo("johndoe");
        assertThat(foundUser.getEmail()).isEqualTo("john@example.com");
    }
}

