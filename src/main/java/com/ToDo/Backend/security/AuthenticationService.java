package com.ToDo.Backend.security;

import com.ToDo.Backend.entity.Rol;
import com.ToDo.Backend.entity.User;
import com.ToDo.Backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
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

    public AuthenticationResponse authenticate(AuthenticationRequets requets){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requets.getEmail(),
                        requets.getPassword())
        );
        var user = repository.findByEmail(requets.getEmail()).orElseThrow();
        var jwtToken = jwtUtil.generateToken(user);
        return AuthenticationResponse.builder()
                .jwt(jwtToken)
                .build();
    }
}
