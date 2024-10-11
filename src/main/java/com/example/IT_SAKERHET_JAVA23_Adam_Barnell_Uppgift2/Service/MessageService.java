package com.example.IT_SAKERHET_JAVA23_Adam_Barnell_Uppgift2.Service;

import com.example.IT_SAKERHET_JAVA23_Adam_Barnell_Uppgift2.Controller.TimeCapsuleMessage;
import com.example.IT_SAKERHET_JAVA23_Adam_Barnell_Uppgift2.Controller.User;
import com.example.IT_SAKERHET_JAVA23_Adam_Barnell_Uppgift2.Repository.TimeCapsuleMessageRepository;
import com.example.IT_SAKERHET_JAVA23_Adam_Barnell_Uppgift2.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class MessageService {

    private final TimeCapsuleMessageRepository messageRepository;
    private final UserRepository userRepository;
    private final AuthService authService;

    public MessageService(TimeCapsuleMessageRepository messageRepository, UserRepository userRepository, EncryptionService encryptionService, AuthService authService) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.encryptionService = encryptionService;
        this.authService = authService;
    }


    public void saveMessage(String message, String authHeader) throws Exception {
        System.out.println("Token received: " + authHeader);

        String email = authService.extractEmailFromToken(authHeader);

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found for email: " + email);
        }

        String encryptedMessage = encryptionService.encrypt(message);
        if (encryptedMessage == null || encryptedMessage.isEmpty()) {
            throw new RuntimeException("Failed to encrypt the message.");
        }

        TimeCapsuleMessage timeCapsuleMessage = new TimeCapsuleMessage();
        timeCapsuleMessage.setEncryptedMessage(encryptedMessage);
        timeCapsuleMessage.setUser(user);
        timeCapsuleMessage.setCreatedAt(LocalDateTime.now());

        messageRepository.save(timeCapsuleMessage);
    }

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private TimeCapsuleMessageRepository timeCapsuleMessageRepository;

    public List<Map<String, String>> getMessages(String authHeader) throws Exception {
        String email = authService.extractEmailFromToken(authHeader);
        User user = userRepository.findByEmail(email);

        List<TimeCapsuleMessage> encryptedMessages = timeCapsuleMessageRepository.findByUserId(user.getId());

        List<Map<String, String>> decryptedMessages = new ArrayList<>();

        for (TimeCapsuleMessage message : encryptedMessages) {
            String decryptedMessage = encryptionService.decrypt(message.getEncryptedMessage());

            Map<String, String> messageData = new HashMap<>();
            messageData.put("decryptedMessage", decryptedMessage);
            messageData.put("createdAt", message.getCreatedAt().toString());

            decryptedMessages.add(messageData);
        }

        return decryptedMessages;
    }





}
