package com.authentication.auth.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
