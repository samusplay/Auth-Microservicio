package com.company.auth.apicontroller;

import com.company.auth.api.HealthApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController implements HealthApi {

    @Override
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Servicio de Auth funcionando correctamente");
    }
}
