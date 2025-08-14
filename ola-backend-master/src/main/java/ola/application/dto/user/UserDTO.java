package ola.application.dto.user;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class UserDTO {
    private int id;
    private String name;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    private String address;
    private String city;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime firstLoginAt;
    private LocalDateTime lastLoginAt;
    private Boolean isDeleted;
}
