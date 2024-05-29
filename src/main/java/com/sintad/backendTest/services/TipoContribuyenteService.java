package com.sintad.backendTest.services;

import java.util.List;

import com.sintad.backendTest.dto.request.TipoContribuyenteRequest;

public interface TipoContribuyenteService {

    public List<TipoContribuyenteRequest> allTipoContribuyente();

    public TipoContribuyenteRequest oneTipoContribuyente(Long id);

    public TipoContribuyenteRequest createTipoContribuyente(TipoContribuyenteRequest tipoContribuyenteRequest);

    public TipoContribuyenteRequest updateTipoContribuyente(TipoContribuyenteRequest tipoContribuyenteRequest, Long id);

    public void deleteTipoContribuyente(Long id);
}
