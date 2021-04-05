package bakery.entities.bakedFoods;

import bakery.entities.bakedFoods.interfaces.BakedFood;

import static bakery.common.ExceptionMessages.*;

public abstract class BaseFood implements BakedFood {
    private String name;
    private double portion;
    private double price;

    protected BaseFood(String name, double portion, double price) {
        this.setName(name);
        this.setPortion(portion);
        this.setPrice(price);
    }

    public void setName(String name) {
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException(INVALID_NAME);
        }
        this.name = name;
    }

    public void setPortion(double portion) {
        invalidNumber(portion, INVALID_PORTION);
        this.portion = portion;
    }

    public void setPrice(double price) {
        invalidNumber(price, INVALID_PRICE);
        this.price = price;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPortion() {
        return this.portion;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    private void invalidNumber(double number, String MESSAGE) {
        if (number <= 0) {
            throw new IllegalArgumentException(MESSAGE);
        }
    }

    @Override
    public String toString() {
        return this.getName() + ": " + String.format("%.2f", getPortion()) +
                "g - " + String.format("%.2f", getPrice());
    }
}
