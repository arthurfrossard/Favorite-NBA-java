package com.teams.favoriteteamsnba.model;

import lombok.Data;

@Data
public class Time {
    private long id;
    private String abbreviation;
    private String city;
    private String conference;
    private String division;
    private String full_name;
    private String name;
}
