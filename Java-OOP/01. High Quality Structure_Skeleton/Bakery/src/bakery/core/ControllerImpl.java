package bakery.core;

import bakery.common.ExceptionMessages;
import bakery.common.OutputMessages;
import bakery.core.interfaces.Controller;
import bakery.entities.bakedFoods.Bread;
import bakery.entities.bakedFoods.Cake;
import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.drinks.Tea;
import bakery.entities.drinks.Water;
import bakery.entities.drinks.interfaces.Drink;
import bakery.entities.tables.BaseTable;
import bakery.entities.tables.InsideTable;
import bakery.entities.tables.OutsideTable;
import bakery.entities.tables.interfaces.Table;
import bakery.repositories.DrinkRepositoryImpl;
import bakery.repositories.FoodRepositoryImpl;
import bakery.repositories.TableRepositoryImpl;
import bakery.repositories.interfaces.*;

import java.util.Collection;

import static bakery.common.ExceptionMessages.FOOD_OR_DRINK_EXIST;
import static bakery.common.ExceptionMessages.TABLE_EXIST;
import static bakery.common.OutputMessages.*;

public class ControllerImpl implements Controller {

    private FoodRepository<BakedFood> foodRepository;
    private DrinkRepository<Drink> drinkRepository;
    private TableRepository<Table> tableRepository;
    private double totalIncome;


    public ControllerImpl(FoodRepository<BakedFood> foodRepository, DrinkRepository<Drink> drinkRepository, TableRepository<Table> tableRepository) {
        this.foodRepository = new FoodRepositoryImpl();
        this.drinkRepository = new DrinkRepositoryImpl();
        this.tableRepository = new TableRepositoryImpl();
    }


    @Override
    public String addFood(String type, String name, double price) {
        BakedFood bakedFood = foodRepository.getByName(name);

        if (bakedFood != null) {
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
        } else {
            if (type.equals("Bread")) {
                bakedFood = new Bread(name, price);
                foodRepository.add(bakedFood);
                return String.format(FOOD_ADDED, bakedFood.getName(), bakedFood.getClass().getSimpleName());
            } else if (type.equals("Cake")) {
                bakedFood = new Cake(name, price);
                foodRepository.add(bakedFood);
                return String.format(FOOD_ADDED, bakedFood.getName(), bakedFood.getClass().getSimpleName());
            }

        }
        return null;
    }

    @Override
    public String addDrink(String type, String name, int portion, String brand) {
        Drink drink = drinkRepository.getByNameAndBrand(name, brand);

        if (drink != null) {
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
        } else {
            if (type.equals("Tea")) {
                drink = new Tea(name, portion, brand);
                drinkRepository.add(drink);
                return String.format(DRINK_ADDED, name, brand);
            } else if (type.equals("Water")) {
                drink = new Water(name, portion, brand);
                drinkRepository.add(drink);
                return String.format(DRINK_ADDED, name, brand);
            }

        }
        return null;
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table = tableRepository.getByNumber(tableNumber);

        if (table != null) {
            throw new IllegalArgumentException(String.format(TABLE_EXIST, tableNumber));
        } else {
            if (type.equals("InsideTable")) {
                table = new InsideTable(tableNumber, capacity);
                tableRepository.add(table);
                return String.format(TABLE_ADDED, tableNumber);
            } else if (type.equals("OutsideTable")) {
                table = new OutsideTable(tableNumber, capacity);
                tableRepository.add(table);
                return String.format(TABLE_ADDED, tableNumber);
            }

        }
        return null;
    }

    @Override
    public String reserveTable(int numberOfPeople) {
        Collection<Table> all = tableRepository.getAll();

        for (Table table : all) {
            boolean reserved = table.isReserved();
            if (!reserved) {
                ((BaseTable) table).setNumberOfPeople(numberOfPeople);
                if (table.getNumberOfPeople() != 0) {
                    table.reserve(numberOfPeople);
                    return String.format(TABLE_RESERVED, table.getTableNumber(), numberOfPeople);
                }

            }
        }
        return String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople);
    }

    @Override
    public String orderFood(int tableNumber, String foodName) {
        Table table = tableRepository.getByNumber(tableNumber);

        if (table == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        } else {
            BakedFood food = foodRepository.getByName(foodName);
            if (food == null) {
                return String.format(NONE_EXISTENT_FOOD, foodName);
            } else {
                tableRepository.getByNumber(tableNumber).orderFood(food);
                return String.format(FOOD_ORDER_SUCCESSFUL, tableNumber, foodName);
            }
        }
    }

    @Override
    public String orderDrink(int tableNumber, String drinkName, String drinkBrand) {
        Table table = tableRepository.getByNumber(tableNumber);

        if (table == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        } else {
            Drink drink = drinkRepository.getByNameAndBrand(drinkName, drinkBrand);
            if (drink == null) {
                return String.format(NON_EXISTENT_DRINK, drinkName, drinkBrand);
            } else
                tableRepository.getByNumber(tableNumber).orderDrink(drink);
            return String.format(DRINK_ORDER_SUCCESSFUL, tableNumber, drinkName, drinkBrand);
        }
    }

    @Override
    public String leaveTable(int tableNumber) {
        double bill;
        Table table = tableRepository.getByNumber(tableNumber);
        if(table == null) {
            throw new IllegalArgumentException(String.format(WRONG_TABLE_NUMBER, tableNumber));
        }
        bill = table.getBill();
        totalIncome += bill;
        table.clear();
        return String.format(BILL, tableNumber, bill);
    }

    @Override
    public String getFreeTablesInfo() {
        StringBuilder builder = new StringBuilder();
        for (Table table : tableRepository.getAll()) {
            if(!table.isReserved()) {
                builder.append(table.getFreeTableInfo()).append(System.lineSeparator());
            }
        }
        return builder.toString().trim();
    }


    @Override
    public String getTotalIncome() {
        return String.format(TOTAL_INCOME, totalIncome);
    }
}
