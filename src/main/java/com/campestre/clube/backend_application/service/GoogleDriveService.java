package com.campestre.clube.backend_application.service;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.client.http.FileContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;

@Service
public class GoogleDriveService {

    @Autowired
    private Drive googleDrive;

    public String upload(java.io.File file, String mimeType, String folderId) throws IOException {
        File fileMetadata = new File();
        fileMetadata.setName(file.getName());
        if (folderId != null) {
            fileMetadata.setParents(Collections.singletonList(folderId));
        }

        FileContent mediaContent = new FileContent(mimeType, file);

        File uploadedFile = googleDrive.files().create(fileMetadata, mediaContent)
                .setFields("id, webViewLink")
                .execute();

        return uploadedFile.getWebViewLink();
    }
}
