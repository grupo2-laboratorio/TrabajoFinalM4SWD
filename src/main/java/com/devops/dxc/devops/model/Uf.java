package com.devops.dxc.devops.model;

import java.io.Serializable;

public class Uf implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2988002029080131424L;

    private int uf;

    public Uf() {
    }

    public int getUf() {
        return Util.getUf();
    }

}
