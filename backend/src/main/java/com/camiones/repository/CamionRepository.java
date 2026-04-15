package com.camiones.repository;

import com.camiones.model.Camion;
import com.camiones.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CamionRepository extends JpaRepository<Camion, Long> {
    List<Camion> findByUsuario(Usuario usuario);
}
