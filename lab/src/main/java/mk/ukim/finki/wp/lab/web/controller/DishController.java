package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/dishes")
public class DishController {
    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping()
    public String getDishesPage(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", error);
        }

        model.addAttribute("dishes", dishService.listDishes());

        return "listDishes";
    }

    @GetMapping("/add")
    public String saveDish(@RequestParam String dishId, @RequestParam String name, @RequestParam String cuisine, @RequestParam int preparationTime) {
        dishService.create(dishId, name, cuisine, preparationTime);
        return "redirect:/dishes";
    }

    @GetMapping("/edit/{id}")
    public String editDish(@PathVariable Long id, @RequestParam String dishId, @RequestParam String name, @RequestParam String cuisine, @RequestParam int preparationTime){
        dishService.update(id, dishId, name, cuisine, preparationTime);
        return "redirect:/dishes";
    }

    @GetMapping("/delete/{id}")
    public String deleteDish(@PathVariable Long id){
        dishService.delete(id);
        return "redirect:/dishes";
    }

    @GetMapping("/dish-form/{id}")
    public String getEditDishForm(@PathVariable Long id, Model model){
        if(id == null){
            return "redirect:/dishes?error=DishNotFound";
        }
        model.addAttribute("dish", dishService.findById(id));
        model.addAttribute("flag", 1);
        return "dish-form";
    }

    @GetMapping("/dish-form")
    public String getAddDishPage(Model model){
        model.addAttribute("flag", 0);
        return "dish-form";
    }
}
