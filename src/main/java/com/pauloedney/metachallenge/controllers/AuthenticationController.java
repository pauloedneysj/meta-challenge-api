package com.pauloedney.metachallenge.controllers;


import com.pauloedney.metachallenge.models.UserModel;
import com.pauloedney.metachallenge.models.dtos.AuthenticationDTO;
import com.pauloedney.metachallenge.models.dtos.LoginResponseDTO;
import com.pauloedney.metachallenge.models.dtos.MeResponseDTO;
import com.pauloedney.metachallenge.models.dtos.RegisterDTO;
import com.pauloedney.metachallenge.repositories.UserRepository;
import com.pauloedney.metachallenge.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserModel) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO data){
        if(this.repository.findByUsername(data.username()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserModel newUser = new UserModel(data.firstName(), data.lastName(), data.username(), encryptedPassword, data.role());

        this.repository.save(newUser);

        MeResponseDTO newUserDTO = new MeResponseDTO(newUser.getId(), newUser.getUsername(), newUser.getFirstName(), newUser.getLastName(), newUser.getRole());

        return ResponseEntity.status(HttpStatus.CREATED).body(newUserDTO);
    }

    @GetMapping("/me")
    public ResponseEntity<?> me() {
        UserModel currentUser = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MeResponseDTO me = new MeResponseDTO(currentUser.getId(), currentUser.getUsername(), currentUser.getFirstName(), currentUser.getLastName(), currentUser.getRole());
        
        return ResponseEntity.ok(me);
    }
}

