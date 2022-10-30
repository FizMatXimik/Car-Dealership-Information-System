package ru.gaplikov.CarDealershipInformationSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gaplikov.CarDealershipInformationSystem.models.Cmodel;
import ru.gaplikov.CarDealershipInformationSystem.services.CmodelService;

import javax.validation.Valid;

@Controller
@RequestMapping("/car_models")
public class CmodelController {

    private final CmodelService cmodelService;

    @Autowired
    public CmodelController(CmodelService cmodelService) {
        this.cmodelService = cmodelService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("cmodels", cmodelService.findAll());
        return "carModel/index";
    }

    @GetMapping("/new")
    public String showNewForm(@ModelAttribute("cmodel") Cmodel cmodel) {
        return "carModel/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("cmodel") @Valid Cmodel cmodel,
                                  BindingResult bindingResult) {
        //cmodelValidator.validate(cmodel, bindingResult);

        if (bindingResult.hasErrors()) {
            return "carModel/new";
        }
        cmodelService.save(cmodel);
        return "redirect:/car_models";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(Model model, @PathVariable("id") int id) {
        model.addAttribute("cmodel", cmodelService.findOne(id));
        return "carModel/update";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("cmodel") @Valid Cmodel cmodel,
                               BindingResult bindingResult,
                               @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {
            return "carModel/update";
        }

        cmodelService.update(id, cmodel);
        return "redirect:/car_models";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        cmodelService.delete(id);
        return "redirect:/car_models";
    }

}
