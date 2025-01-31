package mindera.porto.moveWell.service;

import mindera.porto.moveWell.dto.UserCreateDto;
import mindera.porto.moveWell.dto.UserDeleteDto;
import mindera.porto.moveWell.dto.UserReadDto;
import mindera.porto.moveWell.entity.User;
import mindera.porto.moveWell.mapper.UserMapper;
import mindera.porto.moveWell.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserReadDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User does not exist"));
        UserReadDto userReadDto = UserMapper.fromUserToUserReadDto(user);
        //return Optional.of(userReadDto); //criar um optional do userReadDto
        return userReadDto;
    }

    public UserReadDto addNewUser(UserCreateDto userCreateDto) {
        try {
            User user = UserMapper.fromUserCreateDtoToUser(userCreateDto);
            User userSaved = userRepository.save(user);
            return UserMapper.fromUserToUserReadDto(userSaved);
        } catch (Exception e) {
            throw new IllegalStateException("User is duplicated.");
        }
    }

    public void deleteOwnUser(Long userId, UserDeleteDto userDeleteDto) {

        Optional<User> idOfUser = userRepository.findUserByUsernameAndPassword(userDeleteDto.getUsername(), userDeleteDto.getPassword());

        if(idOfUser.isPresent()){

            if(idOfUser.get().getId().equals(userId)){

                userRepository.deleteById(userId);
            }
        }
    }
}
