package com.example.BackEndTodo.security;

import com.example.BackEndTodo.Entity.Rol;
import com.example.BackEndTodo.Entity.User;
import com.example.BackEndTodo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private static final Logger LOGGER = Logger.getLogger(AuthenticationService.class);

    public AuthenticationResponse register(RegisterRequest request){
        Rol rol = new Rol();
        rol.setId(2);
        var user = User.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(rol)
                .build();
                repository.save(user);
                var jwtToken = jwtUtil.generateToken(user);
                return AuthenticationResponse.builder()
                        .jwt(jwtToken)
                        .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtUtil.generateToken(user);
        return AuthenticationResponse.builder()
                .jwt(jwtToken)
                .build();
    }

    public Optional<User> validateUser(String email){
        return repository.findByEmail(email);
    }
    public List<User> searchAllUser(){
        LOGGER.info("All users were searched: ");
        return repository.findAll();

    }

    public Optional<User> searchUser(Long id){
        LOGGER.info("A user with ID was searched: "+ id);
        return repository.findById(id);

    }

    public void deleteUser(Long id){
        LOGGER.warn("The user with ID has been removed: "+id);
        repository.deleteById(id);
    }
}
