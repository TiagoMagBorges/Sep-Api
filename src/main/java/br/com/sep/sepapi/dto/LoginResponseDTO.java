package br.com.sep.sepapi.dto;

public class LoginResponseDTO {

    private final Long id;

    private final String name;

    private final String email;

    private final String phone;

    public LoginResponseDTO(Long id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
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