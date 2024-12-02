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

    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "descuento", nullable = false)
    private Double descuento;

    @Column(name = "valor_total", nullable = false)
    private Double valorTotal;

    // Método para calcular el valor total de la compra (sin persistir en base de datos)
    @Transient
    public Double calcularValorTotal() {
        return (precio * cantidad) * (1 - descuento / 100);
    }

    // Método para establecer el valor total desde el cálculo
    public void setValorTotalFromCalculation() {
        this.valorTotal = calcularValorTotal();
    }
}