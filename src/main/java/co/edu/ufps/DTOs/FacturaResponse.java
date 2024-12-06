package co.edu.ufps.DTOs;

import lombok.Data;

@Data
public class FacturaResponse {
    private String status;
    private String message;
    private FacturaDTO data;

    // Constructor, getters y setters

    public FacturaResponse(String status, String message, FacturaDTO data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

}



