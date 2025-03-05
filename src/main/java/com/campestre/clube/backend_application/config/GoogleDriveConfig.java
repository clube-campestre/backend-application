package com.campestre.clube.backend_application.config;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.drive.Drive;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Configuration
public class GoogleDriveConfig {

    @Bean
    public Drive googleDrive() throws IOException, GeneralSecurityException {
        GoogleCredential credential = GoogleCredential.fromStream(
                        new FileInputStream("src/main/resources/credentials/service-account.json"))
                .createScoped(Collections.singletonList("https://www.googleapis.com/auth/drive"));

        return new Drive.Builder(
                new NetHttpTransport(),
                JacksonFactory.getDefaultInstance(),
                credential)
                .setApplicationName("ClubeCampestre")
                .build();
    }
}
