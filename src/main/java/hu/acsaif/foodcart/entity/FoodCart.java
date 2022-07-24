package hu.acsaif.foodcart.entity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class FoodCart {
    private List<Food> cart;

    public FoodCart() {
        addOrUpdate(new Food("Cabbage",FoodType.VEGETABLE,500));
        Food.updateIdCount();
        addOrUpdate(new Food("Grapes",FoodType.FRUIT,740));
        Food.updateIdCount();
        addOrUpdate(new Food("Pepper", FoodType.SPICE,225));
        Food.updateIdCount();
        addOrUpdate(new Food("Beer", FoodType.DRINK,150));
        Food.updateIdCount();
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

        for (Food foodInCart: cart) {

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
            cart.add(food);
            Food.updateIdCount();
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
}
