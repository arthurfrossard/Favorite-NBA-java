package com.teams.favoriteteamsnba;

import com.teams.favoriteteamsnba.exception.TimeNotFoundException;
import com.teams.favoriteteamsnba.model.TimeFavorito;
import com.teams.favoriteteamsnba.service.TimeFavoritoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TimeFavoritoServiceTest {
    Logger logger = LoggerFactory.getLogger(TimeFavoritoServiceTest.class);
    @Autowired
    private TimeFavoritoService timeFavoritoService;

    @Test
    @DisplayName("Testar método getAll")
    public void testGetAll() {
        logger.info("Iniciando teste do método getAll");
        List<TimeFavorito> timesFavoritos = timeFavoritoService.getAll();
        assertFalse(timesFavoritos.isEmpty(), "A lista de TimeFavorito não deve estar vazia");
        if (timesFavoritos.isEmpty()) {
            logger.warn("O método getAll retornou uma lista vazia. Verifique a implementação do serviço.");
        } else {
            logger.info("O método getAll retornou uma lista não vazia com {} elementos.", timesFavoritos.size());
        }
        logger.info("Teste do método getAll concluído");
    }

    @Test
    @DisplayName("Testar método getById quando o TimeFavorito não existe")
    public void testGetByIdWhenTimeFavoritoDoesNotExist() {
        logger.info("Iniciando teste do método getByIdWhenTimeFavoritoDoesNotExist");
        assertThrows(TimeNotFoundException.class, () -> {
            timeFavoritoService.getById(1L);
        });
        logger.info("Teste do método getByIdWhenTimeFavoritoDoesNotExist concluído");
    }

    @Test
    @DisplayName("Testar método getByName quando o TimeFavorito não existe")
    public void testGetByNameWhenTimeFavoritoDoesNotExist() {
        logger.info("Iniciando teste do método getByNameWhenTimeFavoritoDoesNotExist");
        assertThrows(TimeNotFoundException.class, () -> {
            timeFavoritoService.getByName("Time Maravilha");
        });
        logger.info("Teste do método getByNameWhenTimeFavoritoDoesNotExist concluído");
    }

    @Test
    @DisplayName("Testar método getByConference quando o TimeFavorito não existe")
    public void testGetByConferenceWhenTimeFavoritoDoesNotExist() {
        logger.info("Iniciando teste do método getByConferenceWhenTimeFavoritoDoesNotExist");
        assertThrows(TimeNotFoundException.class, () -> {
            timeFavoritoService.getByConference("Conference1");
        });
        logger.info("Teste do método getByConferenceWhenTimeFavoritoDoesNotExist concluído");
    }

    @Test
    @DisplayName("Testar método create")
    public void testCreate() {
        logger.info("Iniciando teste do método create");
        TimeFavorito timeFavorito = new TimeFavorito();
        logger.info("Criando um novo TimeFavorito: {}", timeFavorito);
        TimeFavorito createdTimeFavorito = timeFavoritoService.create(timeFavorito);
        assertEquals(timeFavorito, createdTimeFavorito, "O TimeFavorito criado deve corresponder ao original");
        logger.info("Teste do método create concluído");
    }

    @Test
    @DisplayName("Testar método update quando o TimeFavorito não existe")
    public void testUpdateWhenTimeFavoritoDoesNotExist() {
        logger.info("Iniciando teste do método updateWhenTimeFavoritoDoesNotExist");

        long nonExistingId = 1L; // ID que não existe
        TimeFavorito timeFavorito = new TimeFavorito();
        timeFavorito.setId(nonExistingId);

        assertThrows(TimeNotFoundException.class, () -> {
            logger.info("Atualizando o TimeFavorito: {}", timeFavorito);
            timeFavoritoService.update(nonExistingId, timeFavorito);
        });

        logger.info("Teste do método updateWhenTimeFavoritoDoesNotExist concluído");
    }

    @Test
    @DisplayName("Testar método delete")
    public void testDelete() {
        logger.info("Iniciando teste do método delete");
        assertThrows(TimeNotFoundException.class, () -> {
            long timeFavoritoId = 999L;
            logger.info("Excluindo o TimeFavorito com ID: {}", timeFavoritoId);
            timeFavoritoService.delete(timeFavoritoId);
        });
        logger.info("Teste do método delete concluído");
    }
}
