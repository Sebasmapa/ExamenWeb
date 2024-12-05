package co.edu.ufps.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClienteDTO {
	private String documento;
    private String nombre;

    @JsonProperty("tipo_documento")
    private String tipoDocumento;
}

