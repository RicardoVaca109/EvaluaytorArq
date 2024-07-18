package com.udla.evaluaytor.businessdomain.empresa.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udla.evaluaytor.businessdomain.empresa.dto.CategoriaDTO;
import com.udla.evaluaytor.businessdomain.empresa.dto.CategoriaResponseDTO;
import com.udla.evaluaytor.businessdomain.empresa.models.Categoria;
import com.udla.evaluaytor.businessdomain.empresa.repositories.CategoriaRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoriaImpl implements CategoriaService{
    @Autowired
    private CategoriaRepository categoriaRepository;


    @Override
    public CategoriaResponseDTO getCategoriaById(Long id) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        if (!optionalCategoria.isPresent()) {
            throw new RuntimeException("Categoria no encontrada con id " + id);
        }
        Categoria categoria = optionalCategoria.get();
        return convertToDTO(categoria);
    }
    
    @Override
    public List<CategoriaResponseDTO> getAllCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CategoriaResponseDTO createCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setDescripcion(categoriaDTO.getDescripcion());

        Categoria categoriaGuardada = categoriaRepository.save(categoria);
        return convertToDTO(categoriaGuardada);
    }

    @Override
    public boolean deleteCategoriaById(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()) {
            categoriaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public CategoriaResponseDTO updateCategoria(Long id, CategoriaDTO categoriaUpdateDTO) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);

        if (!optionalCategoria.isPresent()) {
            throw new RuntimeException("Categoria no encontrada con id " + id);
        }

        Categoria categoria = optionalCategoria.get();
        categoria.setDescripcion(categoriaUpdateDTO.getDescripcion());

        Categoria updatedCategoria = categoriaRepository.save(categoria);
        return convertToDTO(updatedCategoria);
    }

    private CategoriaResponseDTO convertToDTO(Categoria categoria) {
        CategoriaResponseDTO dto = new CategoriaResponseDTO();
        dto.setId(categoria.getId());
        dto.setDescripcion(categoria.getDescripcion());
        return dto;
    }
}