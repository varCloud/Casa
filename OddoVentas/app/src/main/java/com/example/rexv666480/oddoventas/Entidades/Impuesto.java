package com.example.rexv666480.oddoventas.Entidades;

/**
 * Created by rexv666480 on 21/05/2018.
 */

public class Impuesto {
    private  Integer id;
    private String display_name;
    private String description;
    private Double amount;
    private String amount_type;

    public Impuesto() {
    }

    public Impuesto(Integer id, String display_name, String description, Double amount, String amount_type) {
        this.id = id;
        this.display_name = display_name;
        this.description = description;
        this.amount = amount;
        this.amount_type = amount_type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getAmount_type() {
        return amount_type;
    }

    public void setAmount_type(String amount_type) {
        this.amount_type = amount_type;
    }

    @Override
    public String toString()
    {
        return display_name;
    }
}
