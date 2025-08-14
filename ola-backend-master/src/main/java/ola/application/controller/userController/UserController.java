package ola.application.controller.userController;

import ola.application.dto.user.UserDTO;
import ola.application.services.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/v1/all-users")
    public ResponseEntity<List<UserDTO>> fetchUser() {
        System.out.println(userService);
        return ResponseEntity.ok(userService.getUsers());
    }
    @GetMapping("/u/get/{id}")
    public ResponseEntity<UserDTO> fetchUser(@PathVariable int id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
        @GetMapping("/u/get-by/{username}")
    public ResponseEntity<UserDTO> fetchUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @GetMapping("/d/get-by/{username}")
    public ResponseEntity<UserDTO> fetchdriverByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @DeleteMapping("/u/delete/{username}")
    public ResponseEntity<UserDTO> deleteUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.deleteUserByUsername(username));
    }
    @DeleteMapping("/u/delete-by/{id}")
    public ResponseEntity<UserDTO> deleteUserById(@PathVariable int id) {
        return ResponseEntity.ok(userService.deleteUserById(id));
    }

    @PutMapping("/u/update-user/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody UserDTO updatedUserDTO) {
        return ResponseEntity.ok(userService.updateUser(id,updatedUserDTO));
    }
}