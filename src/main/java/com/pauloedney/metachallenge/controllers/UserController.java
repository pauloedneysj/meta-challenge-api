package com.pauloedney.metachallenge.controllers;

import com.pauloedney.metachallenge.dto.MeResponseDTO;
import com.pauloedney.metachallenge.models.UserModel;
import com.pauloedney.metachallenge.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

   @Autowired
   private UserRepository userRepository;

   @GetMapping
   public ResponseEntity<List<MeResponseDTO>> getAllUsers() {
      List<MeResponseDTO> users = userRepository.findAll().stream().map(user -> new MeResponseDTO(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getRole())).toList(); 

    return ResponseEntity.ok(users);
   }

   @GetMapping("/{id}")
   public ResponseEntity<?> getUserById(@PathVariable String id) {
       UserModel user = userRepository.findById(id).orElse(null);

       if (user == null) {
           return ResponseEntity.notFound().build();
       }

       MeResponseDTO userDTO = new MeResponseDTO(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getRole());

       return ResponseEntity.ok(userDTO);
   }

   @PutMapping("/{id}")
   public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody UserModel userUpdates) {
       UserModel user = userRepository.findById(id).orElse(null);

       if (user != null) {
           String encryptedPassword = new BCryptPasswordEncoder().encode(userUpdates.getPassword());

           user.setUsername(userUpdates.getUsername());
           user.setPassword(encryptedPassword);
           user.setFirstName(userUpdates.getFirstName());
           user.setLastName(userUpdates.getLastName());
           user.setRole(userUpdates.getRole());

           userRepository.save(user);
       }

       assert user != null;
       MeResponseDTO updatedUser = new MeResponseDTO(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getRole());

       return ResponseEntity.ok(updatedUser);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<?> deleteUser(@PathVariable String id) {
       userRepository.deleteById(id);

       return ResponseEntity.ok().build();
   }
}
