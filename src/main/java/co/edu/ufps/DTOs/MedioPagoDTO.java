package co.edu.ufps.DTOs;

import lombok.Data;

@Data
public class MedioPagoDTO {

    private String tipo_pago;
    private String tipo_tarjeta;
    private int cuotas;
    private double valor;
}
