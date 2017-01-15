package com.example.nivaldogomesdasilvafilho.testemaps.util;

import java.io.Serializable;

public class Cidade implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nome;
    private String id;
    private String tempMax;
    private String tempMin;
    private String descTempo;

    public String getNome(){
        return nome;
    }

    public String getId(){
        return id;
    }

    public void setNome(String nome){
        if(!nome.isEmpty())
        this.nome = nome;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getDescTempo() {
        return descTempo;
    }

    public void setDescTempo(String descTempo) {
        this.descTempo = descTempo;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String toString(){
        return nome;
    }
}
