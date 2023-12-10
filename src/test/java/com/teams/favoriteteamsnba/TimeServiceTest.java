package com.teams.favoriteteamsnba;

import com.teams.favoriteteamsnba.exception.TimeNotFoundException;
import com.teams.favoriteteamsnba.model.Time;
import com.teams.favoriteteamsnba.service.TimeService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TimeServiceTest {
    @Autowired
    private TimeService timeService;

    @Test
    @DisplayName("Test getAll Times")
    public void testGetAll() {
        List<Time> times = timeService.getAll();
        assertFalse(times.isEmpty(), "A lista de times não deve estar vazia");
    }

    @Test
    @DisplayName("Test getById Time")
    public void testGetById() {
        assertThrows(TimeNotFoundException.class, () -> timeService.getById(99L), "Deve lançar TimeNotFoundException para id não existente");
    }

    @Test
    @DisplayName("Test getByName Time")
    public void testGetByName() {
        assertThrows(TimeNotFoundException.class, () -> timeService.getByName("NomeInexistente"), "Deve lançar TimeNotFoundException para nome não existente");
    }

    @Test
    @DisplayName("Test getByConference Time")
    public void testGetByConference() {
        assertThrows(TimeNotFoundException.class, () -> timeService.getByConference("ConferenciaInexistente"), "Deve lançar TimeNotFoundException para conferência não existente");
    }
}
