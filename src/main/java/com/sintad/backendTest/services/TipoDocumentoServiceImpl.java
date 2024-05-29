package com.sintad.backendTest.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sintad.backendTest.dto.request.TipoDocumentoRequest;
import com.sintad.backendTest.exceptions.NotFoundException;
import com.sintad.backendTest.models.TipoDocumento;
import com.sintad.backendTest.repositories.ITipoDocumentoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService{
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ITipoDocumentoRepository tipoDocumentoRepository;

    public static final String DOCUMENT_NAME= "Tipo de Documento";

    @Override
    public List<TipoDocumentoRequest> allTipoDocumento() {
        List<TipoDocumento> tiposDocumento = tipoDocumentoRepository.findAll();
        return tiposDocumento.stream().filter(TipoDocumento::isEstado).map(this::mapDTO).collect(Collectors.toList());
    }

    @Override
    public TipoDocumentoRequest oneTipoDocumento(Long id) {
        TipoDocumento tipoDocumento = tipoDocumentoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(DOCUMENT_NAME));
        return mapDTO(tipoDocumento);
    }


    @Override
    public TipoDocumentoRequest createTipoDocumento(TipoDocumentoRequest tipoDocumentoRequest) {
        TipoDocumento tipoDocumento = mapEntity(tipoDocumentoRequest);

        TipoDocumento newTipoDocumento = tipoDocumentoRepository.save(tipoDocumento);

        return mapDTO(newTipoDocumento);
    }

    @Override
    public TipoDocumentoRequest updateTipoDocumento(TipoDocumentoRequest tipoDocumentoRequest, Long id) {
        TipoDocumento tipoDocumento = tipoDocumentoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(DOCUMENT_NAME));

        tipoDocumento.setCodigo(tipoDocumentoRequest.getCodigo());
        tipoDocumento.setNombre(tipoDocumentoRequest.getNombre());
        tipoDocumento.setDescripcion(tipoDocumentoRequest.getDescripcion());

        TipoDocumento updatedTipoDocumento = tipoDocumentoRepository.save(tipoDocumento);

        return mapDTO(updatedTipoDocumento);
    }

    @Override
    public void deleteTipoDocumento(Long id) {
        TipoDocumento tipoDocumento = tipoDocumentoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(DOCUMENT_NAME));

        tipoDocumento.setEstado(false);

        tipoDocumentoRepository.save(tipoDocumento);
    }

    private TipoDocumentoRequest mapDTO(TipoDocumento tipoDocumento){
        TipoDocumentoRequest tipoDocumentoRequest = modelMapper.map(tipoDocumento, TipoDocumentoRequest.class);
        return tipoDocumentoRequest;
    }

    private TipoDocumento mapEntity(TipoDocumentoRequest tipoDocumentoRequest){
        TipoDocumento tipoDocumento = modelMapper.map(tipoDocumentoRequest, TipoDocumento.class);
        return tipoDocumento;
    }
}
