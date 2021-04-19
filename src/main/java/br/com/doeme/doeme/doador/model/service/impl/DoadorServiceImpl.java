package br.com.doeme.doeme.doador.model.service.impl;

import br.com.doeme.doeme.doador.model.entity.Doador;
import br.com.doeme.doeme.doador.model.repositories.DoadorRepository;
import br.com.doeme.doeme.doador.model.service.DoadorService;
import br.com.doeme.doeme.exceptions.ResourceFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoadorServiceImpl implements DoadorService {

    @Autowired
    private DoadorRepository doadorRepository;

    @Override
    public Optional<Doador> findByName(String name) {
        return doadorRepository.findByName(name);
    }

    @Override
    public Doador save(Doador pessoa) throws ResourceFoundException {
        Optional<Doador> pessoaById = findByName(pessoa.getName());
        if (pessoaById.isPresent()) {
            throw new ResourceFoundException("Esse Recurso Já exite na nossa base");
        }

        return doadorRepository.save(pessoa);
    }

    @Override
    public Doador update(Long id, Doador doador) {
        Optional<Doador> pessoaById = findById(id);
        if (pessoaById.isPresent()) {
            var pessoaUpdate = pessoaById.get();
            pessoaUpdate.update(id, doador);
            return doadorRepository.save(pessoaUpdate);
        }

        return new Doador();
    }

    @Override
    public List<Doador> list() {
        return doadorRepository.findAll();
    }

    @Override
    public Optional<Doador> findById(Long id) {
        return doadorRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        doadorRepository.deleteById(id);
    }
}
