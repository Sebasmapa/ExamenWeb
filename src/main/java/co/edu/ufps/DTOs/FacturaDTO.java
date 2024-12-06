package co.edu.ufps.DTOs;

import lombok.Data;

@Data
class FacturaDTO {
    private String numero;
    private String total;
    private String fecha;
    
    public FacturaDTO(String numero, String total, String fecha) {
        this.numero = numero;
        this.total = total;
        this.fecha = fecha;
    }
}
