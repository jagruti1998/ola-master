package ola.application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "ola_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OlaUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "first_login_at")
    private LocalDateTime firstLoginAt;

    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<OlaRoleUser> roles;

    @OneToMany(mappedBy = "sender")
    private List<OlaMessage> sentMessages;

    @OneToMany(mappedBy = "receiver")
    private List<OlaMessage> receivedMessages;


    // getter setter for is_deleted
    public boolean isDeleted() {
        return this.isDeleted;
    }
    public void setDeleted()     {
        this.isDeleted = true;
    }
}