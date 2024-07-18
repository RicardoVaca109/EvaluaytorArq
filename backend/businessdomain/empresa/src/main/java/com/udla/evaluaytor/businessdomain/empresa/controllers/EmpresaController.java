package com.udla.evaluaytor.businessdomain.empresa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udla.evaluaytor.businessdomain.empresa.dto.CategoriaDTO;
import com.udla.evaluaytor.businessdomain.empresa.dto.CategoriaResponseDTO;
import com.udla.evaluaytor.businessdomain.empresa.dto.MatrizEvaluacionDTO;
import com.udla.evaluaytor.businessdomain.empresa.dto.MatrizEvaluacionResponseDTO;
import com.udla.evaluaytor.businessdomain.empresa.dto.PeritoDTO;
import com.udla.evaluaytor.businessdomain.empresa.dto.PeritoResponseDTO;
import com.udla.evaluaytor.businessdomain.empresa.dto.ProveedorDTO;
import com.udla.evaluaytor.businessdomain.empresa.dto.ProveedorResponseDTO;
import com.udla.evaluaytor.businessdomain.empresa.repositories.CategoriaRepository;
import com.udla.evaluaytor.businessdomain.empresa.repositories.EmpresaRepository;
import com.udla.evaluaytor.businessdomain.empresa.repositories.MatrizEvaluacionRepository;
import com.udla.evaluaytor.businessdomain.empresa.repositories.PeritoRepository;
import com.udla.evaluaytor.businessdomain.empresa.repositories.ProveedorRepository;
import com.udla.evaluaytor.businessdomain.empresa.services.CategoriaService;
import com.udla.evaluaytor.businessdomain.empresa.services.MatrizEvaluacionService;
import com.udla.evaluaytor.businessdomain.empresa.services.PeritoService;
import com.udla.evaluaytor.businessdomain.empresa.services.ProveedorService;


@RestController
@RequestMapping("/api/empresa")
public class EmpresaController {
    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    ProveedorRepository proveedorRepository;

    @Autowired
    PeritoRepository peritoRepository;

    @Autowired
    PeritoService peritoService;

    @Autowired 
    CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    MatrizEvaluacionRepository matrizEvaluacionRepository;

    @Autowired
    private MatrizEvaluacionService matrizEvaluacionService;


     //enpointsMatrizEvaluacion
    @PostMapping("/matriz-evaluacion/save")
    public ResponseEntity<MatrizEvaluacionResponseDTO> createMatrizEvaluacion(@RequestBody MatrizEvaluacionDTO matrizEvaluacionDTO) {
        MatrizEvaluacionResponseDTO matrizGuardada = matrizEvaluacionService.createMatrizEvaluacion(matrizEvaluacionDTO);
        return new ResponseEntity<>(matrizGuardada, HttpStatus.CREATED);
    }

    @PutMapping("/matriz-evaluacion/update/{id}")
    public ResponseEntity<MatrizEvaluacionResponseDTO> updateMatrizEvaluacion(@PathVariable Long id, @RequestBody MatrizEvaluacionDTO matrizEvaluacionUpdateDTO) {
        MatrizEvaluacionResponseDTO updatedMatriz = matrizEvaluacionService.updateMatrizEvaluacion(id, matrizEvaluacionUpdateDTO);
        return ResponseEntity.ok(updatedMatriz);
    }

    @GetMapping("/matriz-evaluacion/findall")
    public ResponseEntity<List<MatrizEvaluacionResponseDTO>> getAllMatricesEvaluacion() {
        List<MatrizEvaluacionResponseDTO> matrices = matrizEvaluacionService.getAllMatricesEvaluacion();
        return ResponseEntity.ok(matrices);
    }

    @GetMapping("/matriz-evaluacion/findbyid/{id}")
    public ResponseEntity<MatrizEvaluacionResponseDTO> getMatrizEvaluacionById(@PathVariable Long id) {
        MatrizEvaluacionResponseDTO matriz = matrizEvaluacionService.getMatrizEvaluacionById(id);
        return ResponseEntity.ok(matriz);
    }

