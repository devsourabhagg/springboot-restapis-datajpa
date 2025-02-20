package restapi_datajpa.mapper;

import restapi_datajpa.dto.UserDto;
import restapi_datajpa.entity.User;

public class UserMapper {

    //Convert User Jpa Entity into User Dto
    public static UserDto mapToUserDto(User user){

        UserDto userDto = new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );

        return userDto;
    }

    // Convert UserDto to User Jpa Entity

    public static User mapToUser(UserDto userDto){
        User user = new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );

        return user;
    }
}
