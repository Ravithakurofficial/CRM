package com.curd.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@SpringBootApplication
public class runner {

    public static void main(String[] args) {
        try (InputStream serviceAccount = runner.class.getResourceAsStream("/serviceAccountKey.json")) {
            if (serviceAccount == null) {
                throw new IOException("Service account key not found.");
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://crm-database-8ee82-default-rtdb.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);

            SpringApplication.run(runner.class, args);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
