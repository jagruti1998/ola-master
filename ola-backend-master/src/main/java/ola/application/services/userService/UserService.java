package ola.application.services.userService;

import ola.application.dto.user.UserDTO;
import ola.application.entity.OlaUser;
import ola.application.exception.BadRequestException;
import ola.application.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Get all users
    public List<UserDTO> getUsers(){
        List<OlaUser> users = userRepo.findAllByIsDeletedFalse();
        return users.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Get User by Id --------------------------
    public UserDTO getUserById(int id) throws BadRequestException {
        OlaUser user = userRepo.findByIdAndIsDeletedFalse(id).orElseThrow(() ->new BadRequestException(401,"User not found with id: "+id));

        return this.mapToDto(user);
    }

    //     Get User by Username ----------------------
    public UserDTO getUserByUsername(String username) throws BadRequestException{
        OlaUser user = userRepo.findByUsernameAndIsDeletedFalse(username).orElseThrow(()->new BadRequestException(401,"User with username"+username+"not found"));
        return this.mapToDto(user);
    }

    //     Delete a User by Username
    public UserDTO deleteUserByUsername(String username) throws BadRequestException{
        OlaUser user = userRepo.findByUsernameAndIsDeletedFalse(username).orElseThrow(()->new BadRequestException(401,"User with username "+username+"not found"));
        user.setDeleted();
        userRepo.save(user);
        return this.mapToDto(user);
    }

    // Delete a User by Id
    public UserDTO deleteUserById(int id) throws BadRequestException{
        OlaUser user = userRepo.findByIdAndIsDeletedFalse(id).orElseThrow(()->new BadRequestException(401,"User not found with id: "+id));
        user.setDeleted();
        userRepo.save(user);
        return this.mapToDto(user);
    }

    //     Update existing user by id
    public UserDTO updateUser(int id, UserDTO updatedUserDTO) throws BadRequestException {
        OlaUser user = userRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new BadRequestException(401, "User not found with id: " + id));

        // Update user fields
        user.setName(updatedUserDTO.getName());
        user.setEmail(updatedUserDTO.getEmail());
        user.setAddress(updatedUserDTO.getAddress());
        user.setCity(updatedUserDTO.getCity());
        user.setUsername(updatedUserDTO.getUsername());
        user.setPassword(passwordEncoder().encode(updatedUserDTO.getPassword()));
        user.setUpdatedAt(LocalDateTime.now());

        OlaUser updatedUser = userRepo.save(user);
        return this.mapToDto(updatedUser);
    }


    public OlaUser mapToEntity(UserDTO userDTO){
        OlaUser user = this.modelMapper.map(userDTO, OlaUser.class);
        return user;
    }
    public UserDTO mapToDto(OlaUser user){
        UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
        return userDTO;
    }
}
