package co.edu.ufps.DTOs;

import java.util.List;

import lombok.Data;



@Data
public class FacturaDTO {

    private double impuesto;
    private ClienteDTO cliente;
    private List<ProductoDTO> productos;
    private List<MedioPagoDTO> medios_pago;
    private VendedorDTO vendedor;
    private CajeroDTO cajero;
}
