package com.udla.evaluaytor.businessdomain.evaluacion.dto;

import java.util.Date;

import lombok.Data;

@Data
public class FormularioDTO {
private Long id;

    private Date fecha;
    private String numero; 
    private int evaluacion;
    private EstadoFormularioDTO estadoFormularioDTO;
}
