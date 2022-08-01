package hu.acsaif.foodcart.controller;

import hu.acsaif.foodcart.entity.Food;
import hu.acsaif.foodcart.entity.FoodSort;
import hu.acsaif.foodcart.entity.FoodType;
import hu.acsaif.foodcart.service.FoodCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String getHomePage(@RequestParam(required = false, defaultValue = "", name = "nameFilter") String nameFilter,
                              @RequestParam(required = false, defaultValue = "", name = "typeFilter") List<FoodType> typeFilters,
                              @RequestParam(required = false, defaultValue = "", name = "priceFrom") Integer priceFrom,
                              @RequestParam(required = false, defaultValue = "", name = "priceTo") Integer priceTo,
                              @RequestParam(required = false, defaultValue = "", name = "sortBy") FoodSort sortBy,
                              Model model){
        List<Food> foods = foodCart.sortBy(sortBy);
        foods = foodCart.filterBy(foods,nameFilter,typeFilters,priceFrom,priceTo);

        model.addAttribute("foodCart", foods);
        model.addAttribute("sumOfFoods", foodCart.getSize());
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
        model.addAttribute("sumOfFoods", foodCart.getSize());
        model.addAttribute("food", new Food());
        return "save";
    }

    @PostMapping("/add")
    public String addFood(@ModelAttribute(name = "food") Food food){
        foodCart.addOrUpdate(food);
        return "redirect:/foodCart";
    }

    @GetMapping("/update")
    public String showUpdateForm(@RequestParam(name = "foodId") int id,Model model) {
        Food food = foodCart.getById(id);
        if (food == null){
            return "redirect:/foodCart";
        }
        model.addAttribute("sumOfFoods", foodCart.getSize());
        model.addAttribute("food", food);
        return "save";
    }




}
