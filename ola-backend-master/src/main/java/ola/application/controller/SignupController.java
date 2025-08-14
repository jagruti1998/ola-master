package ola.application.controller;

import ola.application.dto.signup.AdminSignupDTO;
import ola.application.dto.signup.DriverSignupDTO;
import ola.application.dto.signup.ResponseDTO;
import ola.application.dto.signup.UserSignupDTO;
import ola.application.services.signupService.SignupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/signup")
public class SignupController {


    private final SignupService signupService;

    public SignupController(SignupService signupService) {
        this.signupService = signupService;
    }


    @PostMapping(value = "/admin", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseDTO> adminSignup (@RequestBody AdminSignupDTO dto){
        signupService.adminSignup(dto);

        ResponseDTO responseDTO = new ResponseDTO(String.valueOf(HttpStatus.CREATED.value()),
                "Admin Register Successfully !");
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping(value = "/driver", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ResponseDTO> driverSignup(@RequestBody DriverSignupDTO dto){
        signupService.driverSignup(dto);

        ResponseDTO responseDTO = new ResponseDTO(String.valueOf(HttpStatus.CREATED.value()),
                "Driver Register successfully");

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping(value = "/user", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseDTO> userSignup(@RequestBody UserSignupDTO dto){
        signupService.userSignup(dto);

        ResponseDTO res =new ResponseDTO(String.valueOf(HttpStatus.CREATED.value()),
                "User Registered Successfully!");
        return ResponseEntity.ok(res);
    }
}
