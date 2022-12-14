package com.rpx.bsm.resources;

import com.rpx.bsm.entities.TipoDespesa;
import com.rpx.bsm.records.TipoDespesaRecord;
import com.rpx.bsm.services.TipoDespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/tipoDespesas")
public class TipoDespesaResource {

    @Autowired
    private TipoDespesaService service;

    @GetMapping
    public ResponseEntity<List<TipoDespesa>> findAll() {
        List<TipoDespesa> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<TipoDespesa> insert(@Valid @RequestBody TipoDespesaRecord obj) {
        TipoDespesa fp = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(fp.getId()).toUri();
        return ResponseEntity.created(uri).body(fp);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TipoDespesa> findById(@PathVariable Long id){
        TipoDespesa obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TipoDespesa> update(@PathVariable Long id, @RequestBody TipoDespesaRecord obj) {
        TipoDespesa entidade = service.update(id, obj);
        return ResponseEntity.ok().body(entidade);
    }

}
