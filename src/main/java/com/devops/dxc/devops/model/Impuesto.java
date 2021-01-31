package com.devops.dxc.devops.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class Impuesto implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2988002029080131424L;


    private int impuesto;
    @JsonIgnore
    private int sueldo;
    @JsonIgnore
    private int ahorro;

    public Impuesto(int ahorro, int sueldo) {
        this.ahorro = ahorro;
        this.sueldo = sueldo;
    }

    public Impuesto() {
    }


    public int getImpuesto() {
        return Util.getImpuesto(sueldo, ahorro);
    }

    public void setImpuesto(int impuesto) {
        this.impuesto = impuesto;
    }

}
