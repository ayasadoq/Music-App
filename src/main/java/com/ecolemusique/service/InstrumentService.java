package com.ecolemusique.service;

import com.ecolemusique.dto.StatistiqueCount;
import com.ecolemusique.entity.Instrument;
import com.ecolemusique.enumeration.FamilleInstrument;
import com.ecolemusique.repository.InstrumentRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class InstrumentService {

    private final InstrumentRepository instrumentRepository;

    public InstrumentService(InstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

    public List<Instrument> findAll(FamilleInstrument famille, Boolean disponibilite) {
        if (famille != null && disponibilite != null) {
            return instrumentRepository.findByFamilleAndDisponibilite(famille, disponibilite);
        }
        if (famille != null) {
            return instrumentRepository.findByFamille(famille);
        }
        if (disponibilite != null) {
            return instrumentRepository.findByDisponibilite(disponibilite);
        }
        return instrumentRepository.findAll();
    }

    public Optional<Instrument> findById(Long id) {
        return instrumentRepository.findById(id);
    }

    public Instrument save(Instrument instrument) {
        return instrumentRepository.save(instrument);
    }

    public Optional<Instrument> update(Long id, Instrument instrument) {
        return instrumentRepository.findById(id).map(existing -> {
            existing.setNom(instrument.getNom());
            existing.setFamille(instrument.getFamille());
            existing.setDisponibilite(instrument.getDisponibilite());
            return instrumentRepository.save(existing);
        });
    }

    public boolean deleteById(Long id) {
        if (!instrumentRepository.existsById(id)) {
            return false;
        }
        instrumentRepository.deleteById(id);
        return true;
    }

    public List<StatistiqueCount> countByFamille() {
        return instrumentRepository.countInstrumentsByFamille();
    }
}
