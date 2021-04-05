package bakery.repositories;

import bakery.entities.tables.interfaces.Table;
import bakery.repositories.interfaces.TableRepository;

import java.util.*;

public class TableRepositoryImpl implements TableRepository<Table> {
    private List<Table> models;

    public TableRepositoryImpl() {
        this.models = new ArrayList<>();
    }

    @Override
    public Table getByNumber(int number) {
        return models.stream().filter(t -> t.getTableNumber() == number).findFirst().orElse(null);
    }

    @Override
    public Collection<Table> getAll() {
        return Collections.unmodifiableCollection(models);
    }

    @Override
    public void add(Table table) {
        models.add(table);
    }
}
