package com.example.android.models;

public class User {
    private String nome;
    private String sobrenome;
    private String sexo;
    private String horaAcorda;
    private String horaDorme;
    private String userId;


    public User(String nome, String sobrenome, String sexo, String horaAcorda, String horaDorme) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.sexo = sexo;
        this.horaAcorda = horaAcorda;
        this.horaDorme = horaDorme;
    }

    public User(){}

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getSexo() {
        return sexo;
    }

    public String getHoraAcorda() {
        return horaAcorda;
    }

    public String getHoraDorme() {
        return horaDorme;
    }

    public String getUserId() {return userId; }

    public String returnAndSaveId(String id){
        this.userId = id;
        return this.userId;
    }
}
