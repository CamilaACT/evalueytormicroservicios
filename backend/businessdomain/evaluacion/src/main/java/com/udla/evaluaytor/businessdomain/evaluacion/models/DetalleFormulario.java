package com.udla.evaluaytor.businessdomain.evaluacion.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class DetalleFormulario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int cumplimiento;
    private String observacion; 

    @ManyToOne
    @JoinColumn(name = "id_estado_detalle")
    private EstadoDetalle estadoDetalle;

    @OneToOne
    @JoinColumn(name = "id_documento")
    private Documento documento;

    @ManyToOne
    @JoinColumn(name = "id_formulario")
    private Formulario formulario;  
}