package restapi_datajpa.service;

import restapi_datajpa.dto.UserDto;
import restapi_datajpa.entity.User;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);

    User getUserById(Long userId);

    List<User> getAllUsers();

    User updateUser(User User);

    void deleteUser(Long userId);
}
