package ola.application.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ola_role_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OlaRoleUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_user_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private OlaUser user;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private OlaRole role;
}