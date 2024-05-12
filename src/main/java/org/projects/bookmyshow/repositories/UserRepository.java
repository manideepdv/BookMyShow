package org.projects.bookmyshow.repositories;

import org.projects.bookmyshow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // select * from users where id = user_id
    @Override
    Optional<User> findById(Long userId);

    Optional<User> findByEmail(String email);
}

/*
user_id <-> user
*/