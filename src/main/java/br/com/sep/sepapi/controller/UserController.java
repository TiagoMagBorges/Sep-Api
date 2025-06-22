package br.com.sep.sepapi.controller;

import br.com.sep.sepapi.domain.model.User;
import br.com.sep.sepapi.dto.UserRequestDTO;
import br.com.sep.sepapi.dto.UserResponseDTO;
import br.com.sep.sepapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/byEmail")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        User user = userService.getUserByEmail(email);
        if (user != null)
            return ResponseEntity.ok(user);
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO user) {
        User userToCreate = new User();
        userToCreate.setName(user.getName());
        userToCreate.setEmail(user.getEmail());
        userToCreate.setPassword(user.getPassword());
        userToCreate.setPhone(user.getPhone());

        User createdUser = userService.createUser(userToCreate);

        UserResponseDTO userResponse = new UserResponseDTO(createdUser);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();
        return ResponseEntity.created(location).body(userResponse);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable Long userId, @RequestBody User user) {
        user.setId(userId);
        userService.updateUser(user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
