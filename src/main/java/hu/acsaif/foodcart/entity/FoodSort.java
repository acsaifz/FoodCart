package hu.acsaif.foodcart.entity;

public enum FoodSort {
    ID_ASC("Id ascending"),
    ID_DESC("Id descending"),
    NAME_ASC("Name ascending"),
    NAME_DESC("Name descending"),
    TYPE_ASC("Type ascending"),
    TYPE_DESC("Type descending"),
    PRICE_ASC("Price ascending"),
    PRICE_DESC("Price descending");

    private final String displayValue;

    private FoodSort(String displayValue){
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
