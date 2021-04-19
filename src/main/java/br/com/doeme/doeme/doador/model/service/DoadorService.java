package br.com.doeme.doeme.doador.model.service;

import br.com.doeme.doeme.doador.model.entity.Doador;
import br.com.doeme.doeme.exceptions.ResourceFoundException;
import br.com.doeme.doeme.service.CrudService;

import java.util.Optional;

public interface DoadorService extends CrudService<Doador, Long> {
    Optional<Doador> findByName(String name);

    @Override
    Doador save(Doador pessoa) throws ResourceFoundException;
}
