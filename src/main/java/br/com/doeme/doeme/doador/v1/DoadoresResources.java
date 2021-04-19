package br.com.doeme.doeme.doador.v1;

import br.com.doeme.doeme.doador.model.entity.Doador;
import br.com.doeme.doeme.doador.model.service.DoadorService;
import br.com.doeme.doeme.exceptions.ResourceFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/doadores")
public class DoadoresResources {

    @Autowired
    private DoadorService doadorService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Doador>> list() {
        List<Doador> pessoaList = doadorService.list();

        if (pessoaList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(pessoaList);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Doador> finById(@PathVariable("id") Long id) {
        Optional<Doador> pessoa = doadorService.findById(id);

        if (!pessoa.isPresent())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(pessoa.get());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Doador> save(@Valid @RequestBody Doador pessoa) throws ResourceFoundException {
        Doador saved = doadorService.save(pessoa);

        if (saved == null)
            return ResponseEntity.noContent().build();

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Doador> update(@PathVariable("id") Long id, @Valid @RequestBody Doador pessoa) {
        Doador updated = doadorService.update(id, pessoa);

        if (updated == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> detele(@PathVariable("id") Long id) {
        doadorService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
