package ola.application.services.userService;


import ola.application.entity.OlaRoleUser;
import ola.application.entity.OlaUser;
import ola.application.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        OlaUser userEntity = userRepo.findByUsernameAndIsDeletedFalse(username).orElseThrow(()->new UsernameNotFoundException("User not found with username :"+username));
        Collection<OlaRoleUser> roles = userEntity.getRoles();
        if (roles.isEmpty()) {
            throw new UsernameNotFoundException("User does not have any roles assigned");
        }
        String roleName = roles.iterator().next().getRole().getRoleName();

        return org.springframework.security.core.userdetails.User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .roles(roleName)
                .build();
    }
}