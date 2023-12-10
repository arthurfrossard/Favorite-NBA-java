package com.teams.favoriteteamsnba;

import com.teams.favoriteteamsnba.exception.TimeNotFoundException;
import com.teams.favoriteteamsnba.model.TimeFavorito;
import com.teams.favoriteteamsnba.service.TimeFavoritoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TimeFavoritoServiceTest {

    @Autowired
    private TimeFavoritoService timeFavoritoService;

    @Test
    @DisplayName("Testar método getAll")
    public void testGetAll() {
        List<TimeFavorito> timesFavoritos = timeFavoritoService.getAll();
        assertFalse(timesFavoritos.isEmpty(), "A lista de TimeFavorito não deve estar vazia");
    }

    @Test
    @DisplayName("Testar método getById quando o TimeFavorito não existe")
    public void testGetByIdWhenTimeFavoritoDoesNotExist() {
        assertThrows(TimeNotFoundException.class, () -> {
            timeFavoritoService.getById(1L);
        });
    }

    @Test
    @DisplayName("Testar método getByName quando o TimeFavorito não existe")
    public void testGetByNameWhenTimeFavoritoDoesNotExist() {
        assertThrows(TimeNotFoundException.class, () -> {
            timeFavoritoService.getByName("Time Maravilha");
        });
    }

    @Test
    @DisplayName("Testar método getByConference quando o TimeFavorito não existe")
    public void testGetByConferenceWhenTimeFavoritoDoesNotExist() {
        assertThrows(TimeNotFoundException.class, () -> {
            timeFavoritoService.getByConference("Conference1");
        });
    }

    @Test
    @DisplayName("Testar método create")
    public void testCreate() {
        TimeFavorito timeFavorito = new TimeFavorito();
        TimeFavorito createdTimeFavorito = timeFavoritoService.create(timeFavorito);
        assertEquals(timeFavorito, createdTimeFavorito, "O TimeFavorito criado deve corresponder ao original");
    }

    @Test
    @DisplayName("Testar método update quando o TimeFavorito não existe")
    public void testUpdateWhenTimeFavoritoDoesNotExist() {
        assertThrows(TimeNotFoundException.class, () -> {
            TimeFavorito timeFavorito = new TimeFavorito();
            timeFavorito.setId(1L);
            timeFavoritoService.update(timeFavorito);
        });
    }

    @Test
    @DisplayName("Testar método delete")
    public void testDelete() {
        assertThrows(TimeNotFoundException.class, () -> {
            timeFavoritoService.delete(999L);
        });
    }
}
