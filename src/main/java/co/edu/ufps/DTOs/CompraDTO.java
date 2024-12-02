package co.edu.ufps.DTOs;

import java.util.List;

import lombok.Data;

@Data
public class CompraDTO {
    private double impuesto;
    private ClienteDTO cliente;
    private List<ProductoDTO> productos;
    private List<MedioPagoDTO> medios_Pago;
    private VendedorDTO vendedor;
    private CajeroDTO cajero;
    private String observaciones;
}
