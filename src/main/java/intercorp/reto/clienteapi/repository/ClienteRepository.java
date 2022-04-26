package intercorp.reto.clienteapi.repository;

import intercorp.reto.clienteapi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
