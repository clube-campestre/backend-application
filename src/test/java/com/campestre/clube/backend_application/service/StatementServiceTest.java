package com.campestre.clube.backend_application.service;


import com.campestre.clube.backend_application.controller.dtos.requests.StatementRequestDto;
import com.campestre.clube.backend_application.entity.Statement;
import com.campestre.clube.backend_application.entity.Tag;
import com.campestre.clube.backend_application.entity.enums.TransactionType;
import com.campestre.clube.backend_application.exceptions.ConflictException;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.repository.StatementRepository;
import com.campestre.clube.backend_application.repository.TagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("StatementServiceTest")
class StatementServiceTest {

    @InjectMocks
    private StatementService statementService;

    @Mock
    private StatementRepository statementRepository;

    @Mock
    private TagRepository tagRepository;

    private Statement statement;
    private Tag tag;

    @BeforeEach
    void setUp() {
        tag = new Tag();
        tag.setId(1);
        tag.setSurname("BARRACA");

        statement = new Statement();
        statement.setId(1);
        statement.setInformation("info");
        statement.setPrice(10.0);
        statement.setTransactionDate(LocalDateTime.now());
        statement.setTransactionType(TransactionType.ENTRADA);
        statement.setTag(tag);
    }

    @Test
    @DisplayName("Deve registrar Statement com sucesso")
    void registerSuccessfully() {
        when(tagRepository.findBySurname(anyString())).thenReturn(Optional.of(tag));
        when(statementRepository.existsByInformationAndPriceAndTransactionDateAndTag(anyString(), anyDouble(), any(), any())).thenReturn(false);
        when(statementRepository.save(any())).thenReturn(statement);

        Statement result = statementService.register(statement, "barraca");

        assertEquals(statement, result);
        verify(tagRepository).findBySurname("BARRACA");
        verify(statementRepository).save(statement);
    }

