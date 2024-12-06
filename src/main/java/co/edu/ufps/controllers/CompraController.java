package co.edu.ufps.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.DTOs.CompraDTO;
import co.edu.ufps.DTOs.CompraResponseDTO;
import co.edu.ufps.entities.Compra;
import co.edu.ufps.services.CompraService;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @PostMapping("/{tiendaUuid}")
    public CompraResponseDTO crearCompra(@PathVariable String tiendaUuid, @RequestBody CompraDTO compraRequest) {
        // Procesar la compra y obtener la entidad persistida
        Compra compra = compraService.procesarCompra(tiendaUuid, compraRequest);

        // Construir la respuesta
        CompraResponseDTO response = new CompraResponseDTO();
        response.setStatus("success");
        response.setMessage("La factura se ha creado correctamente con el número: " + compra.getId());

        CompraResponseDTO.DataDTO data = new CompraResponseDTO.DataDTO();
        data.setNumero(compra.getId().toString());
        data.setTotal(String.valueOf(compra.getTotal()));
        data.setFecha(compra.getFecha().toString());

        response.setData(data);

        return response;
    }
}
