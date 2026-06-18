package com.ecolemusique.repository;

import com.ecolemusique.dto.StatistiqueCount;
import com.ecolemusique.entity.Instrument;
import com.ecolemusique.enumeration.FamilleInstrument;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InstrumentRepository extends JpaRepository<Instrument, Long> {

    List<Instrument> findByFamille(FamilleInstrument famille);

    List<Instrument> findByDisponibilite(boolean disponibilite);

    List<Instrument> findByFamilleAndDisponibilite(FamilleInstrument famille, boolean disponibilite);

    @Query("select new com.ecolemusique.dto.StatistiqueCount(cast(i.famille as string), count(i)) "
            + "from Instrument i group by i.famille")
    List<StatistiqueCount> countInstrumentsByFamille();
}
