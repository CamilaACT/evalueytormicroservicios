package com.udla.evaluaytor.businessdomain.evaluacion.dto;

import java.util.List;

import lombok.Data;

@Data
public class DetalleFormularioCreateUpdateDTO {
    private int cumplimiento;
    private String observacion;
    private Long estadoDetalleId; // Solo el ID del estado detalle
    private Long documentoId;
    private Long formularioId; // Solo el ID del formulario
}
