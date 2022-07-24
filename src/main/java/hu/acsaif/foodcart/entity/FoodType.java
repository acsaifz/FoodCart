package hu.acsaif.foodcart.entity;

public enum FoodType {
    VEGETABLE("Vegetable"),
    FRUIT("Fruit"),
    SPICE("Spice"),
    DRINK("Drink");

    private final String displayValue;

    private FoodType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
