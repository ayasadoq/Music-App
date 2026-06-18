package com.ecolemusique.service;

import com.ecolemusique.dto.StatistiqueCount;
import com.ecolemusique.entity.Cours;
import com.ecolemusique.enumeration.Jour;
import com.ecolemusique.enumeration.Niveau;
import com.ecolemusique.repository.CoursRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CoursService {

    private final CoursRepository coursRepository;

    public CoursService(CoursRepository coursRepository) {
        this.coursRepository = coursRepository;
    }

    public List<Cours> findAll(Jour jour, Niveau niveau) {
        if (jour != null && niveau != null) {
            return coursRepository.findByJourAndNiveau(jour, niveau);
        }
        if (jour != null) {
            return coursRepository.findByJour(jour);
        }
        if (niveau != null) {
            return coursRepository.findByNiveau(niveau);
        }
        return coursRepository.findAll();
    }

    public Optional<Cours> findById(Long id) {
        return coursRepository.findById(id);
    }

    public Cours save(Cours cours) {
        return coursRepository.save(cours);
    }

    public Optional<Cours> update(Long id, Cours cours) {
        return coursRepository.findById(id).map(existing -> {
            existing.setJour(cours.getJour());
            existing.setHeureDebut(cours.getHeureDebut());
            existing.setDureeMin(cours.getDureeMin());
            existing.setNiveau(cours.getNiveau());
            existing.setProf(cours.getProf());
            return coursRepository.save(existing);
        });
    }

    public boolean deleteById(Long id) {
        if (!coursRepository.existsById(id)) {
            return false;
        }
        coursRepository.deleteById(id);
        return true;
    }

    public List<StatistiqueCount> countByJour() {
        return coursRepository.countCoursByJour();
    }
}
