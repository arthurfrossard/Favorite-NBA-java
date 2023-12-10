package com.teams.favoriteteamsnba.model;

import com.teams.favoriteteamsnba.model.Time;
import lombok.Data;

import java.util.List;

@Data
public class TimeFavorito {
    private long id;
    private String apelido;
    private List<String> qualidadesApreciadas;
    private Time time;
}
