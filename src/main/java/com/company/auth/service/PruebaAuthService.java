package com.company.auth.service;

import com.company.auth.models.PruebaAuthDTO;

public interface PruebaAuthService {
    PruebaAuthDTO create(PruebaAuthDTO dto);
    PruebaAuthDTO findById(Long id);
}
