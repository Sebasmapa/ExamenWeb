package co.edu.ufps.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FacturaResponse {
    private String status;
    private String message;
    private Integer numeroFactura;
    private Double total;
    private String fecha;
    
}
