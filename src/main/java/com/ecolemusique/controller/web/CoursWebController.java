package com.ecolemusique.controller.web;

import com.ecolemusique.entity.Cours;
import com.ecolemusique.entity.Prof;
import com.ecolemusique.enumeration.Jour;
import com.ecolemusique.enumeration.Niveau;
import com.ecolemusique.service.CoursService;
import com.ecolemusique.service.ProfService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cours")
public class CoursWebController {

    private final CoursService coursService;
    private final ProfService profService;

    public CoursWebController(CoursService coursService, ProfService profService) {
        this.coursService = coursService;
        this.profService = profService;
    }

    @GetMapping
    public String list(
            @RequestParam(required = false) Jour jour,
            @RequestParam(required = false) Niveau niveau,
            Model model) {
        addFormAttributes(model);
        model.addAttribute("listeCours", coursService.findAll(jour, niveau));
        model.addAttribute("profs", profService.findAll());
        model.addAttribute("jours", Jour.values());
        model.addAttribute("niveaux", Niveau.values());
        model.addAttribute("selectedJour", jour);
        model.addAttribute("selectedNiveau", niveau);
        return "cours";
    }

    @PostMapping
    public String create(
            @Valid @ModelAttribute("cours") Cours cours,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        resolveProf(cours);
        if (bindingResult.hasErrors()) {
            fillModel(model);
            return "cours";
        }
        coursService.save(cours);
        redirectAttributes.addFlashAttribute("success", "Ajouté avec succès !");
        redirectAttributes.addFlashAttribute("toastType", "success");
        return "redirect:/cours";
    }

    @PostMapping("/{id}")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute("c") Cours cours,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        resolveProf(cours);
        if (bindingResult.hasErrors()) {
            fillModel(model);
            return "cours";
        }
        coursService.update(id, cours);
        redirectAttributes.addFlashAttribute("success", "Modifié avec succès !");
        redirectAttributes.addFlashAttribute("toastType", "primary");
        return "redirect:/cours";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        coursService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Supprimé avec succès !");
        redirectAttributes.addFlashAttribute("toastType", "danger");
        return "redirect:/cours";
    }

    private void fillModel(Model model) {
        addFormAttributes(model);
        model.addAttribute("listeCours", coursService.findAll(null, null));
        model.addAttribute("profs", profService.findAll());
        model.addAttribute("jours", Jour.values());
        model.addAttribute("niveaux", Niveau.values());
    }

    private void addFormAttributes(Model model) {
        if (!model.containsAttribute("cours")) {
            model.addAttribute("cours", newCours());
        }
        if (!model.containsAttribute("newCours")) {
            model.addAttribute("newCours", newCours());
        }
    }

    private void resolveProf(Cours cours) {
        if (cours.getProf() != null && cours.getProf().getId() != null) {
            cours.setProf(profService.findById(cours.getProf().getId()).orElse(null));
        }
    }

    private Cours newCours() {
        Cours cours = new Cours();
        cours.setProf(new Prof());
        return cours;
    }
}
