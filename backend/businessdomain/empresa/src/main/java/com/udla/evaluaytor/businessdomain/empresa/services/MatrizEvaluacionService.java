package com.udla.evaluaytor.businessdomain.empresa.services;

import java.util.List;

import com.udla.evaluaytor.businessdomain.empresa.dto.MatrizEvaluacionDTO;
import com.udla.evaluaytor.businessdomain.empresa.dto.MatrizEvaluacionResponseDTO;

public interface MatrizEvaluacionService{
    List<MatrizEvaluacionResponseDTO> getAllMatricesEvaluacion();
    MatrizEvaluacionResponseDTO getMatrizEvaluacionById(Long id);
    MatrizEvaluacionResponseDTO createMatrizEvaluacion(MatrizEvaluacionDTO matrizEvaluacionDTO);
    MatrizEvaluacionResponseDTO updateMatrizEvaluacion(Long id, MatrizEvaluacionDTO matrizEvaluacionUpdateDTO);
    boolean deleteMatrizEvaluacionById(Long id);
}
