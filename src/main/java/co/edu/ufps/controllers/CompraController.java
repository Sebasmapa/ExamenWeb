package co.edu.ufps.controllers;

import java.util.UUID;

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

    @PostMapping("/{tiendaId}")
    public ResponseEntity<Compra> registrarCompra(
            @PathVariable UUID tiendaId, 
            @RequestBody CompraDTO compraDTO) {
        
        Compra compra = compraService.registrarCompra(tiendaId, compraDTO);
        
        if (compra == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
        }
        
        return new ResponseEntity<>(compra, HttpStatus.CREATED);
    }
}
