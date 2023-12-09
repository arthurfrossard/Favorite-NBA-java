package com.teams.favoriteteamsnba.controller;

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
@RequestMapping("/time")
public class TimeController {
    Logger logger = LoggerFactory.getLogger(TimeController.class);
    @Autowired
    TimeService timeService;

    @GetMapping
    public ResponseEntity<List<Time>> getTimes(@RequestParam(required = false) String name, @RequestParam(required = false) String conference) {
        List<Time> times;
        if (name != null) {
            times = timeService.getByName(name);
        } else if (conference != null) {
            times = timeService.getByConference(conference);
        } else {
            times = timeService.getAll();
        }
        return new ResponseEntity<>(times, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Time> getById(@PathVariable int id){
        logger.info("GET CLIENTE: " + id);
        try {
            Time time = timeService.getById(id);
            return new ResponseEntity<>(time, HttpStatus.OK);
        } catch (TimeNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
