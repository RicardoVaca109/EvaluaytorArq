package com.udla.evaluaytor.businessdomain.empresa.dto;

import lombok.Data;

@Data
public class MatrizEvaluacionResponseDTO {
    private Long id;
    private String pregunta;
    private Integer puntos;
    private Boolean requiereDocumento;
    private CategoriaResponseDTO categoria;
}
