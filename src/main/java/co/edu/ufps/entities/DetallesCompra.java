package co.edu.ufps.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "detalles_compra")
public class DetallesCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "compra_id", nullable = false)
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "valor_total", nullable = false)
    private Double valorTotal;
}
