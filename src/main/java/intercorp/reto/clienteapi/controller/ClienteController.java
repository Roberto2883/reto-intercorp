package intercorp.reto.clienteapi.controller;

import intercorp.reto.clienteapi.model.Cliente;
import intercorp.reto.clienteapi.model.KpiCliente;
import intercorp.reto.clienteapi.service.ClienteService;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/clientes")
@Slf4j
@AllArgsConstructor
public class ClienteController {

    private ClienteService clienteService;

    @ResponseStatus(HttpStatus.OK)

    @ApiOperation(value = "Registra un cliente",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = Cliente.class,
            httpMethod = "POST")
    @PostMapping("/creacliente")
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteService.crearCliente(cliente);
    }


    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Obtiene kpis de los clientes",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = KpiCliente.class,
            httpMethod = "GET")
    @GetMapping("/kpideclientes")
    public KpiCliente getKpiClientes() {
        return clienteService.getKpiClientes();
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Obtiene el listado de clientes",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = Cliente.class,
            httpMethod = "GET")
    @GetMapping("/listclientes")
    public List<Cliente> getAllClientes() {
        return clienteService.findAll();
    }

}
