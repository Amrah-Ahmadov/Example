package com.example.callcenterforloanproject.controller;

import com.example.callcenterforloanproject.dto.UserDto;
import com.example.callcenterforloanproject.dto.UserLoginDto;
import com.example.callcenterforloanproject.dto.UserLoginResponseDto;
import com.example.callcenterforloanproject.dto.UserRegisterDto;
import com.example.callcenterforloanproject.exception.RepeatPasswordIncorrectException;
import com.example.callcenterforloanproject.jwt.JWTManager;
import com.example.callcenterforloanproject.jwt.MyUserDetailsService;
import com.example.callcenterforloanproject.model.User;
import com.example.callcenterforloanproject.service.ConverterService;
import com.example.callcenterforloanproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final ConverterService converterService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JWTManager jwtManager;
    private final MyUserDetailsService myUserDetailsService;

    public UserController(ConverterService converterService, UserService userService, AuthenticationManager authenticationManager, JWTManager jwtManager, MyUserDetailsService myUserDetailsService) {
        this.converterService = converterService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtManager = jwtManager;
        this.myUserDetailsService = myUserDetailsService;
    }


    @PostMapping("/register")
    public ResponseEntity<UserDto> insertUser(@RequestBody UserRegisterDto registration){
        if(!registration.getRepeatPassword().equals(registration.getPassword())){
            throw new RepeatPasswordIncorrectException();
        }
        User user = userService.register(registration);
        return new ResponseEntity<>(converterService.convertUserToUserDto(user), HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> authenticate(@RequestBody UserLoginDto userLoginDto) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword()));
            User user = userService.getUSerByUsername(userLoginDto.getUsername());
            final String token = jwtManager.generateJwt(user.getUsername());
            return new ResponseEntity<>(new UserLoginResponseDto(user.getUsername(), token), HttpStatus.OK);
        }catch (Exception e){
            throw e;
        }
    }
    @GetMapping("/welcome")
    public ResponseEntity<String> getWelcomeMessage(){
        return new ResponseEntity<>("Hello and welcome", HttpStatus.OK);
    }
    private void authenticate(String username, String password) throws Exception {
        try {
            System.out.println("222222");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            System.out.println("111111");
        } catch (DisabledException e) {
            System.out.println("Test 1");
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            System.out.println("test2");
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
