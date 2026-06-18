package com.ecolemusique.repository;

import com.ecolemusique.dto.StatistiqueCount;
import com.ecolemusique.entity.Cours;
import com.ecolemusique.enumeration.Jour;
import com.ecolemusique.enumeration.Niveau;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CoursRepository extends JpaRepository<Cours, Long> {

    List<Cours> findByJour(Jour jour);

    List<Cours> findByNiveau(Niveau niveau);

    List<Cours> findByJourAndNiveau(Jour jour, Niveau niveau);

    @Query("select new com.ecolemusique.dto.StatistiqueCount(cast(c.jour as string), count(c)) "
            + "from Cours c group by c.jour")
    List<StatistiqueCount> countCoursByJour();
}
