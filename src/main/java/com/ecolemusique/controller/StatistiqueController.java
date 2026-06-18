package com.ecolemusique.controller;

import com.ecolemusique.dto.StatistiqueCount;
import com.ecolemusique.service.CoursService;
import com.ecolemusique.service.InstrumentService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistiques")
public class StatistiqueController {

    private final CoursService coursService;
    private final InstrumentService instrumentService;

    public StatistiqueController(CoursService coursService, InstrumentService instrumentService) {
        this.coursService = coursService;
        this.instrumentService = instrumentService;
    }

    @GetMapping("/cours-par-jour")
    public List<StatistiqueCount> coursParJour() {
        return coursService.countByJour();
    }

    @GetMapping("/instruments-par-famille")
    public List<StatistiqueCount> instrumentsParFamille() {
        return instrumentService.countByFamille();
    }
}
