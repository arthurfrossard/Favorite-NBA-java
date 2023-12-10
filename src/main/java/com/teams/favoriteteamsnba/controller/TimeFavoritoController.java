package com.teams.favoriteteamsnba.controller;

import com.teams.favoriteteamsnba.error.Erro;
import com.teams.favoriteteamsnba.exception.TimeNotFoundException;
import com.teams.favoriteteamsnba.model.TimeFavorito;
import com.teams.favoriteteamsnba.service.TimeFavoritoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/timesfavoritos")
public class TimeFavoritoController {
    Logger logger = LoggerFactory.getLogger(TimeFavoritoController.class);

    @Autowired
    private TimeFavoritoService timeFavoritoService;

    @GetMapping
    public ResponseEntity<?> getTimesFavoritos(@RequestParam(required = false) String conference, @RequestParam(required = false) String name) {
        logger.info("Método getTimesFavoritos de timeFavoritoController acionado.");
        try {
            List<TimeFavorito> timesFavoritos;
            if (name != null) {
                timesFavoritos = timeFavoritoService.getByName(name);
            } else if (conference != null) {
                timesFavoritos = timeFavoritoService.getByConference(conference);
            } else {
                timesFavoritos = timeFavoritoService.getAll();
            }
            logger.info("Método getTimes de timeController OK.");
            return new ResponseEntity<>(timesFavoritos, HttpStatus.OK);
        } catch (TimeNotFoundException e) {
            logger.error("Error método getTimes: " + new Erro(e.getMessage()));
            return new ResponseEntity<>(new Erro(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        logger.info("Método getById de timeController acionado para o ID: " + id);
        try {
            TimeFavorito timeFavorito = timeFavoritoService.getById(id);
            logger.info("Método getById de timeController OK.");
            return new ResponseEntity<>(timeFavorito, HttpStatus.OK);
        } catch (TimeNotFoundException e) {
            logger.error("Error método getById TimeController: " + new Erro(e.getMessage()));
            return new ResponseEntity<>(new Erro(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TimeFavorito timeFavorito) {
        TimeFavorito createdTimeFavorito = timeFavoritoService.create(timeFavorito);
        logger.info("TimeFavorito criado com sucesso.");
        return new ResponseEntity<>(createdTimeFavorito, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody TimeFavorito timeFavorito) {
        try {
            TimeFavorito updatedTimeFavorito = timeFavoritoService.update(id, timeFavorito);
            logger.info("TimeFavorito alterado com sucesso.");
            return new ResponseEntity<>(updatedTimeFavorito, HttpStatus.OK);
        } catch (TimeNotFoundException e) {
            logger.error("Error método update TimeFavoritoController: " + new Erro(e.getMessage()));
            return new ResponseEntity<>(new Erro(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        try {
            timeFavoritoService.delete(id);
            logger.info("TimeFavorito com id " + id + " excluído com sucesso.");
            return new ResponseEntity<>("TimeFavorito com id " + id + " foi excluído com sucesso.", HttpStatus.OK);
        } catch (TimeNotFoundException e) {
            logger.error("Error método delete TimeFavoritoController: " + new Erro(e.getMessage()));
            return new ResponseEntity<>(new Erro(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
