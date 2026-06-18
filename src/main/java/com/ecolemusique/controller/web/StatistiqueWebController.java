package com.ecolemusique.controller.web;

import com.ecolemusique.dto.StatistiqueCount;
import com.ecolemusique.service.CoursService;
import com.ecolemusique.service.InstrumentService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatistiqueWebController {

    private final CoursService coursService;
    private final InstrumentService instrumentService;

    public StatistiqueWebController(CoursService coursService, InstrumentService instrumentService) {
        this.coursService = coursService;
        this.instrumentService = instrumentService;
    }

    @GetMapping("/statistiques")
    public String statistiques(Model model) {
        List<StatistiqueCount> coursParJour = coursService.countByJour();
        List<StatistiqueCount> instrumentsParFamille = instrumentService.countByFamille();

        model.addAttribute("coursLabels", coursParJour.stream().map(StatistiqueCount::libelle).toList());
        model.addAttribute("coursValues", coursParJour.stream().map(StatistiqueCount::total).toList());
        model.addAttribute("instrumentLabels", instrumentsParFamille.stream().map(StatistiqueCount::libelle).toList());
        model.addAttribute("instrumentValues", instrumentsParFamille.stream().map(StatistiqueCount::total).toList());
        return "statistiques";
    }
}
