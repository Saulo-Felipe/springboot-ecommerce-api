package com.ecommerce.ecommerce.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserDetails findByEmail(String email);
    UserDetails findByUsername(String username);
}
