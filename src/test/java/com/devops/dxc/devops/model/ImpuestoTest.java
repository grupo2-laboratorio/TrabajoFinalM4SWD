package com.devops.dxc.devops.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ImpuestoTest {

    @Test
    @DisplayName("Get /impuesto success")
    void testImpuesto1() {

        Dxc dxc = new Dxc(10000000, 1500000);
        int dxcResponse = dxc.getImpuesto();

        assertThat(dxcResponse).isEqualTo(0);
    }

    @Test
    @DisplayName("Get /impuesto success")
    void testImpuesto2() {

        Dxc dxc = new Dxc(10000000, 2000000);
        int dxcResponse = dxc.getImpuesto();

        assertThat(dxcResponse).isEqualTo((int) Math.round(dxc.getDxc() * 0.0452));
    }


    @Test
    @DisplayName("Get /impuesto success")
    void testImpuesto3() {

        Dxc dxc = new Dxc(10000000, 3000000);
        int dxcResponse = dxc.getImpuesto();

        assertThat(dxcResponse).isEqualTo((int) Math.round(dxc.getDxc() * 0.0709));
    }

    @Test
    @DisplayName("Get /impuesto success")
    void testImpuesto4() {

        Dxc dxc = new Dxc(10000000, 4000000);
        int dxcResponse = dxc.getImpuesto();

        assertThat(dxcResponse).isEqualTo((int) Math.round(dxc.getDxc() * 0.1062));
    }

    @Test
    @DisplayName("Get /impuesto success")
    void testImpuesto5() {

        Dxc dxc = new Dxc(10000000, 5000000);
        int dxcResponse = dxc.getImpuesto();

        assertThat(dxcResponse).isEqualTo((int) Math.round(dxc.getDxc() * 0.1557));
    }

    @Test
    @DisplayName("Get /impuesto success")
    void testImpuesto6() {

        Dxc dxc = new Dxc(10000000, 8000000);
        int dxcResponse = dxc.getImpuesto();

        assertThat(dxcResponse).isEqualTo((int) Math.round(dxc.getDxc() * 0.2748));
    }


}
