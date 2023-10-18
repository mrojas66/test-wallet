package com.nequi.msauth.user.mapper;

import com.nequi.msauth.entity.UserEntity;
import com.nequi.msauth.user.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ConverterUserFactory {

    @Bean
    public Function<UserEntity, UserDto> userToDto(ModelMapper mapper) {
        return userEntity -> mapper.map(userEntity, UserDto.class);
    }

    @Bean
    public Function<UserDto, UserEntity> userDtoToEntity(ModelMapper mapper) {
        return userDto -> mapper.map(userDto, UserEntity.class);
    }
}
