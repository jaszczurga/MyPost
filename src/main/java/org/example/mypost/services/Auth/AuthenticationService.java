package org.example.mypost.services.Auth;
import lombok.RequiredArgsConstructor;

import org.example.mypost.Dto.Authentication.AuthenticationRequest;
import org.example.mypost.Dto.Authentication.AuthenticationResponse;
import org.example.mypost.Dto.Authentication.RegisterRequest;
import org.example.mypost.dao.UserRepository;
import org.example.mypost.entity.Authentication.Role;
import org.example.mypost.entity.User;
import org.example.mypost.exception.UserNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }
        var user = User.builder()
                .firstName( request.getFirstName() )
                .lastName( request.getLastName() )
                .email( request.getEmail() )
                .password( passwordEncoder.encode(request.getPassword()) )
                .role( Role.USER )
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return  AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        return  AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    public int getLoggedInUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            User user = userRepository.findByEmail(username)
                    .orElseThrow(() -> new UserNotFoundException("User not found with email: " + username));
            return user.getUserId();
        }

        throw new IllegalStateException("User not logged in");
    }


}