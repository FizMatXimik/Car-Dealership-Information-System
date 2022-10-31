package ru.gaplikov.CarDealershipInformationSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gaplikov.CarDealershipInformationSystem.models.Car;
import ru.gaplikov.CarDealershipInformationSystem.models.Parts;
import ru.gaplikov.CarDealershipInformationSystem.services.CarService;
import ru.gaplikov.CarDealershipInformationSystem.services.CmodelService;
import ru.gaplikov.CarDealershipInformationSystem.services.ManagerService;
import ru.gaplikov.CarDealershipInformationSystem.services.PartsService;

import javax.validation.Valid;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;
    private final CmodelService cmodelService;
    private final ManagerService managerService;
    private final PartsService partsService;

    @Autowired
    public CarController(CarService carService, CmodelService cmodelService, ManagerService managerService, PartsService partsService) {
        this.carService = carService;
        this.cmodelService = cmodelService;
        this.managerService = managerService;
        this.partsService = partsService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("cars", carService.findAll());
        return "car/index";
    }

    @GetMapping("/new")
    public String showNewForm(Model model, @ModelAttribute("car") Car car) {
        model.addAttribute("cmodels", cmodelService.findAll());
        model.addAttribute("managers", managerService.findAll());
        return "car/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("car") @Valid Car car,
                         BindingResult bindingResult) {
        //cmodelValidator.validate(cmodel, bindingResult);

        if (bindingResult.hasErrors()) {
            return "car/new";
        }
        carService.save(car);
        return "redirect:/cars";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(Model model, @PathVariable("id") int id) {
        model.addAttribute("car", carService.findOne(id));
        model.addAttribute("cmodels", cmodelService.findAll());
        model.addAttribute("managers", managerService.findAll());
        return "car/update";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("car") @Valid Car car,
                               BindingResult bindingResult,
                               @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {
            return "car/update";
        }

        carService.update(id, car);
        return "redirect:/cars";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        carService.delete(id);
        return "redirect:/cars";
    }

    @GetMapping("/add_parts/{id}")
    public String addParts( Model model, @PathVariable("id") int id, @ModelAttribute("part") Parts part, @ModelAttribute("car") Car car) {
        model.addAttribute("parts", partsService.findAll());
        return "car/add_parts";
    }

    @PostMapping("/add_parts/{id}")
    public String updateParts(@ModelAttribute("parts") Parts parts,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {
            return "car/add_parts";
        }
        carService.updateParts(id, parts.getId(), true);
        return "redirect:/cars";
    }

    @GetMapping("/del_parts/{id}")
    public String delParts( Model model, @PathVariable("id") int id, @ModelAttribute("part") Parts part, @ModelAttribute("car") Car car) {
        model.addAttribute("parts", carService.findOne(id).getParts());
        return "car/del_parts";
    }

    @PostMapping("/del_parts/{id}")
    public String updatePartsDel(@ModelAttribute("parts") Parts parts,
                              BindingResult bindingResult,
                              @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {
            return "car/del_parts";
        }
        carService.updateParts(id, parts.getId(), false);
        return "redirect:/cars";
    }
}
