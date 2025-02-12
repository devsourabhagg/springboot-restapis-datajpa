package restapi_datajpa.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restapi_datajpa.entity.User;
import restapi_datajpa.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    //build create user Rest Api
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody  User user){

        User savedUser = userService.createUser(user);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    //build get user by id
    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long userId){

        User user = userService.getUserById(userId);

        return new ResponseEntity<>(user,HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){

        List<User> users = userService.getAllUsers();

        return new ResponseEntity<>(users,HttpStatus.OK);

    }

    // build update user rest api
    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable("id") Long userId){

        user.setId(userId);

        User updatedUser = userService.updateUser(user);

        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){

        userService.deleteUser(userId);

        return  new ResponseEntity<>("User deleted successfully",HttpStatus.OK);
    }
}
