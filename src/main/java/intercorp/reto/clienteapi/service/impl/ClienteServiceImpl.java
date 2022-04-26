package intercorp.reto.clienteapi.service.impl;

import intercorp.reto.clienteapi.model.Cliente;
import intercorp.reto.clienteapi.model.EsperanzaVida;
import intercorp.reto.clienteapi.model.KpiCliente;
import intercorp.reto.clienteapi.repository.ClienteRepository;
import intercorp.reto.clienteapi.repository.EsperanzaVidaRepository;
import intercorp.reto.clienteapi.service.ClienteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EsperanzaVidaRepository esperanzaVidaRepository;

    @Override
    public Cliente crearCliente(Cliente cliente) {
        cliente.setFechaProbableMuerte(getFechaProbableMuerte(cliente.getFechaNacimiento()));
        return clienteRepository.save(cliente);
    }

    @Override
    public KpiCliente getKpiClientes() {
        //Promedio de edades
        List<Cliente> clientes = clienteRepository.findAll();
        Integer suma = clientes.stream()
                .mapToInt(x -> x.getEdad())
                .sum();
        Double promedio = Double.valueOf(suma) / clientes.size();

        //Desviacion estandar
        Double desviacion = clientes.stream()
                .map(x -> Double.valueOf(x.getEdad()))
                .reduce(0.0, (a, b) -> a + Math.pow(b - promedio, 2));
        desviacion = Math.sqrt(desviacion / clientes.size());

        return new KpiCliente(promedio, desviacion);
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> clientes = clienteRepository.findAll();

        return clientes.stream().map(c -> {
            if(c.getFechaProbableMuerte() == null)
                c.setFechaProbableMuerte(getFechaProbableMuerte(c.getFechaNacimiento()));

            return c;
        }).collect(Collectors.toList());
    }

    private String getFechaProbableMuerte(String fechaNacimientoStr) {
        String fechaProbableMuerte = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr, formatter);
        Integer anioNacimiento = fechaNacimiento.getYear();

        List<EsperanzaVida> esperanzaVidas = esperanzaVidaRepository.findAll();

        EsperanzaVida esperanzaVida = esperanzaVidas.stream()
                .filter(e -> anioNacimiento.equals(e.getAnioNacimiento()))
                .findAny()
                .orElse(null);

        if (esperanzaVida != null) {
            Integer anioMuerte = anioNacimiento + esperanzaVida.getEsperanzaVida().intValue();
            LocalDate fechaMuerte = LocalDate.of(anioMuerte, fechaNacimiento.getMonth(), fechaNacimiento.getDayOfMonth());
            fechaProbableMuerte = fechaMuerte.format(formatter);
        }

        return fechaProbableMuerte;
    }

}
