package com.sintad.backendTest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sintad.backendTest.models.Entidad;


@Repository
public interface IEntidadRepository extends JpaRepository<Entidad, Long>{
    
}
