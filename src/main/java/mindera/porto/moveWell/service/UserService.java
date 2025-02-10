package mindera.porto.moveWell.service;

import ch.qos.logback.core.net.server.Client;
import jakarta.validation.Valid;
import mindera.porto.moveWell.dto.UserCreateDto;
import mindera.porto.moveWell.dto.UserDeleteDto;
import mindera.porto.moveWell.dto.UserReadDto;
import mindera.porto.moveWell.dto.UserUpdateDto;
import mindera.porto.moveWell.entity.Role;
import mindera.porto.moveWell.entity.RoleType;
import mindera.porto.moveWell.entity.User;
import mindera.porto.moveWell.mapper.UserMapper;
import mindera.porto.moveWell.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserReadDto getUserById(Long userId) {
        //User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User does not exist"));
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserReadDto userReadDto = UserMapper.fromUserToUserReadDto(user);
            return userReadDto;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist.");
        }
    }

    public UserReadDto addNewUser(UserCreateDto userCreateDto) {

        Optional<User> userOptional = userRepository.findUserByUsername(userCreateDto.getUsername());

        if (userOptional.isEmpty()) {
            User user = UserMapper.fromUserCreateDtoToUser(userCreateDto);

            if (user.getRole() == null) {
                //Em vez da linha abaixo criar RoleRepository e criar método no RoleService para ir buscar o id do role
                user.setRole(new Role(2L, RoleType.CLIENT));
                User userSaved = userRepository.save(user);
                return UserMapper.fromUserToUserReadDto(userSaved);
            } else {
                User userSaved = userRepository.save(user);
                return UserMapper.fromUserToUserReadDto(userSaved);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User is duplicated.");
        }
    }

    public void deleteOwnUser (Long userId, UserDeleteDto userDeleteDto) {

        Optional<User> userOptional = userRepository.findUserByUsernameAndPassword(
                userDeleteDto.getUsername(),
                userDeleteDto.getPassword()
        );

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (user.getId().equals(userId)) {
                userRepository.delete(user);

            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only delete your own data.");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials. Please check your username and password.");
        }
    }

    public void updateOwnUser (Long userId, UserUpdateDto userUpdateDto) {

        Optional<User> userOptional = userRepository.findUserByUsernameAndPassword(
                userUpdateDto.getUsername(),
                userUpdateDto.getPassword()
        );

        if(userOptional.isPresent()) {
            User user = userOptional.get();

            if (user.getId().equals(userId)) {
                user.setUsername(userUpdateDto.getNewUsername());
                user.setPassword(userUpdateDto.getNewPassword());
                userRepository.save(user);
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only edit your own data.");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials. Please check your username and password.");
        }
    }
}
