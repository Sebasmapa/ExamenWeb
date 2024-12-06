package co.edu.ufps.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.DTOs.CompraDTO;
import co.edu.ufps.services.CompraService;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @PostMapping("/{tiendaUuid}")
    public String crearCompra(@PathVariable String tiendaUuid, @RequestBody CompraDTO compraRequest) {
        // Procesar la compra con el UUID de la tienda
        CompraDTO compraDTO = new CompraDTO();
        compraDTO.setCliente(compraRequest.getCliente());
        compraDTO.setVendedor(compraRequest.getVendedor());
        compraDTO.setCajero(compraRequest.getCajero());
        compraDTO.setImpuesto(compraRequest.getImpuesto());
        compraDTO.setProductos(compraRequest.getProductos());

        // Llamar al servicio para procesar la compra
        compraService.procesarCompra(tiendaUuid, compraDTO);

        return "Compra creada exitosamente";
    }
}