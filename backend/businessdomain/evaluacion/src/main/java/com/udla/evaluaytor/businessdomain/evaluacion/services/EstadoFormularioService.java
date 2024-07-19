package com.udla.evaluaytor.businessdomain.evaluacion.services;

import java.util.List;

import com.udla.evaluaytor.businessdomain.evaluacion.dto.EstadoFormularioDTO;
public interface EstadoFormularioService {
    List<EstadoFormularioDTO> getAllEstadosFormulario();
    EstadoFormularioDTO getEstadoFormularioById(Long id);
    EstadoFormularioDTO createEstadoFormulario(EstadoFormularioDTO estadoFormularioDTO);
    EstadoFormularioDTO updateEstadoFormulario(Long id, EstadoFormularioDTO estadoFormularioDTO);
    void deleteEstadoFormulario(Long id);
}