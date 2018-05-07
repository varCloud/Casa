package com.example.rexv666480.oddoventas.Entidades;


import java.util.Map;

/**
 * Created by rexv666480 on 07/05/2018.
 */

public class Cliente {

    private String id;
    private String name;
    private Map<String,String> country_id;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getCountry_id() {
        return country_id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry_id(Map<String, String> country_id) {
        this.country_id = country_id;
    }
}
