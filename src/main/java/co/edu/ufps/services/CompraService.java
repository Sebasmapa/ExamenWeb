package co.edu.ufps.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.DTOs.CompraDTO;
import co.edu.ufps.DTOs.ProductoDTO;
import co.edu.ufps.entities.Cajero;
import co.edu.ufps.entities.Cliente;
import co.edu.ufps.entities.Compra;
import co.edu.ufps.entities.DetallesCompra;
import co.edu.ufps.entities.Producto;
import co.edu.ufps.entities.Tienda;
import co.edu.ufps.entities.Vendedor;
import co.edu.ufps.repositories.CajeroRepository;
import co.edu.ufps.repositories.ClienteRepository;
import co.edu.ufps.repositories.CompraRepository;
import co.edu.ufps.repositories.DetallesCompraRepository;
import co.edu.ufps.repositories.ProductoRepository;
import co.edu.ufps.repositories.TiendaRepository;
import co.edu.ufps.repositories.VendedorRepository;


@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private CajeroRepository cajeroRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private DetallesCompraRepository detallesCompraRepository;

    @Autowired
    private TiendaRepository tiendaRepository;

    public Compra registrarCompra(UUID tiendaId, CompraDTO compraDTO) {
        // Convertir el UUID de la tienda a String antes de hacer la búsqueda
        String tiendaUuid = tiendaId.toString();

        // Obtener la tienda utilizando el UUID como String
        Optional<Tienda> optionalTienda = tiendaRepository.findByUuid(tiendaUuid);
        if (optionalTienda.isEmpty()) {
            return null; 
        }
        Tienda tienda = optionalTienda.get();

     // Obtener el cliente de manera condicional
        Cliente cliente = clienteRepository.findByDocumento(compraDTO.getCliente().getDocumento());
        if (cliente == null) {
            return null; 
        }
      

        // Obtener el vendedor de manera condicional
        Optional<Vendedor> optionalVendedor = vendedorRepository.findByDocumento(compraDTO.getVendedor().getDocumento());
        if (optionalVendedor.isEmpty()) {
            return null;  
        }
        Vendedor vendedor = optionalVendedor.get();

        // Obtener el cajero de manera condicional
        Optional<Cajero> optionalCajero = cajeroRepository.findByToken(compraDTO.getCajero().getToken());
        if (optionalCajero.isEmpty()) {
            return null; 
        }
        Cajero cajero = optionalCajero.get();

        // Crear nueva compra
        Compra compra = new Compra();
        compra.setCliente(cliente);
        compra.setVendedor(vendedor);
        compra.setCajero(cajero);
        compra.setTienda(tienda);  // Asociar la tienda a la compra
        compra.setFecha(java.time.LocalDate.now());
        compra.setImpuestos(compraDTO.getImpuesto());

        // Calcular el total de la compra y los detalles
        double totalCompra = 0.0;
        for (ProductoDTO productoDTO : compraDTO.getProductos()) {
            Producto producto = productoRepository.findByReferencia(productoDTO.getReferencia());
            if (producto == null) {
                return null;  
            }

            // Crear detalle de compra
            DetallesCompra detalle = new DetallesCompra();
            detalle.setProducto(producto);
            detalle.setCantidad(productoDTO.getCantidad());
            detalle.setPrecio(producto.getPrecio());
            detalle.setDescuento(productoDTO.getDescuento());

            // Calcular y asignar el valor total del detalle
            detalle.setValorTotalFromCalculation();
            totalCompra += detalle.getValorTotal();

            detalle.setCompra(compra);
            detallesCompraRepository.save(detalle);
        }

        // Establecemos el total de la compra
        compra.setTotal(totalCompra);

        // Guardamos la compra
        compraRepository.save(compra);

        return compra;
    }
}
