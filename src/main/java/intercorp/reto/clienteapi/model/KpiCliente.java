package intercorp.reto.clienteapi.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class KpiCliente {

    @ApiModelProperty(
            name = "promedioEdadClientes",
            value = "Promedio de edad de los clientes",
            example = "10",
            dataType = "double")
    private Double promedioEdadClientes;

    @ApiModelProperty(
            name = "desviacionEdadClientes",
            value = "Desviacion estandar de la edad de los cliente",
            example = "1.0",
            dataType = "string")
    private Double desviacionEdadClientes;
}
