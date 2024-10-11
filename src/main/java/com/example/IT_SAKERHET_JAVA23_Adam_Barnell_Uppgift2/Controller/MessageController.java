package com.example.IT_SAKERHET_JAVA23_Adam_Barnell_Uppgift2.Controller;

import com.example.IT_SAKERHET_JAVA23_Adam_Barnell_Uppgift2.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/message")
    public ResponseEntity<?> saveMessage(@RequestBody Map<String, String> payload, @RequestHeader("Authorization") String authHeader) {
        String messageContent = payload.get("messages");
        try {
            messageService.saveMessage(messageContent, authHeader);
            return ResponseEntity.ok("Message saved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error saving message.");
        }
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Map<String, String>>> getMessages(@RequestHeader("Authorization") String authHeader) {
        try {
            List<Map<String, String>> decryptedMessages = messageService.getMessages(authHeader);
            return ResponseEntity.ok(decryptedMessages);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
