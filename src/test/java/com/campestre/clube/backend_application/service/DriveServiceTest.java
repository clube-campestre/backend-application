package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.controller.dtos.responses.DriveRes;
import com.campestre.clube.backend_application.entity.MemberData;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.repository.MemberDataRepository;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.FileList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DriveServiceTest {

    @InjectMocks
    private DriveService driveService;
    @Mock
    private MemberDataRepository memberDataRepository;
    @Mock
    private Drive driveMock;

//    @Test
//    @DisplayName("deve retornar url do arquivo pelo id")
//    void getFileUrl() {
//        String fileId = "abc123";
//        String url = driveService.getFileUrl(fileId);
//        assertEquals("https://drive.google.com/uc?export=view&id=abc123", url);
//    }


    @Test
    @DisplayName("deve listar arquivos com sucesso")
    void listFilesSuccessfully() throws Exception {
        Drive.Files files = mock(Drive.Files.class);
        Drive.Files.List list = mock(Drive.Files.List.class);
        FileList fileList = new FileList();
        com.google.api.services.drive.model.File file = new com.google.api.services.drive.model.File();
        file.setId("id1");
        file.setName("nome1");
        fileList.setFiles(List.of(file));

        when(driveMock.files()).thenReturn(files);
        when(files.list()).thenReturn(list);
        when(list.setFields(anyString())).thenReturn(list);
        when(list.execute()).thenReturn(fileList);

        DriveService spyService = Mockito.spy(driveService);
        doReturn(driveMock).when(spyService).createDriveService();

        List<String> result = spyService.listFiles();
        assertEquals(1, result.size());
        assertTrue(result.get(0).contains("nome1 - id1"));
    }

//    @Test
//    @DisplayName("deve atualizar arquivo com sucesso")
//    void updateFileSuccessfully() throws Exception {
//        MemberData member = new MemberData();
//        member.setCpf("123");
//        when(memberDataRepository.findByCpf("123")).thenReturn(Optional.of(member));
//        when(memberDataRepository.save(any())).thenReturn(member);
//
//        Drive.Files files = mock(Drive.Files.class);
//        Drive.Files.Update update = mock(Drive.Files.Update.class);
//        com.google.api.services.drive.model.File updatedFile = new com.google.api.services.drive.model.File();
//        updatedFile.setId("fileId");
//        updatedFile.setName("img.jpg");
//
//        when(driveMock.files()).thenReturn(files);
//        when(files.update(anyString(), any(), any(FileContent.class))).thenReturn(update);
//        when(update.setFields(anyString())).thenReturn(update);
//        when(update.execute()).thenReturn(updatedFile);
//
//        DriveService spyService = Mockito.spy(driveService);
//        doReturn(driveMock).when(spyService).createDriveService();
//
//        File file = new File("img.jpg");
//        String result = spyService.updateFile("fileId", file, "123");
//        assertTrue(result.contains("Arquivo atualizado: img.jpg"));
//        verify(memberDataRepository).save(any());
//    }

    @Test
    @DisplayName("deve lançar NotFoundException ao atualizar arquivo com membro inexistente")
    void updateFileMemberNotFound() throws Exception {
        when(memberDataRepository.findByCpf("999")).thenReturn(Optional.empty());
        DriveService spyService = Mockito.spy(driveService);
        doReturn(driveMock).when(spyService).createDriveService();
        File file = new File("img.jpg");
        assertThrows(NotFoundException.class, () -> spyService.updateFile("fileId", file, "999"));
    }
}