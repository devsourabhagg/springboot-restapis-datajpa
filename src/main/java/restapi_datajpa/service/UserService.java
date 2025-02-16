package restapi_datajpa.service;

import restapi_datajpa.dto.UserDto;
import restapi_datajpa.entity.User;
import restapi_datajpa.exception.EmailAlreadyExistsException;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto User);

    void deleteUser(Long userId);
}
