package com.example.IT_SAKERHET_JAVA23_Adam_Barnell_Uppgift2.Repository;

import com.example.IT_SAKERHET_JAVA23_Adam_Barnell_Uppgift2.Controller.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}

