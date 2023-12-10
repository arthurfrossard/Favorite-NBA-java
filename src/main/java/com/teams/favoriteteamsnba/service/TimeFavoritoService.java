package com.teams.favoriteteamsnba.service;

import com.teams.favoriteteamsnba.exception.TimeNotFoundException;
import com.teams.favoriteteamsnba.model.Time;
import com.teams.favoriteteamsnba.model.TimeFavorito;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeFavoritoService {
    private List<TimeFavorito> timesFavoritos;
    private TimeService timeService;

    public TimeFavoritoService(TimeService timeService) {
        this.timeService = timeService;
        this.timesFavoritos = initTimeFavorito();
    }

    private List<TimeFavorito> initTimeFavorito() {
        List<TimeFavorito> timesFavoritos = new ArrayList<>();
        List<Long> ids = List.of(5L, 10L, 14L, 16L, 29L);
        List<String> apelidos = List.of("Time Maravilha", "Os Incríveis", "Os Invencíveis", "Os Campeões", "Os Líderes");
        List<List<String>> qualidades = List.of(
                List.of("Excelente defesa", "Ótimo trabalho em equipe"),
                List.of("Ataque poderoso", "Grande espírito de equipe"),
                List.of("Jogadores talentosos", "Estratégia impecável"),
                List.of("Grande técnico", "Jogadores dedicados"),
                List.of("Ótima formação", "Excelente condicionamento físico")
        );

        for (int i = 0; i < ids.size(); i++) {
            Long id = ids.get(i);
            Time time = timeService.getById(id);
            TimeFavorito timeFavorito = new TimeFavorito();
            timeFavorito.setId(id);
            timeFavorito.setApelido(apelidos.get(i));
            timeFavorito.setQualidadesApreciadas(qualidades.get(i));
            timeFavorito.setTime(time);
            timesFavoritos.add(timeFavorito);
        }
        return timesFavoritos;
    }

    public List<TimeFavorito> getAll() {
        if (timesFavoritos.isEmpty()) {
            throw new TimeNotFoundException("Nenhum time encontrado");
        }
        return timesFavoritos;
    }

    public TimeFavorito getById(long id) {
        return timesFavoritos.stream()
                .filter(timeFavorito -> timeFavorito.getId() == id)
                .findFirst()
                .orElseThrow(() -> new TimeNotFoundException("TimeFavorito com id " + id + " não encontrado"));
    }

    public List<TimeFavorito> getByName(String name) {
        List<TimeFavorito> result = timesFavoritos.stream()
                .filter(timeFavorito -> timeFavorito.getTime().getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
        if (result.isEmpty()) {
            throw new TimeNotFoundException("Nenhum TimeFavorito encontrado com o nome " + name);
        }
        return result;
    }

    public List<TimeFavorito> getByConference(String conference) {
        List<TimeFavorito> result = timesFavoritos.stream()
                .filter(timeFavorito -> timeFavorito.getTime().getConference().toLowerCase().contains(conference.toLowerCase()))
                .collect(Collectors.toList());
        if (result.isEmpty()) {
            throw new TimeNotFoundException("Nenhum TimeFavorito encontrado na conferência " + conference);
        }
        return result;
    }

    public TimeFavorito create(TimeFavorito timeFavorito) {
        timesFavoritos.add(timeFavorito);
        return timeFavorito;
    }

    public TimeFavorito update(long id, TimeFavorito timeFavorito) {
        TimeFavorito existingTimeFavorito = getById(id);
        if (existingTimeFavorito == null) {
            throw new TimeNotFoundException("TimeFavorito com id " + id + " não encontrado");
        }
        existingTimeFavorito.setApelido(timeFavorito.getApelido());
        existingTimeFavorito.setQualidadesApreciadas(timeFavorito.getQualidadesApreciadas());
        existingTimeFavorito.setTime(timeFavorito.getTime());
        return existingTimeFavorito;
    }

    public void delete(long id) {
        TimeFavorito timeFavorito = getById(id);
        if (timeFavorito == null) {
            throw new TimeNotFoundException("TimeFavorito com id " + id + " não encontrado");
        }
        timesFavoritos.remove(timeFavorito);
    }

}
