package com.teams.favoriteteamsnba.error;

import lombok.Data;

@Data
public class Erro {
    private String mensagem;

    public Erro(String mensagem) {
        this.mensagem = mensagem;
    }
}
