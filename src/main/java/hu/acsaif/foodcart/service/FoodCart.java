package hu.acsaif.foodcart.service;

import hu.acsaif.foodcart.entity.Food;
import hu.acsaif.foodcart.entity.FoodSort;
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

    public List<Food> sortBy(FoodSort sortBy){
        List<Food> foods = new ArrayList<>(cart);

        if(sortBy == null){
            return foods;
        }

        switch (sortBy) {
            case ID_ASC -> foods.sort((Food f1, Food f2) -> f1.getId() - f2.getId());
            case ID_DESC -> foods.sort((Food f1, Food f2) -> f2.getId() - f1.getId());
            case NAME_ASC -> foods.sort((Food f1, Food f2) -> f1.getFoodName().compareTo(f2.getFoodName()));
            case NAME_DESC -> foods.sort((Food f1, Food f2) -> f2.getFoodName().compareTo(f1.getFoodName()));
            case TYPE_ASC -> foods.sort((Food f1, Food f2) -> f1.getFoodType().compareTo(f2.getFoodType()));
            case TYPE_DESC -> foods.sort((Food f1, Food f2) -> f2.getFoodType().compareTo(f1.getFoodType()));
            case PRICE_ASC -> foods.sort((Food f1, Food f2) -> f1.getFoodPrice() - f2.getFoodPrice());
            case PRICE_DESC -> foods.sort((Food f1, Food f2) -> f2.getFoodPrice() - f1.getFoodPrice());
        }

        return foods;
    }

    public List<Food> filterBy(List<Food> foods, String nameFilter, List<FoodType> typeFilters,Integer priceFrom, Integer priceTo){
        if (!nameFilter.isBlank()) {
            foods = foods.stream().filter(food -> food.getFoodName().toLowerCase().contains(nameFilter.toLowerCase())).toList();
        }

        if (typeFilters.size() > 0){
            foods = foods.stream().filter(food -> {
                for (FoodType foodType: typeFilters){
                    if (foodType == food.getFoodType()){
                        return true;
                    }
                }
                return false;
            }).toList();
        }

        if (priceFrom != null){
            foods = foods.stream().filter(food -> food.getFoodPrice() >= priceFrom).toList();
        }

        if (priceTo != null){
            foods = foods.stream().filter(food -> food.getFoodPrice() <= priceTo).toList();
        }

        return foods;
    }
}
