package ru.gaplikov.CarDealershipInformationSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gaplikov.CarDealershipInformationSystem.models.Parts;
import ru.gaplikov.CarDealershipInformationSystem.services.PartsService;

import javax.validation.Valid;

@Controller
@RequestMapping("/parts")
public class PartsController {

    private final PartsService partsService;

    @Autowired
    public PartsController(PartsService partsService) {
        this.partsService = partsService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("parts", partsService.findAll());
        return "parts/index";
    }

    @GetMapping("/new")
    public String showNewForm(@ModelAttribute("parts") Parts parts) {
        return "parts/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("parts") @Valid Parts parts,
                         BindingResult bindingResult) {
        //cmodelValidator.validate(cmodel, bindingResult);

        if (bindingResult.hasErrors()) {
            return "parts/new";
        }
        partsService.save(parts);
        return "redirect:/parts";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(Model model, @PathVariable("id") int id) {
        model.addAttribute("parts", partsService.findOne(id));
        return "parts/update";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("parts") @Valid Parts parts,
                               BindingResult bindingResult,
                               @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {
            return "parts/update";
        }

        partsService.update(id, parts);
        return "redirect:/parts";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        partsService.delete(id);
        return "redirect:/parts";
    }
}
