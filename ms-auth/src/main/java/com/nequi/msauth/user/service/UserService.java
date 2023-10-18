package com.nequi.msauth.user.service;

import com.nequi.msauth.user.dto.UserDto;
import com.nequi.msauth.entity.UserEntity;

import java.util.Locale;

public interface UserService {

    UserDto save(UserDto userDto, Locale locale);
    UserDto getById(UserEntity loggedUser,Locale locale);
}
