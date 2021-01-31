package com.devops.dxc.devops.rest;

import com.devops.dxc.devops.model.Dxc;
import com.devops.dxc.devops.model.Uf;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RestDataTest {

    @Test
    @DisplayName("Get /dxc success")
    void testDxcSuccess() {

        //given
        Dxc dxcService = mock(Dxc.class);
        int dxcResponse = this.getDxcResponse();

        //when
        when(dxcService.getDxc())
                .thenReturn(dxcResponse);

        RestData restData = new RestData();

        var response = restData.getDxc("0", "8000000").getDxc();
        var expectedResponse = this.getDxcViewResponse();

        //THEN
        assertEquals(expectedResponse, response);
    }

    @Test
    @DisplayName("Get /saldo success")
    void testSaldoSuccess() {

        //given
        Dxc dxcService = mock(Dxc.class);
        int dxcResponse = this.getSaldoResponse();

        //when
        when(dxcService.getDxc())
                .thenReturn(dxcResponse);

        RestData restData = new RestData();

        var response = restData.getDxc("0", "15000000").getSaldo();
        var expectedResponse = this.getSaldoViewResponse();

        //THEN
        assertEquals(expectedResponse, response);
    }

    @Test
    @DisplayName("Get /impuesto success")
    void testImpuestoSuccess() {

        //given
        Dxc dxcService = mock(Dxc.class);
        int dxcResponse = this.getImpuestoResponse();

        //when
        when(dxcService.getDxc())
                .thenReturn(dxcResponse);

        RestData restData = new RestData();

        var response = restData.getDxc("2000000", "15000000").getImpuesto();
        var expectedResponse = this.getImpuestoViewResponse();

        //THEN
        assertEquals(expectedResponse, response);
    }

    @Test
    @DisplayName("Get /uf success")
    void testUfSuccess() {

        //given
        Uf ufService = mock(Uf.class);
        int ufResponse = this.getUfResponse();

        //when
        when(ufService.getUf())
                .thenReturn(ufResponse);

        var response = ufService.getUf();
        var expectedResponse = this.getUfResponse();

        //THEN
        assertEquals(expectedResponse, response);
    }

    public int getDxcViewResponse() {
        Uf uf = new Uf();
        return uf.getUf() * 35;
    }

    public int getSaldoViewResponse() {
        return 13500000;
    }

    public int getImpuestoViewResponse() {
        return 67800;
    }

    public int getDxcResponse() {
        return new Dxc(8000000, 0).getDxc();
    }

    public int getSaldoResponse() {
        return new Dxc(15000000, 0).getSaldo();
    }

    public int getImpuestoResponse() {
        return new Dxc(15000000, 2000000).getImpuesto();
    }

    public int getUfResponse() {
        return new Uf().getUf();
    }
}
