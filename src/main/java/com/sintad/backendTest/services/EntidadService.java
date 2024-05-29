package com.sintad.backendTest.services;

import java.util.List;

import com.sintad.backendTest.dto.request.EntidadRequest;

public interface EntidadService {
    public List<EntidadRequest> allEntidad();

    public List<EntidadRequest> allEntidadWithDetails();

    public EntidadRequest oneEntidad(Long id);

    public EntidadRequest createEntidad(EntidadRequest entidadDTO);

    public EntidadRequest updateEntidad(EntidadRequest entidadDTO, Long id);

    public void deleteEntidad(Long id);
}
