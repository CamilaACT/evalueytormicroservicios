package com.udla.evaluaytor.businessdomain.evaluacion.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;


@Entity
@Data
public class Formulario {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fecha;
    private String numero; 
    private int evaluacion;

     @ManyToOne
    @JoinColumn(name = "id_estado") // Especifica el nombre de la columna de la FK
    private EstadoFormulario estadoFormulario;
    @OneToMany(mappedBy = "formulario")
    private List<DetalleFormulario> detallesFormulario;

}
