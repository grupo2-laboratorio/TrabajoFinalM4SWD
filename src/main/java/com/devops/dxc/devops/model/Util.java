package com.devops.dxc.devops.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

    /**
     * Método para cacular el 10% del ahorro en la AFP.  Las reglas de negocio se pueden conocer en
     * https://www.previsionsocial.gob.cl/sps/preguntas-frecuentes-nuevo-retiro-seguro-10/
     *
     * @param ahorro
     * @return
     */
    public static int getDxc(int ahorro) {
        int uf = getUf();
        int diez = (int) Math.round(ahorro * 0.1);

        if (diez > (150 * uf)) {
            return (150 * uf);
        } else if (diez <= (150 * uf) && diez >= (35 * uf)) {
            return diez;
        } else if (ahorro >= (35 * uf) && diez <= (35 * uf)) {
            return (35 * uf);
        } else {
            return ahorro;
        }
    }

    /**
     * Método que retorna el valor de la UF.  Este método debe ser refactorizado por una integración a un servicio
     * que retorne la UF en tiempo real.  Por ejemplo mindicador.cl
     *
     * @return
     */
    public static int getUf() {

        LocalDate localDate = LocalDate.now();
        String fecha = String.format("%02d", localDate.getDayOfMonth()) + "-" + String.format("%02d", localDate.getMonthValue()) + "-" + localDate.getYear();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UfResponse> ufEntity = restTemplate.getForEntity("https://mindicador.cl/api/uf/" + fecha, UfResponse.class);
        return (int) Math.round(Double.parseDouble(ufEntity.getBody().getSerie().get(0).toDomain().getValor()));

    }


    /**
     * Método que obtiene el impuesto asociado al retiro del 10%
     * tomando en consideracion el salario de la persona que hace el retiro
     *
     * @param sueldo
     * @param ahorro
     * @return
     */
    public static int getImpuesto(int sueldo, int ahorro) {
        int retiro = getDxc(ahorro);
        if (sueldo <= 1500000) {
            return (retiro * 0);
        } else if (sueldo > 1500000 && sueldo <= 2500000) {
            return (int) Math.round(retiro * 0.0452);
        } else if (sueldo > 2500000 && sueldo <= 3000000) {
            return (int) Math.round(retiro * 0.0709);
        } else if (sueldo > 3000000 && sueldo <= 4000000) {
            return (int) Math.round(retiro * 0.1062);
        } else if (sueldo > 4000000 && sueldo <= 6000000) {
            return (int) Math.round(retiro * 0.1557);
        } else {
            return (int) Math.round(retiro * 0.2748);
        }
    }

    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UfResponse {

        private List<SerieModel> serie;

        public List<Serie> toDomain() {
            return serie.stream()
                    .map(SerieModel::toDomain)
                    .collect(Collectors.toList());
        }
    }

    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SerieModel {

        private String fecha;
        private String valor;

        Serie toDomain() {
            return Serie.builder()
                    .fecha(this.getFecha())
                    .valor(this.getValor())
                    .build();
        }
    }

}
