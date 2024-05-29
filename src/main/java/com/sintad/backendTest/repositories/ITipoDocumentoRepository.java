package com.sintad.backendTest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sintad.backendTest.models.TipoDocumento;


public interface ITipoDocumentoRepository extends JpaRepository<TipoDocumento, Long> {

    
}
