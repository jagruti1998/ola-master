package ola.application.services.signupService;

import ola.application.dto.signup.AdminSignupDTO;
import ola.application.dto.signup.DriverSignupDTO;
import ola.application.dto.signup.UserSignupDTO;
import ola.application.entity.OlaDriver;
import ola.application.entity.OlaRole;
import ola.application.entity.OlaRoleUser;
import ola.application.entity.OlaUser;
import ola.application.repository.DriverRepo;
import ola.application.repository.RoleUserRepo;
import ola.application.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.ldap.PagedResultsControl;
import java.time.LocalDateTime;

@Service
public class SignupService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleUserRepo roleUserRepo;
    @Autowired
    private DriverRepo driverRepo;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void adminSignup(AdminSignupDTO dto) {
        OlaUser userEntity = new OlaUser();
        userEntity.setName(dto.getName());
        userEntity.setEmail(dto.getEmail());
        userEntity.setUsername(dto.getUsername());
        userEntity.setPassword(passwordEncoder().encode(dto.getPassword()));
        userEntity.setCreatedAt(LocalDateTime.now());
        userEntity.setUpdatedAt(LocalDateTime.now());
        userEntity.setIsDeleted(false);
        userRepo.save(userEntity);

        OlaRoleUser roleUserEntity = new OlaRoleUser();
        OlaRole rolesEntity = new OlaRole();
        rolesEntity.setId(1);
        roleUserEntity.setUser(userEntity);
        roleUserEntity.setRole(rolesEntity);
        roleUserRepo.save(roleUserEntity);

    }

    public void driverSignup(DriverSignupDTO dto) {
        OlaUser userEntity = new OlaUser();
        userEntity.setName(dto.getName());
        userEntity.setEmail(dto.getEmail());
        userEntity.setUsername(dto.getUsername());
        userEntity.setPassword(passwordEncoder().encode(dto.getPassword()));
        userEntity.setCreatedAt(LocalDateTime.now());
        userEntity.setUpdatedAt(LocalDateTime.now());
        userEntity.setIsDeleted(false);
        userRepo.save(userEntity);

        OlaRoleUser roleUserEntity = new OlaRoleUser();
        OlaRole rolesEntity = new OlaRole();
        rolesEntity.setId(2);
        roleUserEntity.setUser(userEntity);
        roleUserEntity.setRole(rolesEntity);
        roleUserRepo.save(roleUserEntity);

        OlaDriver driver = new OlaDriver();
        driver.setUser(userEntity);
        driver.setDob(dto.getDob());
        driver.setCreatedAt(LocalDateTime.now());
        driver.setUpdatedAt(LocalDateTime.now());
        driverRepo.save(driver);


    }

    public void userSignup(UserSignupDTO dto) {
        OlaUser userEntity = new OlaUser();
        userEntity.setName(dto.getName());
        userEntity.setEmail(dto.getEmail());
        userEntity.setUsername(dto.getUsername());
        userEntity.setPassword(passwordEncoder().encode(dto.getPassword()));
        userEntity.setCreatedAt(LocalDateTime.now());
        userEntity.setUpdatedAt(LocalDateTime.now());
        userEntity.setIsDeleted(false);
        userRepo.save(userEntity);

        OlaRoleUser roleUserEntity = new OlaRoleUser();
        OlaRole rolesEntity = new OlaRole();
        rolesEntity.setId(3);
        roleUserEntity.setUser(userEntity);
        roleUserEntity.setRole(rolesEntity);
        roleUserRepo.save(roleUserEntity);
    }
}
