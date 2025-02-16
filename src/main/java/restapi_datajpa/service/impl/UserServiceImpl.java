package restapi_datajpa.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import restapi_datajpa.dto.UserDto;
import restapi_datajpa.entity.User;
import restapi_datajpa.exception.EmailAlreadyExistsException;
import restapi_datajpa.exception.ResourceNotFoundException;
import restapi_datajpa.repository.UserRepository;
import restapi_datajpa.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        //convert UserDto into User JPA Entity
//        User user = UserMapper.mapToUser(userDto);

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if (optionalUser.isPresent()){

            throw new EmailAlreadyExistsException("Email Already Exits for User");
        }

        User user = modelMapper.map(userDto,User.class);


        User savedUser = userRepository.save(user);

        //convert User JPA Entity to UserDto
        //UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);

        UserDto savedUserDto = modelMapper.map(savedUser,UserDto.class);


        return  savedUserDto;

    }

    @Override
    public UserDto getUserById(Long userId) {
//        Optional<User> optionalUser =  userRepository.findById(userId);
//        User user = optionalUser.get();
        User user = userRepository.findById(userId).orElseThrow(

                () -> new ResourceNotFoundException("User","id",userId)

        );

        //return UserMapper.mapToUserDto(user);
        return modelMapper.map(user,UserDto.class);
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
//        return users.stream().map(UserMapper::mapToUserDto)
 //               .collect(Collectors.toList());

        return users.stream().map((user) -> modelMapper.map(user,UserDto.class))
                .collect(Collectors.toList());


    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        //User existingUser = userRepository.findById(userDto.getId()).get();

        User existingUser = userRepository.findById(userDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User","id",userDto.getId())
        );

        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());
        User updatedUser = userRepository.save(existingUser);
//        return UserMapper.mapToUserDto(updatedUser);
        return modelMapper.map(updatedUser,UserDto.class);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User","id",userId)
        );
        userRepository.deleteById(userId);
    }
}
