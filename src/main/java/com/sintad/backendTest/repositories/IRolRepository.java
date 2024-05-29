package com.sintad.backendTest.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sintad.backendTest.models.Rol;
import com.sintad.backendTest.utils.TypeRole;

public interface IRolRepository extends JpaRepository<Rol, Long> {
    public Optional<Rol> findByNombre(TypeRole nombre);
}