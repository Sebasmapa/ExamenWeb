package co.edu.ufps.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.DTOs.ClienteDTO;
import co.edu.ufps.entities.Cliente;
import co.edu.ufps.entities.TipoDocumento;
import co.edu.ufps.repositories.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente buscarOCrearCliente(String documento, String tipoDocumento, ClienteDTO clienteRequest) {
        return clienteRepository.findByDocumentoAndTipoDocumento_Nombre(documento, tipoDocumento)
                .orElseGet(() -> {
                    Cliente nuevoCliente = new Cliente();
                    nuevoCliente.setDocumento(documento);
                    nuevoCliente.setNombre(clienteRequest.getNombre());
                    TipoDocumento tipoDoc = new TipoDocumento();
                    tipoDoc.setNombre(tipoDocumento);
                    nuevoCliente.setTipoDocumento(tipoDoc);

                    return clienteRepository.save(nuevoCliente);
                });
    }
    

}
