package com.udla.evaluaytor.businessdomain.evaluacion.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import java.util.List;
@Data
@Entity
public class EstadoDetalle {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @OneToMany(mappedBy = "estadoDetalle")
    private List<DetalleFormulario> detallesFormulario;
}
