package com.youcef.healthcare.Api.repository;

import com.youcef.healthcare.Api.models.Patient;
import com.youcef.healthcare.Api.models._User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<_User,Long> {

    @Query(value = "SELECT * FROM _user WHERE user_type = 'Patient' ",nativeQuery = true)
    List<_User> findAllPatients();

    List<_User> findAllByFnameContainsIgnoreCase(String fName);


    Optional<_User> findByUserName(String userName);
}
