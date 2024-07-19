package com.udla.evaluaytor.businessdomain.evaluacion.services;

import java.util.List;

import com.udla.evaluaytor.businessdomain.evaluacion.dto.EstadoDetalleDTO;
public interface EstadoDetalleService {
    List<EstadoDetalleDTO> getAllEstadosDetalle();
    EstadoDetalleDTO getEstadoDetalleById(Long id);
    EstadoDetalleDTO createEstadoDetalle(EstadoDetalleDTO estadoDetalleDTO);
    EstadoDetalleDTO updateEstadoDetalle(Long id, EstadoDetalleDTO estadoDetalleDTO);
    void deleteEstadoDetalle(Long id);
}
