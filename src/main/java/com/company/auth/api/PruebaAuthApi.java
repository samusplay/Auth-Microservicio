package com.company.auth.api;

import com.company.auth.models.PruebaAuthDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public interface PruebaAuthApi {

    @PostMapping("/crear")
    ResponseEntity<PruebaAuthDTO>createTest(@RequestBody PruebaAuthDTO dto);

    @GetMapping("/{id}")
    ResponseEntity<PruebaAuthDTO>getTest(@PathVariable Long id);

}
