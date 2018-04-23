package com.example.android.models;

import com.google.gson.annotations.SerializedName;

public class Medication {
    @SerializedName("produto")
    private String nome;

    @SerializedName("principioAtivo")
    private String pAtivo;

    @SerializedName("laboratorio")
    private String lab;

    @SerializedName("apresentacao")
    private String desc;

    @SerializedName("classeTerapeutica")
    private String tClass;

    @SerializedName("restricao")
    private String resctric;

    @SerializedName("pmc0")
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
