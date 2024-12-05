package co.edu.ufps.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MedioPagoDTO {

	@JsonProperty("tipo_pago")
    private String tipoPago;

    @JsonProperty("tipo_tarjeta")
    private String tipoTarjeta;

    private Integer cuotas;
    private double valor;
}