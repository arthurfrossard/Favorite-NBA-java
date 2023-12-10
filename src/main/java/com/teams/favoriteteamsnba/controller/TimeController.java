package com.teams.favoriteteamsnba.controller;

import com.teams.favoriteteamsnba.error.Erro;
import com.teams.favoriteteamsnba.exception.TimeNotFoundException;
import com.teams.favoriteteamsnba.model.Time;
import com.teams.favoriteteamsnba.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/times")
public class TimeController {
    Logger logger = LoggerFactory.getLogger(TimeController.class);

    @Autowired
    TimeService timeService;

    @GetMapping
    public ResponseEntity<?> getTimes(@RequestParam(required = false) String name, @RequestParam(required = false) String conference) {
        logger.info("Método getTimes de timeController acionado.");
        try {
            List<Time> times;
            if (name != null) {
                times = timeService.getByName(name);
            } else if (conference != null) {
                times = timeService.getByConference(conference);
            } else {
                times = timeService.getAll();
            }
            logger.info("Método getTimes de timeController OK.");
            return new ResponseEntity<>(times, HttpStatus.OK);
        } catch (TimeNotFoundException e) {
            logger.error("Error método getTimes: " + new Erro(e.getMessage()));
            return new ResponseEntity<>(new Erro(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        logger.info("Método getById de timeController acionado para o ID: " + id);
        try {
            Time time = timeService.getById(id);
            logger.info("Método getById de timeController OK.");
            return new ResponseEntity<>(time, HttpStatus.OK);
        } catch (TimeNotFoundException e) {
            logger.error("Error método getById TimeController: " + new Erro(e.getMessage()));
            return new ResponseEntity<>(new Erro(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}

