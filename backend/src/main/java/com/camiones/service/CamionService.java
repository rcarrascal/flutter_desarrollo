package com.camiones.service;

import com.camiones.dto.CamionDTO;
import com.camiones.dto.CamionRequest;
import com.camiones.model.Camion;
import com.camiones.model.Usuario;
import com.camiones.repository.CamionRepository;
import com.camiones.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CamionService {

    @Autowired
    private CamionRepository camionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<CamionDTO> getCamionesByUsuario(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return camionRepository.findByUsuario(usuario).stream()
                .map(camion -> new CamionDTO(
                        camion.getId(),
                        camion.getPlaca(),
                        camion.getTipoCamion(),
                        camion.getUsuario().getId()
                ))
                .collect(Collectors.toList());
    }

    public CamionDTO createCamion(CamionRequest request, String username) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Camion camion = new Camion(
                request.getPlaca(),
                request.getTipoCamion(),
                usuario
        );

        camion = camionRepository.save(camion);

        return new CamionDTO(
                camion.getId(),
                camion.getPlaca(),
                camion.getTipoCamion(),
                camion.getUsuario().getId()
        );
    }

    public void deleteCamion(Long id, String username) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Camion camion = camionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Camión no encontrado"));

        if (!camion.getUsuario().getId().equals(usuario.getId())) {
            throw new RuntimeException("No tienes permiso para eliminar este camión");
        }

        camionRepository.delete(camion);
    }
}
