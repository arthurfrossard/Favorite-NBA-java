package com.teams.favoriteteamsnba.service;

import com.teams.favoriteteamsnba.exception.TimeNotFoundException;
import com.teams.favoriteteamsnba.model.Time;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class TimeService {
    Logger logger = LoggerFactory.getLogger(Time.class);
    private List<Time> times = initTimes();

    private List<Time> initTimes() {
        List<Time> times = new ArrayList<>();
        final String uri = "https://www.balldontlie.io/api/v1/teams";
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();

        for (int i = 1; i <= 2; i++) {
            ResponseEntity<String> response = restTemplate.getForEntity(uri + "?page=" + i, String.class);
            logger.info("Status code: " + ((ResponseEntity<?>) response).getStatusCode());
            String result = response.getBody();
            try {
                JsonNode root = mapper.readTree(result);
                JsonNode data = root.path("data");
                times.addAll(mapper.readValue(data.toString(), new TypeReference<List<Time>>(){}));
            } catch (Exception e) {
                logger.error("Error processing JSON", e);
            }
        }
        return times;
    }

    public List<Time> getAll() {
        if (times.isEmpty()) {
            throw new TimeNotFoundException("Nenhum time encontrado");
        }
        return times;
    }

    public Time getById(long id) {
        return times.stream()
                .filter(time -> time.getId() == id)
                .findFirst()
                .orElseThrow(() -> new TimeNotFoundException(id));
    }

    public List<Time> getByName(String name) {
        List<Time> result = times.stream()
                .filter(time -> time.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
        if (result.isEmpty()) {
            throw new TimeNotFoundException("Nenhum time encontrado com o nome " + name);
        }
        return result;
    }

    public List<Time> getByConference(String conference) {
        List<Time> result = times.stream()
                .filter(time -> time.getConference().toLowerCase().contains(conference.toLowerCase()))
                .collect(Collectors.toList());
        if (result.isEmpty()) {
            throw new TimeNotFoundException("Nenhum time encontrado na conferência " + conference);
        }
        return result;
    }
}


