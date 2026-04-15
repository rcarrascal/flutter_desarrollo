package com.camiones.controller;

import com.camiones.dto.CamionDTO;
import com.camiones.dto.CamionRequest;
import com.camiones.service.CamionService;
import com.camiones.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/camiones")
@CrossOrigin(origins = "*")
public class CamionController {

    @Autowired
    private CamionService camionService;

    @Autowired
    private JwtUtil jwtUtil;

    private String getUsernameFromHeader(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Token no válido");
        }
        String token = authHeader.substring(7);
        return jwtUtil.getUsernameFromToken(token);
    }

    @GetMapping
    public ResponseEntity<List<CamionDTO>> getCamiones(
            @RequestHeader("Authorization") String authHeader) {
        try {
            String username = getUsernameFromHeader(authHeader);
            List<CamionDTO> camiones = camionService.getCamionesByUsuario(username);
            return ResponseEntity.ok(camiones);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<CamionDTO> createCamion(
            @Valid @RequestBody CamionRequest request,
            @RequestHeader("Authorization") String authHeader) {
        try {
            String username = getUsernameFromHeader(authHeader);
            CamionDTO camion = camionService.createCamion(request, username);
            return ResponseEntity.ok(camion);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCamion(
            @PathVariable Long id,
            @RequestHeader("Authorization") String authHeader) {
        try {
            String username = getUsernameFromHeader(authHeader);
            camionService.deleteCamion(id, username);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
