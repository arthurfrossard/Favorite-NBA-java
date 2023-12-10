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
        try {
            List<TimeFavorito> timesFavoritos;
            if (name != null) {
                timesFavoritos = timeFavoritoService.getByName(name);
            } else if (conference != null) {
                timesFavoritos = timeFavoritoService.getByConference(conference);
            } else {
                timesFavoritos = timeFavoritoService.getAll();
            }
            return new ResponseEntity<>(timesFavoritos, HttpStatus.OK);
        } catch (TimeNotFoundException e) {
            return new ResponseEntity<>(new Erro(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        logger.info("GET TimeFavorito: " + id);
        try {
            TimeFavorito timeFavorito = timeFavoritoService.getById(id);
            return new ResponseEntity<>(timeFavorito, HttpStatus.OK);
        } catch (TimeNotFoundException e) {
            return new ResponseEntity<>(new Erro(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TimeFavorito timeFavorito) {
        TimeFavorito createdTimeFavorito = timeFavoritoService.create(timeFavorito);
        return new ResponseEntity<>(createdTimeFavorito, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody TimeFavorito timeFavorito) {
        if (timeFavorito.getId() != id) {
            throw new IllegalArgumentException("ID do recurso na URL e no corpo da solicitação não correspondem");
        }
        TimeFavorito updatedTimeFavorito = timeFavoritoService.update(timeFavorito);
        return new ResponseEntity<>(updatedTimeFavorito, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        timeFavoritoService.delete(id);
        return new ResponseEntity<>("TimeFavorito com id " + id + " foi excluído com sucesso.", HttpStatus.OK);
    }
}
