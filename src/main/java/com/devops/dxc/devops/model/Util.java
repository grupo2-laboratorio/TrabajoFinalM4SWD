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
     * @param sueldo
     * @return
     */
    public static int getDxc(int ahorro, int sueldo) {
        int uf = getUf();
        if (((ahorro * 0.1) / uf) > 150) {
            return (int) (150 * uf);
        } else if ((ahorro * 0.1) <= (35 * uf) && ahorro >= (35 * uf)) {
            return (int) (35 * uf);
        } else if (ahorro <= 35 * uf) {
            return (int) ahorro;
        } else {
            return (int) (ahorro * 0.1);
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
        String fecha = localDate.getDayOfMonth() + "-" + localDate.getMonthValue() + "-" + localDate.getYear();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UfResponse> ufEntity = restTemplate.getForEntity("https://mindicador.cl/api/uf/" + fecha, UfResponse.class);
        return (int) Math.round(Double.parseDouble(ufEntity.getBody().getSerie().get(0).toDomain().getValor()));

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
