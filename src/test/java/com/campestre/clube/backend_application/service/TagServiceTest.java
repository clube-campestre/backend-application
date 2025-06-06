package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.entity.Tag;
import com.campestre.clube.backend_application.exceptions.ConflictException;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.repository.TagRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TagServiceTest {

    @InjectMocks
    private TagService tagService;
    @Mock
    private TagRepository tagRepository;
    @Mock
    private StatementService statementService;

    @Test
    @DisplayName("deve registrar Tag com sucesso")
    void registerSuccessfully() {
        Tag tag = new Tag();
        tag.setSurname("Tag1");
        tag.setColor("Azul");

        when(tagRepository.existsBySurnameOrColorContains(anyString(), anyString())).thenReturn(false);
        when(tagRepository.save(any())).thenReturn(tag);

        assertEquals(tag, tagService.register(tag));
        verify(tagRepository, times(1)).existsBySurnameOrColorContains("Tag1", "Azul");
        verify(tagRepository, times(1)).save(tag);
    }

    @Test
    @DisplayName("deve lançar exceção ao registrar Tag com surname ou color já existente")
    void registerWithExistingSurnameOrColor() {
        Tag tag = new Tag();
        tag.setSurname("Tag1");
        tag.setColor("Azul");

        when(tagRepository.existsBySurnameOrColorContains(anyString(), anyString())).thenReturn(true);

        assertThrows(ConflictException.class, () -> tagService.register(tag));
        verify(tagRepository, times(1)).existsBySurnameOrColorContains("Tag1", "Azul");
        verify(tagRepository, never()).save(any());
    }

    @Test
    @DisplayName("deve retornar todas as Tags")
    void getAll() {
        List<Tag> tags = List.of(new Tag());
        when(tagRepository.findAll()).thenReturn(tags);

        assertEquals(tags, tagService.getAll());
        verify(tagRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("deve buscar Tag por id com sucesso")
    void getByIdSuccessfully() {
        Tag tag = new Tag();
        when(tagRepository.findById(anyInt())).thenReturn(Optional.of(tag));

        assertEquals(tag, tagService.getById(1));
        verify(tagRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("deve lançar exceção ao buscar Tag por id inexistente")
    void getByIdNotFound() {
        when(tagRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> tagService.getById(1));
        verify(tagRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("deve atualizar Tag com sucesso")
    void updateSuccessfully() {
        Tag oldTag = new Tag();
        oldTag.setId(1);
        oldTag.setSurname("Antiga");
        oldTag.setColor("Azul");

        Tag newTag = new Tag();
        newTag.setSurname("Nova");
        newTag.setColor("Vermelha");

        when(tagRepository.findById(anyInt())).thenReturn(Optional.of(oldTag));
        when(tagRepository.save(any())).thenReturn(oldTag);

        Tag result = tagService.update(newTag, 1);

        assertEquals("Nova", result.getSurname());
        assertEquals("Vermelha", result.getColor());
        verify(tagRepository, times(1)).findById(1);
        verify(tagRepository, times(1)).save(oldTag);
    }

    @Test
    @DisplayName("deve lançar exceção ao atualizar Tag inexistente")
    void updateNotFound() {
        Tag newTag = new Tag();
        when(tagRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> tagService.update(newTag, 1));
        verify(tagRepository, times(1)).findById(1);
        verify(tagRepository, never()).save(any());
    }

    @Test
    @DisplayName("deve deletar Tag com sucesso")
    void deleteSuccessfully() {
        Tag tagToBeDeleted = new Tag();
        tagToBeDeleted.setId(1);
        Tag tagGenerica = new Tag();
        tagGenerica.setId(3);

        when(tagRepository.findById(1)).thenReturn(Optional.of(tagToBeDeleted));
        when(tagRepository.findById(3)).thenReturn(Optional.of(tagGenerica));
        doNothing().when(statementService).updateAllForTagId(tagToBeDeleted, tagGenerica);
        doNothing().when(tagRepository).deleteById(1);

        tagService.delete(1);

        verify(tagRepository, times(1)).findById(1);
        verify(tagRepository, times(1)).findById(3);
        verify(statementService, times(1)).updateAllForTagId(tagToBeDeleted, tagGenerica);
        verify(tagRepository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("deve lançar exceção ao deletar Tag inexistente")
    void deleteNotFound() {
        when(tagRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> tagService.delete(1));
        verify(tagRepository, times(1)).findById(1);
        verify(tagRepository, never()).deleteById(anyInt());
        verify(statementService, never()).updateAllForTagId(any(), any());
    }
}