package com.example.quiz.enums;

public enum Constants {
    FIRST_LOGIN("FIRST_LOGIN");

    private String valor;
    Constants(String first_login) {
        this.valor = first_login;
    }

    public String getValor() {
        return valor;
    }
}
