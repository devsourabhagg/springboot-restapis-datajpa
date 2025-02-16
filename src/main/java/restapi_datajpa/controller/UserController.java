package restapi_datajpa.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import restapi_datajpa.dto.UserDto;
import restapi_datajpa.entity.User;
import restapi_datajpa.exception.EmailAlreadyExistsException;
import restapi_datajpa.exception.ErrorDetails;
import restapi_datajpa.exception.ResourceNotFoundException;
import restapi_datajpa.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    //build create user Rest Api
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody  UserDto user) {

        UserDto savedUserDto = userService.createUser(user);

        return new ResponseEntity<>(savedUserDto, HttpStatus.CREATED);
    }

    //build get user by id
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){

        UserDto userDto = userService.getUserById(userId);

        return new ResponseEntity<>(userDto,HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){

        List<UserDto> usersDto = userService.getAllUsers();

        return new ResponseEntity<>(usersDto,HttpStatus.OK);

    }

    // build update user rest api
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable("id") Long userId){

        userDto.setId(userId);

        UserDto updatedUserDto = userService.updateUser(userDto);

        return new ResponseEntity<>(updatedUserDto,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){

        userService.deleteUser(userId);

        return  new ResponseEntity<>("User deleted successfully",HttpStatus.OK);
    }

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorDetails> handleResourceNotFoundException (ResourceNotFoundException exception,
//                                         WebRequest webRequest){
//
//        ErrorDetails errorDetails = new ErrorDetails(
//                LocalDateTime.now(),
//                exception.getMessage(),
//                webRequest.getDescription(false),
//                "USER_NOT_FOUND"
//        );
//
//        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
//
//    }

}
