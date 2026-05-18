package net.javaguides.springboot.repository;

import net.javaguides.springboot.entity.UserJWT;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJWTRepository extends JpaRepository<UserJWT, Long> {

    Optional<UserJWT> findByEmail(String email);

    Optional<UserJWT> findByUsernameOrEmail(String username, String email);

    Optional<UserJWT> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
