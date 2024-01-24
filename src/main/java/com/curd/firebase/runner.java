package com.curd.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@SpringBootApplication
public class runner {

    public static void main(String[] args) throws IOException {
        ClassLoader classLoader = runner.class.getClassLoader();


        File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json")).getFile());

        // Use try-with-resources to automatically close the InputStream
        try (InputStream serviceAccount = new FileInputStream(file)) {
            FirebaseOptions options = new FirebaseOptions.Builder()
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