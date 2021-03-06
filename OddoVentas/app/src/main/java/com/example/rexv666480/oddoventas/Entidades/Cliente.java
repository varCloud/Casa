package com.example.rexv666480.oddoventas.Entidades;


import java.util.Map;

/**
 * Created by rexv666480 on 07/05/2018.
 */

public class Cliente {

    private int id;
    private String display_name;
    private String email;
    private String city;
    private String phone;
    private String street;
    private String image_small;
    private Object[] country_id;

    public Cliente() {
         id= 0;
         display_name="";
         email="";
         city="";
         phone="";
         street="";
         image_small="";
        country_id = new Object[1];

    }

    public Cliente(int id, String display_name) {
        this.id = id;
        this.display_name = display_name;
    }

    public Cliente(int id, String display_name, String email, String city, String phone, String street, String image_small, Object[] country_id) {
        this.id = id;
        this.display_name = display_name;
        this.email = email;
        this.city = city;
        this.phone = phone;
        this.street = street;
        this.image_small = image_small;
        this.country_id = country_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getImage_small() {
        return image_small;
    }

    public void setImage_small(String image_small) {
        this.image_small = image_small;
    }

    public Object[] getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Object[] country_id) {
        this.country_id = country_id;
    }

    @Override
    public String toString()
    {
        return display_name;
    }
}
