package com.teams.favoriteteamsnba;

import com.teams.favoriteteamsnba.exception.TimeNotFoundException;
import com.teams.favoriteteamsnba.model.Time;
import com.teams.favoriteteamsnba.service.TimeService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TimeServiceTest {
    Logger logger = LoggerFactory.getLogger(TimeServiceTest.class);
    @Autowired
    private TimeService timeService;

    @Test
    @DisplayName("Test getAll Times")
    public void testGetAll() {
        logger.info("Iniciando teste do método getAll");
        List<Time> times = timeService.getAll();
        assertFalse(times.isEmpty(), "A lista de times não deve estar vazia");
        logger.info("O método getAll retornou uma lista com {} times", times.size());
        logger.info("Teste do método getAll concluído");
    }

    @Test
    @DisplayName("Test getById Time")
    public void testGetById() {
        long timeId = 99L;
        logger.info("Iniciando teste do método getById para o ID: {}", timeId);
        assertThrows(TimeNotFoundException.class, () -> {
            timeService.getById(timeId);
        }, "Deve lançar TimeNotFoundException para ID não existente: " + timeId);
        logger.info("Teste do método getById concluído");
    }

    @Test
    @DisplayName("Test getByName Time")
    public void testGetByName() {
        String nomeTime = "NomeInexistente";
        logger.info("Iniciando teste do método getByName para o nome: {}", nomeTime);
        assertThrows(TimeNotFoundException.class, () -> {
            timeService.getByName(nomeTime);
        }, "Deve lançar TimeNotFoundException para nome não existente: " + nomeTime);
        logger.info("Teste do método getByName concluído");
    }

    @Test
    @DisplayName("Test getByConference Time")
    public void testGetByConference() {
        String nomeConferencia = "ConferenciaInexistente";
        logger.info("Iniciando teste do método getByConference para a conferência: {}", nomeConferencia);
        assertThrows(TimeNotFoundException.class, () -> {
            timeService.getByConference(nomeConferencia);
        }, "Deve lançar TimeNotFoundException para conferência não existente: " + nomeConferencia);
        logger.info("Teste do método getByConference concluído");
    }
}
