package com.nequi.msauth.user.controller;

import com.nequi.msauth.commons.GeneralBodyResponse;
import com.nequi.msauth.entity.UserEntity;
import com.nequi.msauth.user.dto.UserDto;
import com.nequi.msauth.user.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final MessageSource messageSource;
    private final UserService service;

    /**
     * Servicio para registro de usuarios
     * @param userDto dto usuario
     * @param locale
     * @return
     */
    @PostMapping
    public ResponseEntity<GeneralBodyResponse> save(@Valid @RequestBody UserDto userDto, Locale locale) {
        UserDto user = service.save(userDto, locale);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new GeneralBodyResponse<>(messageSource.getMessage("general.create.success", null, locale), user));
    }

    /**
     * Servicio para obtener mi perfil
     * @param loggedUser
     * @param locale
     * @return
     */
    @GetMapping("me")
    @SecurityRequirement(name = "bearer-jwt")
    public ResponseEntity<GeneralBodyResponse> myProfile(@AuthenticationPrincipal UserEntity loggedUser, Locale locale) {
        UserDto user = service.getById(loggedUser, locale);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new GeneralBodyResponse<>(messageSource.getMessage("general.create.success", null, locale), user));
    }
}
