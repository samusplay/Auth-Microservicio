package com.company.auth.service.impl;

import com.company.auth.entity.PruebaAuth;
import com.company.auth.exceptions.ResourceNotFoundException;
import com.company.auth.models.PruebaAuthDTO;
import com.company.auth.repository.PruebaAuthRepository;
import com.company.auth.service.PruebaAuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PruebaAuthServiceImpl implements PruebaAuthService {
    //inyectar repositorio
    private final PruebaAuthRepository pruebaAuthRepository;
    @Override
    public PruebaAuthDTO create(PruebaAuthDTO dto) {
        //logica del negocio + llamadas luego rrabbit
        PruebaAuth entity=new PruebaAuth();
        entity.setDesccripcion(dto.getDescripcion());
        PruebaAuth guardado=pruebaAuthRepository.save(entity);

        dto.setId(guardado.getId());
        return dto;

    }

    @Override
    public PruebaAuthDTO findById(Long id) {
        PruebaAuth entity=pruebaAuthRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No se encontró la prueba con ID: " + id));

        PruebaAuthDTO dto=new PruebaAuthDTO();
        dto.setId(entity.getId());
        dto.setDescripcion(entity.getDesccripcion());
        return dto;

    }
}
