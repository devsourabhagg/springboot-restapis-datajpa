package restapi_datajpa.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import restapi_datajpa.dto.UserDto;
import restapi_datajpa.entity.User;
import restapi_datajpa.mapper.UserMapper;
import restapi_datajpa.repository.UserRepository;
import restapi_datajpa.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {

        //convert UserDto into User JPA Entity
        User user = UserMapper.mapToUser(userDto);

        User savedUser = userRepository.save(user);

        //convert User JPA Entity to UserDto
        UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);

        return  savedUserDto;

    }

    @Override
    public UserDto getUserById(Long userId) {
        Optional<User> optionalUser =  userRepository.findById(userId);
        User user = optionalUser.get();
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        //covert user jpa entity to user dto
//        List<UserDto> userDtos = new ArrayList<>();
//        for(User user : users){
//            userDtos.add(UserMapper.mapToUserDto(user));
//        }
//
//        return userDtos;

        //using stream
        return users.stream().map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());


    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User existingUser = userRepository.findById(userDto.getId()).get();
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());
        User updatedUser = userRepository.save(existingUser);
        return UserMapper.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
