package restapi_datajpa.service;

import restapi_datajpa.entity.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User getUserById(Long userId);

    List<User> getAllUsers();

    User updateUser(User User);

    void deleteUser(Long userId);
}
