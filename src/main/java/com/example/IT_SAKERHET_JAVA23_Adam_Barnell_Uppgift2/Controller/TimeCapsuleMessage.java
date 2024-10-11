package com.example.IT_SAKERHET_JAVA23_Adam_Barnell_Uppgift2.Controller;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "timecapsulemessages")
public class TimeCapsuleMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private String decryptedMessage;

    @Column(name = "encrypted_message", nullable = false)
    private String encryptedMessage;

    @Setter
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