    @DeleteMapping("/matriz-evaluacion/deletebyid/{id}")
    public ResponseEntity<Void> deleteMatrizEvaluacionById(@PathVariable Long id) {
        boolean isDeleted = matrizEvaluacionService.deleteMatrizEvaluacionById(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    
    //enpoints Categoria
    @PostMapping("/categoria/save")
    public ResponseEntity<CategoriaResponseDTO> createCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        CategoriaResponseDTO categoriaGuardada = categoriaService.createCategoria(categoriaDTO);
        return new ResponseEntity<>(categoriaGuardada, HttpStatus.CREATED);
    }

    @PutMapping("/categoria/update/{id}")
    public ResponseEntity<CategoriaResponseDTO> updateCategoria(@PathVariable Long id, @RequestBody CategoriaDTO categoriaUpdateDTO) {
        CategoriaResponseDTO updatedCategoria = categoriaService.updateCategoria(id, categoriaUpdateDTO);
        return ResponseEntity.ok(updatedCategoria);
    }

    @GetMapping("/categoria/findall")
    public ResponseEntity<List<CategoriaResponseDTO>> getAllCategorias() {
        List<CategoriaResponseDTO> categorias = categoriaService.getAllCategorias();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/categoria/findbyid/{id}")
    public ResponseEntity<CategoriaResponseDTO> getCategoriaById(@PathVariable Long id) {
        CategoriaResponseDTO categoria = categoriaService.getCategoriaById(id);
        return ResponseEntity.ok(categoria);
    }


    @DeleteMapping("/categoria/deletebyid/{id}")
    public ResponseEntity<Void> deleteCategoriaById(@PathVariable Long id) {
        boolean isDeleted = categoriaService.deleteCategoriaById(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

   
    //enpoints perito
    @PostMapping("/perito/save")
    public ResponseEntity<PeritoResponseDTO> createPerito(@RequestBody PeritoDTO peritoDTO) {
        PeritoResponseDTO peritoGuardado = peritoService.createPerito(peritoDTO);
        return new ResponseEntity<>(peritoGuardado, HttpStatus.CREATED);
    }


    @PutMapping("/perito/update/{id}")
    public ResponseEntity<PeritoResponseDTO> updatePerito(@PathVariable Long id, @RequestBody PeritoDTO peritoUpdateDTO) {
        PeritoResponseDTO updatedPerito = peritoService.updatePerito(id, peritoUpdateDTO);
        return ResponseEntity.ok(updatedPerito);
    }


    @GetMapping("/perito/findall")
    public ResponseEntity<List<PeritoResponseDTO>> getAllPeritos() {
        List<PeritoResponseDTO> peritos = peritoService.getAllPeritos();
        return ResponseEntity.ok(peritos);
    }


    @GetMapping("/perito/findbyid/{id}")
    public ResponseEntity<PeritoResponseDTO> getPeritoById(@PathVariable Long id) {
        PeritoResponseDTO perito = peritoService.getPeritoById(id);
        return ResponseEntity.ok(perito);
    }


    @DeleteMapping("/perito/deletebyid/{id}")
    public ResponseEntity<Void> deletePeritoById(@PathVariable Long id) {
        boolean isRemoved = peritoService.deletePeritoById(id);
        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //enpoints proveedor
    @PostMapping("/proveedor/save")
    public ResponseEntity<ProveedorResponseDTO> createProveedor(@RequestBody ProveedorDTO proveedorDTO) {
        ProveedorResponseDTO proveedorGuardado = proveedorService.createProveedor(proveedorDTO);
        return new ResponseEntity<>(proveedorGuardado, HttpStatus.CREATED);
    }
      
    @PutMapping("/proveedor/update/{id}")
    public ResponseEntity<ProveedorResponseDTO> updateProveedor(@PathVariable Long id, @RequestBody ProveedorDTO proveedorUpdateDTO) {
        ProveedorResponseDTO updatedProveedor = proveedorService.updateProveedor(id, proveedorUpdateDTO);
        return ResponseEntity.ok(updatedProveedor);
    }


    @GetMapping("proveedor/findall")
    public ResponseEntity<List<ProveedorResponseDTO>> getAllProveedores() {
        List<ProveedorResponseDTO> proveedores = proveedorService.getAllProveedores();
        return ResponseEntity.ok(proveedores);
    }

    @GetMapping("proveedor/findbyid/{id}")
     public ResponseEntity<ProveedorResponseDTO> getProveedorById(@PathVariable Long id) {
        ProveedorResponseDTO proveedor = proveedorService.getProveedorById(id);
        return ResponseEntity.ok(proveedor);
    }

    @DeleteMapping("/proveedor/deletebyid/{id}")
    public ResponseEntity<Void> deleteProveedorById(@PathVariable Long id) {
        boolean isRemoved = proveedorService.deleteProveedorById(id);
        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}