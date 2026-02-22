package com.company.auth.apicontroller;

import com.company.auth.api.PruebaAuthApi;
import com.company.auth.models.PruebaAuthDTO;
import com.company.auth.service.PruebaAuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
//respetar la url base api/auth
@RequestMapping("/prueba")
public class PruebaAuthApiController implements PruebaAuthApi {
    private final PruebaAuthService pruebaAuthService;
    @Override
    public ResponseEntity<PruebaAuthDTO> createTest(PruebaAuthDTO dto) {
       PruebaAuthDTO create=pruebaAuthService.create(dto);
       //retornamos un 201
        return new ResponseEntity<>(create, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PruebaAuthDTO> getTest(Long id) {
        PruebaAuthDTO finded=pruebaAuthService.findById(id);
        return  ResponseEntity.ok(finded);
    }
}
