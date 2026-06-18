package com.ecolemusique.controller.web;

import com.ecolemusique.entity.Prof;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/profs")
public class ProfWebController {

    private final ProfService profService;

    public ProfWebController(ProfService profService) {
        this.profService = profService;
    }

    @GetMapping
    public String list(Model model) {
        addFormAttributes(model);
        model.addAttribute("profs", profService.findAll());
        return "profs";
    }

    @PostMapping
    public String create(
            @Valid @ModelAttribute("prof") Prof prof,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            addFormAttributes(model);
            model.addAttribute("profs", profService.findAll());
            return "profs";
        }
        profService.save(prof);
        redirectAttributes.addFlashAttribute("success", "Ajouté avec succès !");
        redirectAttributes.addFlashAttribute("toastType", "success");
        return "redirect:/profs";
    }

    @PostMapping("/{id}")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute("prof") Prof prof,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            addFormAttributes(model);
            model.addAttribute("profs", profService.findAll());
            return "profs";
        }
        profService.update(id, prof);
        redirectAttributes.addFlashAttribute("success", "Modifié avec succès !");
        redirectAttributes.addFlashAttribute("toastType", "primary");
        return "redirect:/profs";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        profService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Supprimé avec succès !");
        redirectAttributes.addFlashAttribute("toastType", "danger");
        return "redirect:/profs";
    }

    private void addFormAttributes(Model model) {
        if (!model.containsAttribute("prof")) {
            model.addAttribute("prof", new Prof());
        }
        if (!model.containsAttribute("newProf")) {
            model.addAttribute("newProf", new Prof());
        }
    }
}
