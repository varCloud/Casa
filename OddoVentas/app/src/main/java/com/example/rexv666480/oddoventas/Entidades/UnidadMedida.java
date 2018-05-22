package com.example.rexv666480.oddoventas.Entidades;

/**
 * Created by rexv666480 on 21/05/2018.
 */

public class UnidadMedida {

    private String display_name;
    private Integer id;
    private Object[]  category_id;
    private String name;

    public UnidadMedida(String display_name, Integer id, Object[] category_id, String name) {
        this.display_name = display_name;
        this.id = id;
        this.category_id = category_id;
        this.name = name;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object[] getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Object[] category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UnidadMedida() {
    }

    @Override
    public String toString()
    {
        return display_name;
    }
}
