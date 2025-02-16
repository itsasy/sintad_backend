package com.sintad.backendTest.services;

import java.util.List;

import com.sintad.backendTest.dto.request.TipoDocumentoRequest;

public interface TipoDocumentoService {
    public List<TipoDocumentoRequest> allTipoDocumento();

    public TipoDocumentoRequest oneTipoDocumento(Long id);

    public TipoDocumentoRequest createTipoDocumento(TipoDocumentoRequest tipoDocumentoRequest);

    public TipoDocumentoRequest updateTipoDocumento(TipoDocumentoRequest tipoDocumentoRequest, Long id);

    public void deleteTipoDocumento(Long id);
}
