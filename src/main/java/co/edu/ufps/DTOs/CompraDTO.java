package co.edu.ufps.DTOs;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CompraDTO {
    private double impuesto;

    private ClienteDTO cliente;

    @JsonProperty("productos")
    private List<ProductoDTO> productos;

    @JsonProperty("medios_pago")
    private List<MedioPagoDTO> mediosPago;

    @JsonProperty("vendedor")
    private VendedorDTO vendedor;

    @JsonProperty("cajero")
    private CajeroDTO cajero;
}

