package com.sintad.backendTest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.sintad.backendTest.dto.request.LoginRequest;
import com.sintad.backendTest.dto.request.RegisterRequest;
import com.sintad.backendTest.dto.response.JWTResponse;
import com.sintad.backendTest.dto.response.MessageResponse;
import com.sintad.backendTest.models.Rol;
import com.sintad.backendTest.models.Usuario;
import com.sintad.backendTest.repositories.IRolRepository;
import com.sintad.backendTest.repositories.IUsuarioRepository;
import com.sintad.backendTest.security.jwt.JwtUtils;
import com.sintad.backendTest.services.UserService;
import com.sintad.backendTest.utils.TypeRole;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api-sintad/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUsuarioRepository userRepository;

    @Autowired
    private IRolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("login")
    public ResponseEntity<JWTResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserService userDetails = (UserService) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(authentication);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JWTResponse(
                jwt,
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        if (Boolean.TRUE.equals(userRepository.existsByEmail(registerRequest.getEmail()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("fail", "El email ya se encuentra registrado"));
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(registerRequest.getEmail());
        usuario.setUsername(registerRequest.getUsername());
        usuario.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        Rol roles = rolRepository.findByNombre(TypeRole.ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado"));

        usuario.setRoles(Collections.singleton(roles));
        userRepository.save(usuario);

        return ResponseEntity.ok(new MessageResponse("success","Usuario registrado exitosamente"));
    }
}
