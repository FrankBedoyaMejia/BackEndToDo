package com.example.BackEndTodo.controller;

import com.example.BackEndTodo.Entity.User;
import com.example.BackEndTodo.exception.BadRequestException;
import com.example.BackEndTodo.exception.ResourceNotFoundException;
import com.example.BackEndTodo.security.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000, http://127.0.0.1:3000/"})
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationService service;

    public UserController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, AuthenticationService service) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<User>> AllUsers(){return ResponseEntity.ok(service.searchAllUser());}

    @GetMapping("/{email}")
    public ResponseEntity<User> SearchUser(@PathVariable String email) throws ResourceNotFoundException {
        Optional<User> userSearch = service.validateUser(email);
        if (userSearch.isPresent()){
            return ResponseEntity.ok(userSearch.get());
        }
        else{
            throw new ResourceNotFoundException("Error. does not exist" +
                    "the user in the database");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody RegisterRequest request) throws BadRequestException {
        Optional<User> userSearch = service.validateUser(request.getEmail());
        if (userSearch.isPresent()){
            throw new BadRequestException("the user already exists and/or a wrong format was sent");
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(service.register(request));
        }

    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> createAuthenticationTokens(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id)throws ResourceNotFoundException{
        Optional<User> userSearch = service.searchUser(id);
        if (userSearch.isPresent()){
            service.deleteUser(id);
            return ResponseEntity.ok("User with id: " + id + " has been deleted.");
        }else {
            throw new ResourceNotFoundException("Error. does not exist" +
                    "the user in the database");
        }
    }
}
