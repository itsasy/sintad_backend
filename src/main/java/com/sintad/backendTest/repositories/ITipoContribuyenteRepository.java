package com.sintad.backendTest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sintad.backendTest.models.TipoContribuyente;

import java.util.List;

public interface ITipoContribuyenteRepository extends JpaRepository<TipoContribuyente, Long> {

    @Query("SELECT tc FROM TipoContribuyente tc")
    List<TipoContribuyente> findAllWithEntidades();
}
