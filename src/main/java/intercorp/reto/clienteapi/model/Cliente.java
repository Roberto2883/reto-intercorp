package intercorp.reto.clienteapi.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(
            name = "nombre",
            value = "Nombre del cliente",
            example = "Roberto",
            dataType = "string")
    @Valid
    @NotNull(message = "Nombre es requerido")
    @NotEmpty(message = "El campo nombre no debe ser vacío")
    private String nombre;

    @ApiModelProperty(
            name = "apellido",
            value = "Apellido del cliente",
            example = "Contreras",
            dataType = "string")
    @NotNull(message = "Apellido es requerido")
    @NotEmpty(message = "El campo apellido no debe ser vacio")
    private String apellido;

    @ApiModelProperty(
            name = "edad",
            value = "Edad del cliente",
            example = "37",
            dataType = "integer")
    @NotNull(message = "Edad es requerido")
    private Integer edad;

    @ApiModelProperty(
            name = "fechaNacimiento",
            value = "Fecha de nacimiento del cliente en formato dd/MM/yyyy",
            example = "26/05/1984",
            dataType = "string")
    @Valid
    @NotNull(message = "FechaNacimiento es requerido")
    @Pattern(regexp = "^[0-3][0-9]/[0-3][0-9]/(?:[0-9][0-9])?[0-9][0-9]$",
            message= "Fecha debe corresponder al formato: dd/MM/yyyy")
    private String fechaNacimiento;

    @ApiModelProperty(
            name = "fechaProbableMuerte",
            value = "Fecha probable de muerte en base a la esperanza de vida",
            example = "31/12/2050",
            dataType = "string")
    private String fechaProbableMuerte;

}
