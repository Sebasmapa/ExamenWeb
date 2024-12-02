package co.edu.ufps.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pago")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "compra_id", nullable = false)
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "tipo_pago_id", nullable = false)
    private TipoPago tipoPago;

    @Column(name = "tipo_tarjeta") // Ejemplo: débito o crédito
    private String tipoTarjeta;

    @Column(name = "numero_cuotas") // Número de cuotas en caso de crédito
    private Integer numeroCuotas;

    @Column(name = "monto", nullable = false)
    private Double monto;
}
