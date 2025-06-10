package br.com.sep.sepapi.dto;

import br.com.sep.sepapi.domain.model.User;

public class UserResponseDTO {
    private final Long id;
    private final String name;
    private final String email;
    private final String phone;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}