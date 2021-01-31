package com.devops.dxc.devops.rest;

import com.devops.dxc.devops.model.Dxc;
import com.devops.dxc.devops.model.Impuesto;
import com.devops.dxc.devops.model.Saldo;
import com.devops.dxc.devops.model.Uf;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/rest/msdxc")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class RestData {

    private final static Logger LOGGER = Logger.getLogger("devops.subnivel.Control");

    @GetMapping(path = "/dxc", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Dxc getDxc(@RequestParam(name = "sueldo") String sueldo, @RequestParam(name = "ahorro") String ahorro) {

        LOGGER.log(Level.INFO, "< Trabajo DevOps - DXC > <Consultado Diez por ciento>");

        Dxc response = new Dxc(Integer.parseInt(ahorro), Integer.parseInt(sueldo));
        return response;
    }

    @GetMapping(path = "/saldo", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Saldo getSaldo(@RequestParam(name = "sueldo") String sueldo, @RequestParam(name = "ahorro") String ahorro) {

        LOGGER.log(Level.INFO, "< Trabajo DevOps - SALDO > <Consultado Diez por ciento>");

        Saldo response = new Saldo(Integer.parseInt(ahorro), Integer.parseInt(sueldo));
        return response;
    }

    @GetMapping(path = "/impuesto", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Impuesto getImpuesto(@RequestParam(name = "sueldo") String sueldo, @RequestParam(name = "ahorro") String ahorro) {

        LOGGER.log(Level.INFO, "< Trabajo DevOps - IMPUESTO > <Consultado Diez por ciento>");

        Impuesto response = new Impuesto(Integer.parseInt(ahorro), Integer.parseInt(sueldo));
        return response;
    }


    @GetMapping(path = "/uf", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    int getUF() {

        LOGGER.log(Level.INFO, "< Trabajo DevOps - UF > <Consultado Diez por ciento>");

        return new Uf().getUf();
    }
}