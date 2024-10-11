package com.example.IT_SAKERHET_JAVA23_Adam_Barnell_Uppgift2;

import com.example.IT_SAKERHET_JAVA23_Adam_Barnell_Uppgift2.Service.EncryptionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ItSakerhetJava23AdamBarnellUppgift2Application {

	public static void main(String[] args) throws Exception {
		try {
			EncryptionService service = new EncryptionService();
			String original = "Test message for encryption";

			// Encrypt and immediately decrypt
			String encrypted = service.encrypt(original);
			System.out.println("Encrypted: " + encrypted);

			String decrypted = service.decrypt(encrypted);
			System.out.println("Decrypted: " + decrypted);

		} catch (Exception e) {
			e.printStackTrace();
		}
		SpringApplication.run(ItSakerhetJava23AdamBarnellUppgift2Application.class, args);

	}

}
