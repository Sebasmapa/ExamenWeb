package co.edu.ufps.DTOs;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CompraDTO {
	private double impuesto;

    @JsonProperty("cliente")
    private ClienteDTO cliente;

    @JsonProperty("productos")
    private List<ProductoDTO> productos;

    @JsonProperty("medios_pago")
    private List<MedioPagoDTO> mediosPago;

    @JsonProperty("vendedorDocumento")
    private String vendedorDocumento;

    @JsonProperty("cajeroToken")
    private String cajeroToken;
    
    public VendedorDTO getVendedor() {
        VendedorDTO vendedor = new VendedorDTO();
        vendedor.setDocumento(vendedorDocumento);
        return vendedor;
    }

    public CajeroDTO getCajero() {
        CajeroDTO cajero = new CajeroDTO();
        cajero.setToken(cajeroToken);
        return cajero;
    }
}

