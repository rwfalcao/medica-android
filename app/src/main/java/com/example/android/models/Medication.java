package com.example.android.models;

public class Medication {
    private String nome;
    private String pAtivo;
    private String lab;
    private String desc;
    private String tClass;
    private String resctric;
    private double preco;

    public Medication(String nome, String pAtivo, String lab, String desc, String tClass, String resctric, double preco) {
        this.nome = nome;
        this.pAtivo = pAtivo;
        this.lab = lab;
        this.desc = desc;
        this.tClass = tClass;
        this.resctric = resctric;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public String getpAtivo() {
        return pAtivo;
    }

    public String getLab() {
        return lab;
    }

    public String getDesc() {
        return desc;
    }

    public String gettClass() {
        return tClass;
    }

    public String getResctric() {
        return resctric;
    }

    public double getPreco() {
        return preco;
    }
}
