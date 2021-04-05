package bakery.entities.bakedFoods;

import bakery.entities.bakedFoods.interfaces.BakedFood;

public class Bread extends BaseFood {
    final static Integer INITIAL_BREAD_PORTION = 200;

    public Bread(String name, double price) {
        super(name, INITIAL_BREAD_PORTION,price);
    }
}
