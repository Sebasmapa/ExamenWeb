package co.edu.ufps.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.DTOs.ClienteDTO;
import co.edu.ufps.entities.Cliente;
import co.edu.ufps.services.ClienteService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/clientes") 
public class ClienteController {

    @Autowired
    private ClienteService clienteService; 

    @PostMapping("/buscar-o-crear")
    public ResponseEntity<Cliente> buscarOCrearCliente(
            @RequestParam("documento") String documento,
            @RequestParam("tipoDocumento") String tipoDocumento,
            @RequestBody ClienteDTO clienteDTO) {
        try {
            // Buscar o crear el cliente utilizando el servicio inyectado
            Cliente cliente = clienteService.buscarOCrearCliente(documento, tipoDocumento, clienteDTO); // Aquí usamos la instancia inyectada
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
