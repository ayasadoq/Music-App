package com.ecolemusique.controller;

import com.ecolemusique.entity.Prof;
import com.ecolemusique.service.ProfService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profs")
public class ProfController {

    private final ProfService profService;

    public ProfController(ProfService profService) {
        this.profService = profService;
    }

    @GetMapping
    public List<Prof> findAll() {
        return profService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prof> findById(@PathVariable Long id) {
        return profService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Prof> create(@RequestBody Prof prof) {
        return ResponseEntity.status(HttpStatus.CREATED).body(profService.save(prof));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prof> update(@PathVariable Long id, @RequestBody Prof prof) {
        return profService.update(id, prof)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!profService.deleteById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
