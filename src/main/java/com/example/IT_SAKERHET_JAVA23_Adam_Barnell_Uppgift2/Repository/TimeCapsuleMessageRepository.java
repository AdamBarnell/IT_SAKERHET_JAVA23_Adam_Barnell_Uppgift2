package com.example.IT_SAKERHET_JAVA23_Adam_Barnell_Uppgift2.Repository;

import com.example.IT_SAKERHET_JAVA23_Adam_Barnell_Uppgift2.Controller.TimeCapsuleMessage;
import com.example.IT_SAKERHET_JAVA23_Adam_Barnell_Uppgift2.Controller.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface TimeCapsuleMessageRepository extends JpaRepository<TimeCapsuleMessage, Long> {
    List<TimeCapsuleMessage> findByUserId(Long userId);
}

