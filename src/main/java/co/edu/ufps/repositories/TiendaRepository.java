package co.edu.ufps.repositories;

import co.edu.ufps.entities.Tienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TiendaRepository extends JpaRepository<Tienda, Integer> {

    Optional<Tienda> findByUuid(String uuid);  // Método para buscar tienda por UUID como String
}