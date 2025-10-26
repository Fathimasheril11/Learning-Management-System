package com.example.Learning_App.service;

import com.example.Learning_App.entity.User;
import com.example.Learning_App.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setup() {
        userService = new UserService();

        try {
            java.lang.reflect.Field field = UserService.class.getDeclaredField("userRepository");
            field.setAccessible(true);
            field.set(userService, userRepository);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void findByEmail_whenExists_returnsUser() {
        User u = new User("bob", "bob@example.com", "pwd");
        when(userRepository.findByEmail("bob@example.com")).thenReturn(Optional.of(u));

        User result = userService.findByEmail("bob@example.com");

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("bob");
        verify(userRepository).findByEmail("bob@example.com");
    }

    @Test
    void findByUsername_whenNotFound_returnsNull() {
        when(userRepository.findByUsername("nobody")).thenReturn(Optional.empty());

        User result = userService.findByUsername("nobody");

        assertThat(result).isNull();
        verify(userRepository).findByUsername("nobody");
    }

    @Test
    void save_invokesRepositorySave() {
        User toSave = new User("carol", "carol@example.com", "secret");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User arg = invocation.getArgument(0);
            arg.setId(1L);
            return arg;
        });

        User saved = userService.save(toSave);

        assertThat(saved.getId()).isEqualTo(1L);
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());
        assertThat(captor.getValue().getEmail()).isEqualTo("carol@example.com");
    }
}
