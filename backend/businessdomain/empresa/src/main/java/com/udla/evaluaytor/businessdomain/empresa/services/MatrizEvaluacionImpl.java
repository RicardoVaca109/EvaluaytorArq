package com.udla.evaluaytor.businessdomain.empresa.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udla.evaluaytor.businessdomain.empresa.dto.CategoriaResponseDTO;
import com.udla.evaluaytor.businessdomain.empresa.dto.MatrizEvaluacionDTO;
import com.udla.evaluaytor.businessdomain.empresa.dto.MatrizEvaluacionResponseDTO;
import com.udla.evaluaytor.businessdomain.empresa.models.Categoria;
import com.udla.evaluaytor.businessdomain.empresa.models.MatrizEvaluacion;
import com.udla.evaluaytor.businessdomain.empresa.repositories.CategoriaRepository;
import com.udla.evaluaytor.businessdomain.empresa.repositories.MatrizEvaluacionRepository;

import jakarta.transaction.Transactional;

@Service
public class MatrizEvaluacionImpl implements MatrizEvaluacionService{
    @Autowired
    private MatrizEvaluacionRepository matrizEvaluacionRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;


    @Override
    public MatrizEvaluacionResponseDTO getMatrizEvaluacionById(Long id) {
        Optional<MatrizEvaluacion> optionalMatriz = matrizEvaluacionRepository.findById(id);
        if (!optionalMatriz.isPresent()) {
            throw new RuntimeException("Matriz de evaluación no encontrada con id " + id);
        }
        MatrizEvaluacion matriz = optionalMatriz.get();
        return convertToDTO(matriz);
    }

    @Override
    public List<MatrizEvaluacionResponseDTO> getAllMatricesEvaluacion() {
        List<MatrizEvaluacion> matrices = matrizEvaluacionRepository.findAll();
        return matrices.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public MatrizEvaluacionResponseDTO createMatrizEvaluacion(MatrizEvaluacionDTO matrizEvaluacionDTO) {
        MatrizEvaluacion matriz = new MatrizEvaluacion();
        matriz.setPregunta(matrizEvaluacionDTO.getPregunta());
        matriz.setPuntos(matrizEvaluacionDTO.getPuntos());
        matriz.setRequiereDocumento(matrizEvaluacionDTO.getRequiereDocumento());

        Categoria categoria = categoriaRepository.findById(matrizEvaluacionDTO.getIdCategoria())
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id " + matrizEvaluacionDTO.getIdCategoria()));
        matriz.setCategoria(categoria);

        MatrizEvaluacion matrizGuardada = matrizEvaluacionRepository.save(matriz);
        return convertToDTO(matrizGuardada);
    }

    @Override
    public boolean deleteMatrizEvaluacionById(Long id) {
        Optional<MatrizEvaluacion> matriz = matrizEvaluacionRepository.findById(id);
        if (matriz.isPresent()) {
            matrizEvaluacionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public MatrizEvaluacionResponseDTO updateMatrizEvaluacion(Long id, MatrizEvaluacionDTO matrizEvaluacionUpdateDTO) {
        Optional<MatrizEvaluacion> optionalMatriz = matrizEvaluacionRepository.findById(id);

        if (!optionalMatriz.isPresent()) {
            throw new RuntimeException("Matriz de evaluación no encontrada con id " + id);
        }

        MatrizEvaluacion matriz = optionalMatriz.get();
        matriz.setPregunta(matrizEvaluacionUpdateDTO.getPregunta());
        matriz.setPuntos(matrizEvaluacionUpdateDTO.getPuntos());
        matriz.setRequiereDocumento(matrizEvaluacionUpdateDTO.getRequiereDocumento());

        Categoria categoria = categoriaRepository.findById(matrizEvaluacionUpdateDTO.getIdCategoria())
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id " + matrizEvaluacionUpdateDTO.getIdCategoria()));
        matriz.setCategoria(categoria);

        MatrizEvaluacion updatedMatriz = matrizEvaluacionRepository.save(matriz);
        return convertToDTO(updatedMatriz);
    }


    private MatrizEvaluacionResponseDTO convertToDTO(MatrizEvaluacion matriz) {
        MatrizEvaluacionResponseDTO dto = new MatrizEvaluacionResponseDTO();
        dto.setId(matriz.getId());
        dto.setPregunta(matriz.getPregunta());
        dto.setPuntos(matriz.getPuntos());
        dto.setRequiereDocumento(matriz.getRequiereDocumento());
        
        CategoriaResponseDTO categoriaDTO = new CategoriaResponseDTO();
        categoriaDTO.setId(matriz.getCategoria().getId());
        categoriaDTO.setDescripcion(matriz.getCategoria().getDescripcion());
        dto.setCategoria(categoriaDTO);
        
        return dto;
    }
}
