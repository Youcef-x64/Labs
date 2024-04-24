package com.esihati.repository;

import com.esihati.model._User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<_User, Long> {

    Optional<_User> findByEmailOrPhone(String email, String phone);

}
