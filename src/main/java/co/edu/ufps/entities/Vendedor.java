package co.edu.ufps.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "vendedor")
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "documento")
    private String documento;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL)
    private List<Compra> compras;
}
