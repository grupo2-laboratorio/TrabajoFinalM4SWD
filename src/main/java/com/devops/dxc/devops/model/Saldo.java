package com.devops.dxc.devops.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class Saldo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2988002029080131424L;

    private int saldo;
    @JsonIgnore
    private int sueldo;
    @JsonIgnore
    private int ahorro;

    public Saldo(int ahorro, int sueldo) {
        this.ahorro = ahorro;
        this.sueldo = sueldo;
    }

    public Saldo() {
    }

    public int getSaldo() {
        return (ahorro - Util.getDxc(ahorro, sueldo));
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

}
