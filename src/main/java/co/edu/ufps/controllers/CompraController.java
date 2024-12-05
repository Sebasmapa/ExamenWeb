package co.edu.ufps.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.DTOs.CompraDTO;
import co.edu.ufps.entities.Compra;
import co.edu.ufps.services.CompraService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @PostMapping("/{tiendaUuid}")
    public ResponseEntity<?> registrarCompra(
            @PathVariable String tiendaUuid,
            @RequestBody CompraDTO compraDTO) {
        try {
            // Procesar la compra utilizando el service2
            Compra compra = compraService.procesarCompra(tiendaUuid, compraDTO);

            // Crear respuesta exitosa
            return ResponseEntity.status(HttpStatus.CREATED).body(compra);

        } catch (IllegalArgumentException e) {
            // Manejar errores de validación y negocio
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of("status", "error", "message", e.getMessage())
            );

        } catch (Exception e) {
            // Manejar cualquier otro error inesperado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    Map.of("status", "error", "message", "Error inesperado: " + e.getMessage())
            );
        }
    }
}