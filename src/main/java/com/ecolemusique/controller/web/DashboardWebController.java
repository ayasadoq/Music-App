package com.ecolemusique.controller.web;

import com.ecolemusique.service.CoursService;
import com.ecolemusique.service.InstrumentService;
import com.ecolemusique.service.ProfService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardWebController {

    private final CoursService coursService;
    private final InstrumentService instrumentService;
    private final ProfService profService;

    public DashboardWebController(CoursService coursService, InstrumentService instrumentService, ProfService profService) {
        this.coursService = coursService;
        this.instrumentService = instrumentService;
        this.profService = profService;
    }

    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {
        model.addAttribute("nbCours", coursService.findAll(null, null).size());
        model.addAttribute("nbInstruments", instrumentService.findAll(null, null).size());
        model.addAttribute("nbProfs", profService.findAll().size());
        return "dashboard";
    }
}
