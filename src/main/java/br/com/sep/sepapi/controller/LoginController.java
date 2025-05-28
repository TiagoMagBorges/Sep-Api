package br.com.sep.sepapi.controller;

import br.com.sep.sepapi.dto.LoginRequestDTO;
import br.com.sep.sepapi.dto.LoginResponseDTO;
import br.com.sep.sepapi.domain.model.User;
import br.com.sep.sepapi.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        User user = userService.getUserByEmail(loginRequestDTO.getEmail());

        if (user != null && user.getPassword().equals(loginRequestDTO.getPassword())) {
            LoginResponseDTO response = new LoginResponseDTO(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getPhone()
            );
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}