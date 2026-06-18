package com.ecolemusique.controller.web;

import com.ecolemusique.entity.Instrument;
import com.ecolemusique.enumeration.FamilleInstrument;
import com.ecolemusique.service.InstrumentService;
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
@RequestMapping("/instruments")
public class InstrumentWebController {

    private final InstrumentService instrumentService;

    public InstrumentWebController(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    @GetMapping
    public String list(
            @RequestParam(required = false) FamilleInstrument famille,
            @RequestParam(required = false) Boolean disponibilite,
            Model model) {
        addFormAttributes(model);
        model.addAttribute("instruments", instrumentService.findAll(famille, disponibilite));
        model.addAttribute("familles", FamilleInstrument.values());
        model.addAttribute("selectedFamille", famille);
        model.addAttribute("selectedDisponibilite", disponibilite);
        return "instruments";
    }

    @PostMapping
    public String create(
            @Valid @ModelAttribute("instrument") Instrument instrument,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            addFormAttributes(model);
            model.addAttribute("instruments", instrumentService.findAll(null, null));
            model.addAttribute("familles", FamilleInstrument.values());
            return "instruments";
        }
        instrumentService.save(instrument);
        redirectAttributes.addFlashAttribute("success", "Ajouté avec succès !");
        redirectAttributes.addFlashAttribute("toastType", "success");
        return "redirect:/instruments";
    }

    @PostMapping("/{id}")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute("instrument") Instrument instrument,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            addFormAttributes(model);
            model.addAttribute("instruments", instrumentService.findAll(null, null));
            model.addAttribute("familles", FamilleInstrument.values());
            return "instruments";
        }
        instrumentService.update(id, instrument);
        redirectAttributes.addFlashAttribute("success", "Modifié avec succès !");
        redirectAttributes.addFlashAttribute("toastType", "primary");
        return "redirect:/instruments";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        instrumentService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Supprimé avec succès !");
        redirectAttributes.addFlashAttribute("toastType", "danger");
        return "redirect:/instruments";
    }

    private void addFormAttributes(Model model) {
        if (!model.containsAttribute("instrument")) {
            model.addAttribute("instrument", new Instrument());
        }
        if (!model.containsAttribute("newInstrument")) {
            model.addAttribute("newInstrument", new Instrument());
        }
    }
}
