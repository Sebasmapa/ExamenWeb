package co.edu.ufps.services;

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
import co.edu.ufps.repositories.CompraRepository;
import co.edu.ufps.repositories.DetallesCompraRepository;
import co.edu.ufps.repositories.ProductoRepository;
import co.edu.ufps.repositories.TiendaRepository;
import co.edu.ufps.repositories.VendedorRepository;
import jakarta.transaction.Transactional;


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
    private DetallesCompraRepository detallesCompraRepository;

    @Autowired
    private TiendaRepository tiendaRepository;

    @Autowired
    private ClienteService clienteService;

    @Transactional
    public Compra procesarCompra(String tiendaUuid, CompraDTO compraDTO) {
    	System.out.println("cliente"+ compraDTO.getCliente());
        // Validar datos básicos del cliente
        if (compraDTO.getCliente() == null) {
            throw new IllegalArgumentException("El campo 'cliente' no puede ser nulo.");
        }

        String doc = compraDTO.getCliente().getDocumento();
        String tipoDoc = compraDTO.getCliente().getTipoDocumento();
        if (doc == null || tipoDoc == null) {
            throw new IllegalArgumentException("Los campos 'documento' y 'tipo_documento' del cliente son obligatorios.");
        }

        // Buscar la tienda
        Tienda tienda = tiendaRepository.findByUuid(tiendaUuid)
                .orElseThrow(() -> new IllegalArgumentException("Tienda no encontrada con UUID: " + tiendaUuid));

        // Inicializar compra
        Compra compra = new Compra();
        compra.setTienda(tienda);
        compra.setFecha(java.time.LocalDate.now());

        // Manejo del cliente: buscar o crears
        Cliente cliente = clienteService.buscarOCrearCliente(
                compraDTO.getCliente().getDocumento(),
                compraDTO.getCliente().getTipoDocumento(),
                compraDTO.getCliente()
        );
        compra.setCliente(cliente);

        // Validar y obtener vendedor
        Vendedor vendedor = vendedorRepository.findByDocumento(compraDTO.getVendedor().getDocumento())
                .orElseThrow(() -> new IllegalArgumentException("Vendedor no encontrado con documento: " + compraDTO.getVendedor().getDocumento()));
        compra.setVendedor(vendedor);

        // Validar y obtener cajero
        Cajero cajero = cajeroRepository.findByToken(compraDTO.getCajero().getToken())
                .orElseThrow(() -> new IllegalArgumentException("Cajero no encontrado con token: " + compraDTO.getCajero().getToken()));
        compra.setCajero(cajero);

        // Procesar productos y calcular el total
        double total = procesarProductos(compraDTO, compra);

        // Calcular impuestos
        double impuestos = total * (compraDTO.getImpuesto() / 100.0);
        compra.setImpuestos(impuestos);

        // Actualizar el total
        compra.setTotal(total + impuestos);

        // Guardar la compra
        return compraRepository.save(compra);
    }

    private double procesarProductos(CompraDTO compraDTO, Compra compra) {
        double total = 0.0;

        for (ProductoDTO productoDTO : compraDTO.getProductos()) {
            Producto producto = productoRepository.findByReferencia(productoDTO.getReferencia());
            if (producto == null) {
                throw new IllegalArgumentException("Producto no encontrado con referencia: " + productoDTO.getReferencia());
            }

            // Validar cantidades
            if (productoDTO.getCantidad() > producto.getCantidad()) {
                throw new IllegalArgumentException("Cantidad solicitada supera el inventario disponible para el producto: " + productoDTO.getReferencia());
            }

            // Crear detalle de compra
            double precioFinal = producto.getPrecio() * (1 - productoDTO.getDescuento() / 100.0);
            double subtotal = precioFinal * productoDTO.getCantidad();
            total += subtotal;

            DetallesCompra detalle = new DetallesCompra();
            detalle.setCompra(compra);
            detalle.setProducto(producto);
            detalle.setCantidad(productoDTO.getCantidad());
            detalle.setPrecio(precioFinal);
            detalle.setDescuento(productoDTO.getDescuento());
            detallesCompraRepository.save(detalle);

            // Actualizar inventario del producto
            producto.setCantidad(producto.getCantidad() - productoDTO.getCantidad());
            productoRepository.save(producto);
        }

        return total;
    }
}
