package co.edu.ufps.DTOs;

import lombok.Data;

@Data
public class CompraResponseDTO {
    private String status;
    private String message;
    private DataDTO data;

    @Data
    public static class DataDTO {
        private String numero;
        private String total;
        private String fecha;
    }
}