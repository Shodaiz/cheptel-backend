package com.hbtech.cheptel.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public class RegisterRequest {

    @Schema(example = "ahmed.benali")
    private String username;

    @Schema(example = "ahmed.benali@gmail.com")
    private String email;

    @Schema(example = "0551234567")
    private String phoneNumber;

    @Schema(example = "motdepasse123")
    private String password;

    @Schema(example = "Farmer", allowableValues = {"Farmer", "Veterinarian", "Inspector"})
    private String role;

    public RegisterRequest() {}

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
