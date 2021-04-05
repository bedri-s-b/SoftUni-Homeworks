package bakery.entities.drinks;

import bakery.entities.drinks.interfaces.Drink;

import static bakery.common.ExceptionMessages.*;

public abstract class BaseDrink implements Drink {
    private String name;
    private int portion;
    private double price;
    private String brand;

    protected BaseDrink(String name, int portion, double price, String brand) {
        this.setName(name);
        this.setPortion(portion);
        this.setPrice(price);
        this.setBrand(brand);
    }

    public void setName(String name) {
        invalidTextInput(name, INVALID_NAME);
        this.name = name;
    }

    public void setPortion(int portion) {
        invalidNumber(portion, INVALID_PORTION);
        this.portion = portion;
    }

    public void setPrice(double price) {
        invalidNumber(price, INVALID_PRICE);
        this.price = price;
    }

    public void setBrand(String brand) {
        invalidTextInput(brand, INVALID_BRAND);
        this.brand = brand;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getPortion() {
        return this.portion;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String getBrand() {
        return this.brand;
    }

    private void invalidNumber(double number, String MESSAGE) {
        if (number <= 0) {
            throw new IllegalArgumentException(MESSAGE);
        }
    }

    private void invalidTextInput(String text, String MESSAGE) {
        if (text.trim().isEmpty()) {
            throw new IllegalArgumentException(MESSAGE);
        }
    }

    @Override
    public String toString() {
        return String.format("%s %s - %dml - %.2flv", getName(), getBrand(), getPortion(), getPrice());
    }
}
