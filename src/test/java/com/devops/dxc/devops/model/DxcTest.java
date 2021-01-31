package com.devops.dxc.devops.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DxcTest {

    int uf = new Uf().getUf();

    @Test
    @DisplayName("Get /dxc success")
    void testDxcTope() {

        Dxc dxc = new Dxc(70000000, 0);
        int dxcResponse = dxc.getDxc();

        assertThat(dxcResponse).isEqualTo(uf * 150);
    }

    @Test
    @DisplayName("Get /dxc success")
    void testDxcIntermedio() {

        Dxc dxc = new Dxc(30000000, 0);
        int dxcResponse = dxc.getDxc();

        assertThat(dxcResponse).isEqualTo(3000000);
    }

    @Test
    @DisplayName("Get /dxc success")
    void testDxcMinimo() {

        Dxc dxc = new Dxc(11000000, 0);
        int dxcResponse = dxc.getDxc();

        assertThat(dxcResponse).isEqualTo(1100000);
    }


    @Test
    @DisplayName("Get /dxc success")
    void testDxcAhorro() {

        Dxc dxc = new Dxc(800000, 0);
        int dxcResponse = dxc.getDxc();

        assertThat(dxcResponse).isEqualTo(800000);
    }

}
