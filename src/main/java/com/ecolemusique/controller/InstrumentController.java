package com.ecolemusique.controller;

import com.ecolemusique.entity.Instrument;
import com.ecolemusique.enumeration.FamilleInstrument;
import com.ecolemusique.service.InstrumentService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/instruments")
public class InstrumentController {

    private final InstrumentService instrumentService;

    public InstrumentController(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    @GetMapping
    public List<Instrument> findAll(
            @RequestParam(required = false) FamilleInstrument famille,
            @RequestParam(required = false) Boolean disponibilite) {
        return instrumentService.findAll(famille, disponibilite);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instrument> findById(@PathVariable Long id) {
        return instrumentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Instrument> create(@RequestBody Instrument instrument) {
        return ResponseEntity.status(HttpStatus.CREATED).body(instrumentService.save(instrument));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instrument> update(@PathVariable Long id, @RequestBody Instrument instrument) {
        return instrumentService.update(id, instrument)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!instrumentService.deleteById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
