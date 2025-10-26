package com.example.Learning_App.repository;

import com.example.Learning_App.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EntityScan(basePackages = "com.example.Learning_App.entity")
@EnableJpaRepositories(basePackages = "com.example.Learning_App.repository")
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create",
        "spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=MySQL",
        "spring.datasource.driver-class-name=org.h2.Driver"
})
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Save and find user by email and username")
    void saveAndFindUser() {
        // given
        User user = new User();
        user.setEmail("user@gmail.com");
        user.setUsername("user");
        user.setPassword("user123");
        userRepository.save(user);

        // when
        Optional<User> byEmail = userRepository.findByEmail("user@gmail.com");
        Optional<User> byUsername = userRepository.findByUsername("user");

        // then
        assertThat(byEmail).isPresent();
        assertThat(byUsername).isPresent();
        assertThat(byEmail.get().getUsername()).isEqualTo("user");
        assertThat(byUsername.get().getEmail()).isEqualTo("user@gmail.com");
    }
}
