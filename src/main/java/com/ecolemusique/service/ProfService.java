package com.ecolemusique.service;

import com.ecolemusique.entity.Prof;
import com.ecolemusique.repository.ProfRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ProfService {

    private final ProfRepository profRepository;

    public ProfService(ProfRepository profRepository) {
        this.profRepository = profRepository;
    }

    public List<Prof> findAll() {
        return profRepository.findAll();
    }

    public Optional<Prof> findById(Long id) {
        return profRepository.findById(id);
    }

    public Prof save(Prof prof) {
        return profRepository.save(prof);
    }

    public Optional<Prof> update(Long id, Prof prof) {
        return profRepository.findById(id).map(existing -> {
            existing.setNom(prof.getNom());
            existing.setSpecialite(prof.getSpecialite());
            return profRepository.save(existing);
        });
    }

    public boolean deleteById(Long id) {
        if (!profRepository.existsById(id)) {
            return false;
        }
        profRepository.deleteById(id);
        return true;
    }
}
