package co.edu.ufps.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;


@Data
public class MedioPagoDTO {

    @JsonProperty("tipo_pago")
    @Schema(description = "El tipo de pago (ejemplo: TARJETA CREDITO, TARJETA DEBITO, BITCOIN)", required = true)
    private String tipoPago;

    @JsonProperty("tipo_tarjeta")
    @Schema(description = "El tipo de tarjeta (ejemplo: VISA, MASTERCARD). Puede ser nulo si el tipo de pago no es una tarjeta.", required = false)
    private String tipoTarjeta;

    @JsonProperty("cuotas")
    @Schema(description = "Cantidad de cuotas para pagos con tarjeta. Se inicializa en 0 si no aplica.", example = "0", required = true)
    private int cuotas = 0; // Inicializado en 0 por defecto

    @JsonProperty("valor")
    @Schema(description = "El valor del medio de pago", required = true)
    private double valor;
}
