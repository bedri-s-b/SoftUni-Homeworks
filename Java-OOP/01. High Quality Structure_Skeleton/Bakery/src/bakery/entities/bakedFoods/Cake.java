package bakery.entities.bakedFoods;

public class Cake extends BaseFood {
    final static Integer INITIAL_BREAD_PORTION = 245;

    public Cake(String name, double price) {
        super(name, INITIAL_BREAD_PORTION, price);
    }
}
