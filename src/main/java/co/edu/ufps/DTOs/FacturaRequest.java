package co.edu.ufps.DTOs;

import java.util.List;

import lombok.Data;

@Data
public class FacturaRequest {
    private int impuesto;
    private ClienteDto cliente;
    private List<ProductoDto> productos;
    private List<MedioPagoDto> mediosPago;
    private VendedorDto vendedor;
    private CajeroDto cajero;

    @Data
    public static class ClienteDto {
        private String documento;
        private String nombre;
        private String tipoDocumento;
    }

    @Data
    public static class ProductoDto {
        private String referencia;
        private int cantidad;
        private double descuento;
    }

    @Data
    public static class MedioPagoDto {
        private String tipoPago;
        private String tipoTarjeta;
        private int cuotas;
        private double valor;
    }

    @Data
    public static class VendedorDto {
        private String documento;
    }

    @Data
    public static class CajeroDto {
        private String token;
    }
}
