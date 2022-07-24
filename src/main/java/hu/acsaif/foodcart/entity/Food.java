package hu.acsaif.foodcart.entity;

import java.util.Objects;


public class Food {
    private static int count = 1;
    private int id;
    private String foodName;
    private FoodType foodType;
    private int foodPrice;

    public Food(){
        id = count++;
    }

    public Food(String foodName, FoodType foodType, int foodPrice) {
        id = count++;
        this.foodName = foodName;
        this.foodType = foodType;
        this.foodPrice = foodPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public static void correctIdCount(){
        count--;
    }
    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", foodName='" + foodName + '\'' +
                ", foodType=" + foodType +
                ", foodPrice=" + foodPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return id == food.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
