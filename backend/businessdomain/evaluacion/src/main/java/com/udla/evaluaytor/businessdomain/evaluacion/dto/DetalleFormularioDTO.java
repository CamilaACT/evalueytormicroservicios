package com.udla.evaluaytor.businessdomain.evaluacion.dto;

import java.util.List;

import lombok.Data;

@Data
public class DetalleFormularioDTO {
    private Long id;
    private int cumplimiento;
    private String observacion;
    private EstadoDetalleDTO estadoDetalleDTO;
    private DocumentoDTO documentoDTO;
    private FormularioDTO formularioDTO; // Nuevo campo para incluir el Formulario relacionado
}