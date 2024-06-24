package com.udla.evaluaytor.businessdomain.evaluacion.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udla.evaluaytor.businessdomain.evaluacion.dto.EstadoFormularioDTO;
import com.udla.evaluaytor.businessdomain.evaluacion.dto.FormularioCreateUpdateDTO;
import com.udla.evaluaytor.businessdomain.evaluacion.dto.FormularioDTO;
import com.udla.evaluaytor.businessdomain.evaluacion.models.EstadoFormulario;
import com.udla.evaluaytor.businessdomain.evaluacion.models.Formulario;
import com.udla.evaluaytor.businessdomain.evaluacion.repositories.EstadoFormularioRepository;
import com.udla.evaluaytor.businessdomain.evaluacion.repositories.FormularioRepository;

@Service
public class FormularioImpl implements FormularioService{

    @Autowired
    private FormularioRepository formularioRepository;

    @Autowired
    private EstadoFormularioRepository estadoFormularioRepository;

    @Override
    public List<FormularioDTO> getAllFormularios() {
        List<Formulario> formularios = formularioRepository.findAll();
        return formularios.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public FormularioDTO getFormularioById(Long id) {
        Optional<Formulario> formularioOpt = formularioRepository.findById(id);
        return formularioOpt.map(this::convertToDTO).orElse(null); // O lanza una excepción
    }

    @Override
    public FormularioDTO createFormulario(FormularioCreateUpdateDTO formularioDTO) {
        Formulario formulario = convertToEntity(formularioDTO);
        Formulario savedFormulario = formularioRepository.save(formulario);
        return convertToDTO(savedFormulario);
    }

    @Override
    public FormularioDTO updateFormulario(Long id, FormularioCreateUpdateDTO formularioDTO) {
        Optional<Formulario> formularioOpt = formularioRepository.findById(id);
        if (formularioOpt.isPresent()) {
            Formulario formulario = formularioOpt.get();
            formulario.setFecha(Optional.ofNullable(formularioDTO.getFecha()).orElse(new Date()));
            formulario.setNumero(formularioDTO.getNumero());
            formulario.setEvaluacion(formularioDTO.getEvaluacion());

            Optional<EstadoFormulario> estadoOpt = estadoFormularioRepository.findById(formularioDTO.getEstadoFormularioId());
            estadoOpt.ifPresent(formulario::setEstadoFormulario);

            Formulario updatedFormulario = formularioRepository.save(formulario);
            return convertToDTO(updatedFormulario);
        }
        return null; // O lanza una excepción
    }

    @Override
    public void deleteFormulario(Long id) {
        formularioRepository.deleteById(id);
    }


    private FormularioDTO convertToDTO(Formulario formulario) {
        FormularioDTO dto = new FormularioDTO();
        dto.setId(formulario.getId());
        dto.setFecha(formulario.getFecha());
        dto.setNumero(formulario.getNumero());
        dto.setEvaluacion(formulario.getEvaluacion());

        EstadoFormularioDTO estadoDTO = new EstadoFormularioDTO();
        estadoDTO.setId(formulario.getEstadoFormulario().getId());
        estadoDTO.setNombre(formulario.getEstadoFormulario().getNombre());
        dto.setEstadoFormularioDTO(estadoDTO);

        return dto;
    }

    private Formulario convertToEntity(FormularioCreateUpdateDTO dto) {
        Formulario formulario = new Formulario();
        formulario.setFecha(Optional.ofNullable(dto.getFecha()).orElse(new Date()));
        formulario.setNumero(dto.getNumero());
        formulario.setEvaluacion(dto.getEvaluacion());

        Optional<EstadoFormulario> estadoOpt = estadoFormularioRepository.findById(dto.getEstadoFormularioId());
        estadoOpt.ifPresent(formulario::setEstadoFormulario);

        return formulario;
    }
}