package com.hbtech.cheptel.repository;

import com.hbtech.cheptel.entity.Role;
import com.hbtech.cheptel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    @Query("select u from User u where u.phone = :phoneNumber")
    Optional<User> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    boolean existsByUsername(String username);

    long countByRoleNot(Role role);
}
