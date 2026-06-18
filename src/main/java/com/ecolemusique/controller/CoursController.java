package com.ecolemusique.controller;

import com.ecolemusique.entity.Cours;
import com.ecolemusique.enumeration.Jour;
import com.ecolemusique.enumeration.Niveau;
import com.ecolemusique.service.CoursService;
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
@RequestMapping("/api/cours")
public class CoursController {

    private final CoursService coursService;

    public CoursController(CoursService coursService) {
        this.coursService = coursService;
    }

    @GetMapping
    public List<Cours> findAll(
            @RequestParam(required = false) Jour jour,
            @RequestParam(required = false) Niveau niveau) {
        return coursService.findAll(jour, niveau);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cours> findById(@PathVariable Long id) {
        return coursService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cours> create(@RequestBody Cours cours) {
        return ResponseEntity.status(HttpStatus.CREATED).body(coursService.save(cours));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cours> update(@PathVariable Long id, @RequestBody Cours cours) {
        return coursService.update(id, cours)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!coursService.deleteById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
