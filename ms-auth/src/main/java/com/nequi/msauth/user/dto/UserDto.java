package com.nequi.msauth.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class UserDto {

    private UUID id;
    @NotNull(message = "Nombre es requerido")
    private String firstName;
    @NotNull(message = "Apellido es requerido")
    private String lastName;
    @NotNull(message = "Correo electronico es requerido")
    private String email;
    private String phone;
    private String address;
    private Integer cityId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String confPassword;
}
