package hu.acsaif.foodcart.controller;

import hu.acsaif.foodcart.entity.Food;
import hu.acsaif.foodcart.service.FoodCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/foodCart")
public class FoodCartController {
    private FoodCart foodCart;

    @Autowired
    public void setFoodCart(FoodCart foodCart){
        this.foodCart = foodCart;
    }

    @GetMapping("")
    public String getHomePage(@RequestParam(required = false, defaultValue = "", name = "filter") String filter,
                              @RequestParam(required = false, defaultValue = "", name = "sortBy") String sortBy,
                              Model model){
        List<Food> foods = new ArrayList<>(foodCart.getCart());

        switch (sortBy) {
            case "idAsc" -> foods.sort((Food f1, Food f2) -> f1.getId() - f2.getId());
            case "idDesc" -> foods.sort((Food f1, Food f2) -> f2.getId() - f1.getId());
            case "nameAsc" -> foods.sort((Food f1, Food f2) -> f1.getFoodName().compareTo(f2.getFoodName()));
            case "nameDesc" -> foods.sort((Food f1, Food f2) -> f2.getFoodName().compareTo(f1.getFoodName()));
            case "typeAsc" -> foods.sort((Food f1, Food f2) -> f1.getFoodType().compareTo(f2.getFoodType()));
            case "typeDesc" -> foods.sort((Food f1, Food f2) -> f2.getFoodType().compareTo(f1.getFoodType()));
            case "priceAsc" -> foods.sort((Food f1, Food f2) -> f1.getFoodPrice() - f2.getFoodPrice());
            case "priceDesc" -> foods.sort((Food f1, Food f2) -> f2.getFoodPrice() - f1.getFoodPrice());
        }

        if (!filter.isBlank()) {
             foods = foods.stream().filter(food -> food.getFoodName().toLowerCase().contains(filter.toLowerCase())).toList();
        }

        model.addAttribute("foodCart", foods);
        model.addAttribute("sumOfFoods", foodCart.getCart().size());
        return "index";
    }

    @GetMapping("/delete")
    public String deleteFood(@RequestParam(name = "foodId") int id){
        Food food = foodCart.getById(id);
        if (food != null){
            foodCart.remove(food);
        }
        return "redirect:/foodCart";
    }

    @GetMapping("/add")
    public String showAddForm(Model model){
        model.addAttribute("sumOfFoods", foodCart.getCart().size());
        model.addAttribute("food", new Food());
        return "save";
    }

    @PostMapping("/add")
    public String addFood(@ModelAttribute(name = "food") Food food, Model model){
        foodCart.addOrUpdate(food);
        return "redirect:/foodCart";
    }

    @GetMapping("/update")
    public String showUpdateForm(@RequestParam(name = "foodId") int id,Model model) {
        Food food = foodCart.getById(id);
        if (food == null){
            return "redirect:/foodCart";
        }
        model.addAttribute("sumOfFoods", foodCart.getCart().size());
        model.addAttribute("food", food);
        return "save";
    }




}
