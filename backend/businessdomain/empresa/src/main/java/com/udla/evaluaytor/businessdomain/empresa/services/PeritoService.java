package com.udla.evaluaytor.businessdomain.empresa.services;

import java.util.List;

import com.udla.evaluaytor.businessdomain.empresa.dto.PeritoDTO;
import com.udla.evaluaytor.businessdomain.empresa.dto.PeritoResponseDTO;

public interface PeritoService {
    List<PeritoResponseDTO> getAllPeritos();
    PeritoResponseDTO getPeritoById(Long id);
    PeritoResponseDTO createPerito(PeritoDTO peritoDTO);
    PeritoResponseDTO updatePerito(Long id, PeritoDTO peritoUpdateDTO);
    boolean deletePeritoById(Long id);
}
