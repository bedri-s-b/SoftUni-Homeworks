package bakery.repositories;

import bakery.entities.drinks.interfaces.Drink;
import bakery.repositories.interfaces.DrinkRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DrinkRepositoryImpl implements DrinkRepository<Drink> {
    private List<Drink> models;

    public DrinkRepositoryImpl() {
        this.models = new ArrayList<>();
    }

    @Override
    public Drink getByNameAndBrand(String drinkName, String drinkBrand) {
        return models.stream().filter(drink -> drink.getName().equals(drinkName) && drink.getBrand().equals(drinkBrand)).findFirst().orElse(null);
    }

    @Override
    public Collection<Drink> getAll() {
        return Collections.unmodifiableCollection(models);
    }

    @Override
    public void add(Drink drink) {
        models.add(drink);
    }
}
