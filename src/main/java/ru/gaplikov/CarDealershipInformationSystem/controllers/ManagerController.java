package ru.gaplikov.CarDealershipInformationSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gaplikov.CarDealershipInformationSystem.models.Manager;
import ru.gaplikov.CarDealershipInformationSystem.services.ManagerService;

import javax.validation.Valid;

@Controller
@RequestMapping("/managers")
public class ManagerController {

    private final ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("managers", managerService.findAll());
        return "manager/index";
    }

    @GetMapping("/new")
    public String showNewForm(@ModelAttribute("manager") Manager manager) {
        return "manager/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("manager") @Valid Manager manager,
                         BindingResult bindingResult) {
        //cmodelValidator.validate(cmodel, bindingResult);

        if (bindingResult.hasErrors()) {
            return "manager/new";
        }
        managerService.save(manager);
        return "redirect:/managers";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(Model model, @PathVariable("id") int id) {
        model.addAttribute("manager", managerService.findOne(id));
        return "manager/update";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("manager") @Valid Manager manager,
                               BindingResult bindingResult,
                               @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {
            return "manager/update";
        }

        managerService.update(id, manager);
        return "redirect:/managers";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        managerService.delete(id);
        return "redirect:/managers";
    }
}
