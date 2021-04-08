package viceCity.repositories;

import viceCity.models.guns.Gun;
import viceCity.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class GunRepository implements Repository<Gun> {
    private Map<String,Gun> models;

    public GunRepository() {
        this.models = new LinkedHashMap<>();
    }

    @Override
    public Collection<Gun> getModels() {
        return models.values();
    }

    @Override
    public void add(Gun model) {
        models.putIfAbsent(model.getName(),model);
    }

    @Override
    public boolean remove(Gun model) {
        Gun remove = models.remove(model.getName());
        return models.containsKey(model.getName());

    }

    @Override
    public Gun find(String name) {
        return models.get(name);
    }
}
