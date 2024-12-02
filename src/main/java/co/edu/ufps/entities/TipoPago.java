package co.edu.ufps.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "tipo_pago")
public class TipoPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "tipoPago", cascade = CascadeType.ALL)
    private List<Pago> pagos;
}
