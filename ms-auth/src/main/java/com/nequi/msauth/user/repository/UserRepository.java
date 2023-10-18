package com.nequi.msauth.user.repository;

import com.nequi.msauth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity>findByEmail(String email);

    boolean existsByEmail(String email);

    @Query(value = "select count(u)>0 from UserEntity u where u.documentNumber=:documentNumber and u.id<>:userId")
    boolean existsByDocumentNumberAndUser(String documentNumber, UUID userId);

    @Query(value = "select u.id from UserEntity u where u.email=:email")
    UUID findUUIDByEmail(String email);
}
