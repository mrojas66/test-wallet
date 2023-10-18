package com.nequi.msauth.user.service;

import com.nequi.msauth.entity.UserEntity;
import com.nequi.msauth.exception.BusinessLogicException;
import com.nequi.msauth.exception.ModelNotFoundException;
import com.nequi.msauth.user.dto.UserDto;
import com.nequi.msauth.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final MessageSource messageSource;
    private final UserRepository repository;
    private final Function<UserDto, UserEntity> userDtoToEntity;
    private final Function<UserEntity, UserDto> userToDto;
    private final PasswordEncoder passwordEncoder;

    /**
     * Funcion para guardar nuevo usuario
     * @param userDto
     * @param locale
     * @return
     */
    @Override
    @Transactional
    public UserDto save(UserDto userDto, Locale locale) {
        this.validatePass(userDto.getPassword(),userDto.getConfPassword(), locale);
        this.checkIfExistEmail(userDto, locale);
        UserEntity userEntity = this.userDtoToEntity.apply(userDto);
        userEntity.setStatus(1);
        userEntity.setCreatedAt(LocalDateTime.now());
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity=repository.save(userEntity);
        return userToDto.apply(userEntity);
    }

    /**
     * Funcion para obtener perfil usuario logueado
     * @param loggedUser
     * @param locale
     * @return
     */
    @Override
    public UserDto getById(UserEntity loggedUser,Locale locale) {
        UserEntity user = repository.findById(loggedUser.getId()).orElseThrow(()->
                new ModelNotFoundException(messageSource.getMessage("exception.model.user.invalid",null, locale)));
        return userToDto.apply(user);
    }

    /**
     * Funcion para validar correo
     * @param userDto
     * @param locale
     */
    private void checkIfExistEmail(UserDto userDto, Locale locale){
        if(repository.existsByEmail(userDto.getEmail())){
            throw new BusinessLogicException(
                    messageSource.getMessage("exception.model.email",null, locale)
            );
        }
    }

    /**
     * Funcion para validar contrasenia
     * @param password contraseña
     * @param confPassword confirmacion de contraseña
     * @param locale
     */
    public void validatePass(String password, String confPassword, Locale locale){
        if(password.length()<7){
            throw new BusinessLogicException(
                    messageSource.getMessage("exception.model.pass.length",null, locale));
        }
        if(!password.equals(confPassword)){
            throw new BusinessLogicException(
                    messageSource.getMessage("exception.model.pass",null,locale));
        }
    }
}
