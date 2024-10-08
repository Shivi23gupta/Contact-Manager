package com.cm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cm.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

    // extra method db relatedoperations
    // custom query methods
    // custom finder methods

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailToken(String emailToken);
}
