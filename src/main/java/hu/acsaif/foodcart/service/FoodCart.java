package hu.acsaif.foodcart.service;

import hu.acsaif.foodcart.entity.Food;
import hu.acsaif.foodcart.entity.FoodType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FoodCart {
    private int idCounter=0;
    private List<Food> cart;

    public FoodCart() {
        addOrUpdate(new Food("Cabbage", FoodType.VEGETABLE,500));
        addOrUpdate(new Food("Grapes",FoodType.FRUIT,740));
        addOrUpdate(new Food("Pepper", FoodType.SPICE,225));
        addOrUpdate(new Food("Beer", FoodType.DRINK,150));
    }

    public List<Food> getCart() {
        return cart;
    }

    public void setCart(List<Food> cart) {
        this.cart = cart;
    }

    public int getSize(){
        return cart.size();
    }

    public void addOrUpdate(Food food){
        if (cart == null){
            cart = new ArrayList<>();
        }

        if (cart.contains(food)){
            for (Food foodInCart: cart){
                if (foodInCart.equals(food)){
                    foodInCart.setFoodName(food.getFoodName());
                    foodInCart.setFoodType(food.getFoodType());
                    foodInCart.setFoodPrice(food.getFoodPrice());
                }
            }
        }else {
            food.setId(++idCounter);
            cart.add(food);
        }
    }

    public void remove(Food food){
        cart.remove(food);
    }

    public Food getById(int id){
        for (Food food: cart){
            if (food.getId()==id){
                return food;
            }
        }
        return null;
    }

    public List<Food> sortBy(String sortBy){
        List<Food> foods = new ArrayList<>(cart);

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

        return foods;
    }

    public void filterBy(List<Food> foods, String name, FoodType foodType,int priceFrom, int priceTo){
        if (!name.isBlank()) {
            foods = foods.stream().filter(food -> food.getFoodName().toLowerCase().contains(name.toLowerCase())).toList();
        }
    }
}
