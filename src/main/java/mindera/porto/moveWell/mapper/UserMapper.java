package mindera.porto.moveWell.mapper;

import mindera.porto.moveWell.dto.UserCreateDto;
import mindera.porto.moveWell.dto.UserReadDto;
import mindera.porto.moveWell.entity.User;


public class UserMapper {

    public static UserReadDto fromUserToUserReadDto (User user) {
        UserReadDto userReadDto = new UserReadDto();
        userReadDto.setId(user.getId());
        userReadDto.setName(user.getName());
        userReadDto.setUsername(user.getUsername());
        userReadDto.setAge(user.getAge());
        return userReadDto;
    }

    public static User fromUserCreateToUser(UserCreateDto userCreateDto) {

        User user = new User();
        user.setName(userCreateDto.getName());
        user.setUsername(userCreateDto.getUsername());
        user.setAge(userCreateDto.getAge());
        return user;
    }
}
