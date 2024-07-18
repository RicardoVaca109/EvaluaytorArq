package com.udla.evaluaytor.businessdomain.empresa.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udla.evaluaytor.businessdomain.empresa.dto.PeritoDTO;
import com.udla.evaluaytor.businessdomain.empresa.dto.PeritoResponseDTO;
import com.udla.evaluaytor.businessdomain.empresa.models.Perito;
import com.udla.evaluaytor.businessdomain.empresa.repositories.PeritoRepository;

import jakarta.transaction.Transactional;

@Service
public class PeritoImpl implements PeritoService{
    @Autowired
    private PeritoRepository peritoRepository;


    @Override
    public PeritoResponseDTO getPeritoById(Long id) {
        Optional<Perito> optionalPerito = peritoRepository.findById(id);
        if (!optionalPerito.isPresent()) {
            throw new RuntimeException("Perito no encontrado con id " + id);
        }
        Perito perito = optionalPerito.get();
        return convertToDTO(perito);
    }

    @Override
    public List<PeritoResponseDTO> getAllPeritos() {
        List<Perito> peritos = peritoRepository.findAll();
        return peritos.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public PeritoResponseDTO createPerito(PeritoDTO peritoDTO) {
        Perito perito = new Perito();
        perito.setNombre(peritoDTO.getNombre());
        perito.setDireccion(peritoDTO.getDireccion());
        perito.setTelefono(peritoDTO.getTelefono());

        Perito peritoGuardado = peritoRepository.save(perito);
        return convertToDTO(peritoGuardado);
    }

    @Override
    public boolean deletePeritoById(Long id) {
        Optional<Perito> perito = peritoRepository.findById(id);
        if (perito.isPresent()) {
            peritoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public PeritoResponseDTO updatePerito(Long id, PeritoDTO peritoUpdateDTO) {
        Optional<Perito> optionalPerito = peritoRepository.findById(id);

        if (!optionalPerito.isPresent()) {
            throw new RuntimeException("Perito no encontrado con id " + id);
        }

        Perito perito = optionalPerito.get();
        perito.setNombre(peritoUpdateDTO.getNombre());
        perito.setDireccion(peritoUpdateDTO.getDireccion());
        perito.setTelefono(peritoUpdateDTO.getTelefono());

        Perito updatedPerito = peritoRepository.save(perito);
        return convertToDTO(updatedPerito);
    }

    private PeritoResponseDTO convertToDTO(Perito perito) {
        PeritoResponseDTO dto = new PeritoResponseDTO();
        dto.setId(perito.getId());
        dto.setNombre(perito.getNombre());
        dto.setDireccion(perito.getDireccion());
        dto.setTelefono(perito.getTelefono());
        return dto;
    }
  
}
