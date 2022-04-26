package intercorp.reto.clienteapi.service;

import intercorp.reto.clienteapi.model.Cliente;
import intercorp.reto.clienteapi.model.KpiCliente;

import java.util.List;

public interface ClienteService {
    Cliente crearCliente(Cliente cliente);
    KpiCliente getKpiClientes();
    List<Cliente> findAll();
}