    @Test
    @DisplayName("Deve lançar exceção ao registrar Statement com tag inexistente")
    void registerTagNotFound() {
        when(tagRepository.findBySurname(anyString())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> statementService.register(statement, "barraca"));
        verify(tagRepository).findBySurname("BARRACA");
        verify(statementRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção ao registrar Statement duplicado")
    void registerStatementDuplicate() {
        when(tagRepository.findBySurname(anyString())).thenReturn(Optional.of(tag));
        when(statementRepository.existsByInformationAndPriceAndTransactionDateAndTag(anyString(), anyDouble(), any(), any())).thenReturn(true);

        assertThrows(ConflictException.class, () -> statementService.register(statement, "barraca"));
        verify(statementRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve retornar todos os Statements")
    void getAll() {
        List<Statement> list = List.of(statement);
        when(statementRepository.findAll()).thenReturn(list);

        assertEquals(list, statementService.getAll());
        verify(statementRepository).findAll();
    }

    @Test
    @DisplayName("Deve buscar Statement por id com sucesso")
    void getByIdSuccessfully() {
        when(statementRepository.findById(anyInt())).thenReturn(Optional.of(statement));

        assertEquals(statement, statementService.getById(1));
        verify(statementRepository).findById(1);
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar Statement por id inexistente")
    void getByIdNotFound() {
        when(statementRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> statementService.getById(1));
        verify(statementRepository).findById(1);
    }

    @Test
    @DisplayName("Deve atualizar Statement com sucesso")
    void updateSuccessfully() {
        StatementRequestDto dto = mock(StatementRequestDto.class);
        when(dto.getTagName()).thenReturn("barraca");
        when(dto.getInformation()).thenReturn("nova info");
        when(dto.getPrice()).thenReturn(20.0);
        when(dto.getTransactionDate()).thenReturn(LocalDateTime.now());
        when(dto.getTransactionType()).thenReturn(TransactionType.SAIDA);

        when(statementRepository.findById(anyInt())).thenReturn(Optional.of(statement));
        when(tagRepository.findBySurname(anyString())).thenReturn(Optional.of(tag));
        when(statementRepository.save(any())).thenReturn(statement);

        Statement result = statementService.update(dto, 1);

        assertEquals(statement, result);
        verify(statementRepository).findById(1);
        verify(tagRepository).findBySurname("BARRACA");
        verify(statementRepository).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção ao atualizar Statement inexistente")
    void updateNotFound() {
        StatementRequestDto dto = mock(StatementRequestDto.class);
        when(statementRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> statementService.update(dto, 1));
        verify(statementRepository).findById(1);
        verify(tagRepository, never()).findBySurname(anyString());
        verify(statementRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção ao atualizar Statement com tag inexistente")
    void updateTagNotFound() {
        StatementRequestDto dto = mock(StatementRequestDto.class);
        when(dto.getTagName()).thenReturn("barraca");
        when(statementRepository.findById(anyInt())).thenReturn(Optional.of(statement));
        when(tagRepository.findBySurname(anyString())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> statementService.update(dto, 1));
        verify(tagRepository).findBySurname("BARRACA");
        verify(statementRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve atualizar todos os Statements de uma Tag para outra")
    void updateAllForTagId() {
        List<Statement> list = List.of(statement);
        Tag genericTag = new Tag();
        when(statementRepository.findAllByTag(tag)).thenReturn(list);

        statementService.updateAllForTagId(tag, genericTag);

        assertEquals(genericTag, list.get(0).getTag());
        verify(statementRepository).findAllByTag(tag);
        verify(statementRepository).saveAll(list);
    }

    @Test
    @DisplayName("Deve deletar todos os Statements de uma Tag")
    void deleteByTagSuccessfully() {
        when(tagRepository.findBySurname(anyString())).thenReturn(Optional.of(tag));
        doNothing().when(statementRepository).deleteByTag(tag);

        statementService.deleteByTag("barraca");

        verify(tagRepository).findBySurname("BARRACA");
        verify(statementRepository).deleteByTag(tag);
    }

    @Test
    @DisplayName("Deve lançar exceção ao deletar Statements de Tag inexistente")
    void deleteByTagNotFound() {
        when(tagRepository.findBySurname(anyString())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> statementService.deleteByTag("barraca"));
        verify(statementRepository, never()).deleteByTag(any());
    }

    @Test
    @DisplayName("Deve deletar Statement por id com sucesso")
    void deleteSuccessfully() {
        when(statementRepository.findById(anyInt())).thenReturn(Optional.of(statement));
        doNothing().when(statementRepository).deleteById(anyInt());

        statementService.delete(1);

        verify(statementRepository).findById(1);
        verify(statementRepository).deleteById(1);
    }

    @Test
    @DisplayName("Deve lançar exceção ao deletar Statement inexistente")
    void deleteNotFound() {
        when(statementRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> statementService.delete(1));
        verify(statementRepository).findById(1);
        verify(statementRepository, never()).deleteById(anyInt());
    }

    @Test
    @DisplayName("Deve buscar Statements por filtro e paginação")
    void getByFilterAndPagination() {
        List<Statement> list = List.of(statement);
        Page<Statement> page = new PageImpl<>(list);
        when(statementRepository.findByFilterAndPagination(any(), any(), any(), any(), any(), any(Pageable.class))).thenReturn(page);

        List<Statement> result = statementService.getByFilterAndPagination(null, null, null, null, null, 0, 10);

        assertEquals(list, result);
        verify(statementRepository).findByFilterAndPagination(any(), any(), any(), any(), any(), any(Pageable.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao salvar Statement nulo")
    void saveNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> statementService.save(null));
        verify(statementRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve salvar Statement com sucesso")
    void saveSuccessfully() {
        when(statementRepository.save(any())).thenReturn(statement);

        Statement result = statementService.save(statement);

        assertEquals(statement, result);
        verify(statementRepository).save(statement);
    }

    @Test
    @DisplayName("Deve retornar todos os Statements (findAll)")
    void findAll() {
        List<Statement> list = List.of(statement);
        when(statementRepository.findAll()).thenReturn(list);

        assertEquals(list, statementService.findAll());
        verify(statementRepository).findAll();
    }

    @Test
    @DisplayName("Deve buscar Statement por id (findById) com sucesso")
    void findByIdSuccessfully() {
        when(statementRepository.findById(anyInt())).thenReturn(Optional.of(statement));

        assertEquals(statement, statementService.findById(1));
        verify(statementRepository).findById(1);
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar Statement por id inexistente (findById)")
    void findByIdNotFound() {
        when(statementRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> statementService.findById(1));
        verify(statementRepository).findById(1);
    }

    @Test
    @DisplayName("Deve buscar Statements por Tag com sucesso")
    void findByTagSuccessfully() {
        List<Statement> list = List.of(statement);
        when(tagRepository.findBySurname(anyString())).thenReturn(Optional.of(tag));
        when(statementRepository.findAllByTag(tag)).thenReturn(list);

        List<Statement> result = statementService.findByTag("barraca");

        assertEquals(list, result);
        verify(tagRepository).findBySurname("BARRACA");
        verify(statementRepository).findAllByTag(tag);
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar Statements por Tag inexistente")
    void findByTagNotFound() {
        when(tagRepository.findBySurname(anyString())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> statementService.findByTag("barraca"));
        verify(tagRepository).findBySurname("BARRACA");
        verify(statementRepository, never()).findAllByTag(any());
    }


}
