package ola.application.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Table(name = "ola_role")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OlaRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_description")
    private String roleDescription;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Collection<OlaRoleUser> user;
}