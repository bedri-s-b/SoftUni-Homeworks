package bakery.entities.tables;

import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.drinks.BaseDrink;
import bakery.entities.drinks.interfaces.Drink;
import bakery.entities.tables.interfaces.Table;
import bakery.repositories.FoodRepositoryImpl;
import bakery.repositories.interfaces.FoodRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

import static bakery.common.ExceptionMessages.INVALID_NUMBER_OF_PEOPLE;
import static bakery.common.ExceptionMessages.INVALID_TABLE_CAPACITY;

public abstract class BaseTable implements Table {

    private Collection<BakedFood> foodOrders;
    private Collection<BaseDrink> drinkOrders;
    private int tableNumber;
    private int capacity;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReserved;
    private double price;

    protected BaseTable(int tableNumber, int capacity, double pricePerPerson) {
        this.setTableNumber(tableNumber);
        this.setCapacity(capacity);
        this.setPricePerPerson(pricePerPerson);
        this.foodOrders = new ArrayList<>();
        this.drinkOrders = new ArrayList<>();
        this.isReserved = false;
    }

    private void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    private void setCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException(INVALID_TABLE_CAPACITY);
        }
        this.capacity = capacity;
    }

    private void setPricePerPerson(double pricePerPerson) {
        if (pricePerPerson <= 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.pricePerPerson = pricePerPerson;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }

    public void setPrice(double pricePerPerson, int numberOfPeople) {
        this.price = price * pricePerPerson;
    }

    @Override
    public int getTableNumber() {
        return this.tableNumber;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public int getNumberOfPeople() {
        return this.numberOfPeople;
    }

    @Override
    public double getPricePerPerson() {
        return this.pricePerPerson;
    }

    @Override
    public boolean isReserved() {
        return this.isReserved;
    }


    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void reserve(int numberOfPeople) {
        setNumberOfPeople(numberOfPeople);
        setPrice(pricePerPerson, numberOfPeople);
        isReserved = true;
    }

    @Override
    public void orderFood(BakedFood food) {
        foodOrders.add(food);
    }

    @Override
    public void orderDrink(Drink drink) {
        drinkOrders.add((BaseDrink) drink);
    }

    @Override
    public double getBill() {
        double bill = 0;
        for (BakedFood bakedFood : foodOrders) {
            bill += bakedFood.getPrice();
        }

        for (Drink drink : drinkOrders) {
            bill += drink.getPrice();
        }
        return bill + price;
    }

    @Override
    public void clear() {
        foodOrders.clear();
        drinkOrders.clear();
        numberOfPeople = 0;
        price = 0;
        isReserved = false;
    }

    @Override
    public String getFreeTableInfo() {
        return String.format("Table: %d%nType: %s%nCapacity: %d%nPrice per Person: %.2f%n", getTableNumber(), getClass().getSimpleName(), getCapacity(), getPricePerPerson());
    }

}
