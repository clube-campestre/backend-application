package com.campestre.clube.backend_application.config;

import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GoogleDriveConfig {

    public GoogleCredentials getCredentials() throws IOException {
        ClassPathResource resource = new ClassPathResource("credentials/credentials.json");
        return GoogleCredentials.fromStream(resource.getInputStream());
    }
}
