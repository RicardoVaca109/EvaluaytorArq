package com.udla.evaluaytor.businessdomain.empresa.services;

import java.util.List;

import com.udla.evaluaytor.businessdomain.empresa.dto.CategoriaDTO;
import com.udla.evaluaytor.businessdomain.empresa.dto.CategoriaResponseDTO;

public interface CategoriaService {
    List<CategoriaResponseDTO> getAllCategorias();
    CategoriaResponseDTO getCategoriaById(Long id);
    CategoriaResponseDTO createCategoria(CategoriaDTO categoriaDTO);
    CategoriaResponseDTO updateCategoria(Long id, CategoriaDTO categoriaUpdateDTO);
    boolean deleteCategoriaById(Long id);
}
