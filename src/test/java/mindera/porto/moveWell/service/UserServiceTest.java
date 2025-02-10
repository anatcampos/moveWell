package mindera.porto.moveWell.service;

import mindera.porto.moveWell.dto.UserCreateDto;
import mindera.porto.moveWell.dto.UserDeleteDto;
import mindera.porto.moveWell.dto.UserReadDto;
import mindera.porto.moveWell.dto.UserUpdateDto;
import mindera.porto.moveWell.entity.User;
import mindera.porto.moveWell.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User testUser;
    private UserCreateDto testUserCreateDto;
    private UserUpdateDto testUserUpdateDto;
    private UserDeleteDto testUserDeleteDto;

    @BeforeEach
    void setUp() {

        userRepository.deleteAll();
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("testuser");
        testUser.setPassword("password123");

        testUserCreateDto = new UserCreateDto();
        testUserCreateDto.setUsername("testuser");
        testUserCreateDto.setPassword("password123");

        testUserUpdateDto = new UserUpdateDto();
        testUserUpdateDto.setUsername("updateduser");
        testUserUpdateDto.setPassword("updatedpassword123");

        testUserDeleteDto = new UserDeleteDto();
        testUserDeleteDto.setUsername("testuser");
        testUserDeleteDto.setPassword("password123");
    }

    @Test
    void testGetUserById_Exists() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));

        UserReadDto result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
        verify(userRepository).findById(1L);
    }

    @Test
    void testGetUserById_NotExists() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            userService.getUserById(1L);
        });
    }

    @Test
    void testAddNewUser_Success() {
        when(userRepository.findUserByUsername(testUserCreateDto.getUsername())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        UserReadDto result = userService.addNewUser(testUserCreateDto);

        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testAddNewUser_Conflict() {
        when(userRepository.findUserByUsername(testUserCreateDto.getUsername())).thenReturn(Optional.of(testUser));

        assertThrows(ResponseStatusException.class, () -> {
            userService.addNewUser(testUserCreateDto);
        });
    }

    @Test
    void testDeleteOwnUser_Success() {
        when(userRepository.findUserByUsernameAndPassword(testUserDeleteDto.getUsername(), testUserDeleteDto.getPassword()))
                .thenReturn(Optional.of(testUser));

        userService.deleteOwnUser(1L, testUserDeleteDto);

        verify(userRepository).delete(testUser);
    }

    @Test
    void testDeleteOwnUser_InvalidCredentials() {
        when(userRepository.findUserByUsernameAndPassword(testUserDeleteDto.getUsername(), testUserDeleteDto.getPassword()))
                .thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            userService.deleteOwnUser(1L, testUserDeleteDto);
        });
    }

    @Test
    void testDeleteOwnUser_Forbidden() {
        User differentUser = new User();
        differentUser.setId(2L);
        when(userRepository.findUserByUsernameAndPassword(testUserDeleteDto.getUsername(), testUserDeleteDto.getPassword()))
                .thenReturn(Optional.of(differentUser));

        assertThrows(ResponseStatusException.class, () -> {
            userService.deleteOwnUser(1L, testUserDeleteDto);
        });
    }

//    @Test
//    void testUpdateOwnUser_Success() {
//        // Configurar o mock para o findByUsernameAndPassword
//        when(userRepository.findUserByUsernameAndPassword(testUserUpdateDto.getUsername(), testUserUpdateDto.getPassword()))
//                .thenReturn(Optional.of(testUser)); // Retorna o usuário com os dados atuais
//
//        // Chama o método para atualizar o usuário
//        userService.updateOwnUser(1L, testUserUpdateDto);
//
//        // Verifica se o username foi atualizado corretamente
//        assertNotNull(testUser.getUsername(), "O nome de usuário não pode ser nulo.");
//        assertEquals("updateduser", testUser.getUsername(), "O nome de usuário não foi atualizado corretamente.");
//
//        // Verifica se o save foi chamado corretamente
//        verify(userRepository).save(testUser);
//    }


    @Test
    void testUpdateOwnUser_InvalidCredentials() {
        when(userRepository.findUserByUsernameAndPassword(testUserUpdateDto.getUsername(), testUserUpdateDto.getPassword()))
                .thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            userService.updateOwnUser(1L, testUserUpdateDto);
        });
    }

    @Test
    void testUpdateOwnUser_Forbidden() {
        User differentUser = new User();
        differentUser.setId(2L);
        when(userRepository.findUserByUsernameAndPassword(testUserUpdateDto.getUsername(), testUserUpdateDto.getPassword()))
                .thenReturn(Optional.of(differentUser));

        assertThrows(ResponseStatusException.class, () -> {
            userService.updateOwnUser(1L, testUserUpdateDto);
        });
    }


}
