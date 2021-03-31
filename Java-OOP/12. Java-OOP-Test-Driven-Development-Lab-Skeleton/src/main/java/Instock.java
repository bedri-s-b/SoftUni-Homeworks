import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Instock implements ProductStock {
    private Map<String, Product> products;

    public Instock() {
        this.products = new LinkedHashMap<>();
    }

    @Override
    public Integer getCount() {
        return this.products.size();
    }

    @Override
    public Boolean contains(Product product) {
        return this.products.containsKey(product.getLabel());
    }

    @Override
    public void add(Product product) {
        if (!contains(product)) {
            this.products.put(product.getLabel(), product);
        }
    }

    @Override
    public void changeQuantity(String label, int quantity) {
        validateLabelExists(label);
        Product product = this.products.get(label);
        product.setQuantity(product.getQuantity() + quantity);
    }

    @Override
    public Product find(int index) {
        return new ArrayList<>(this.products.values()).get(index);
    }

    @Override
    public Product findByLabel(String label) {
        validateLabelExists(label);
        return products.get(label);
    }

    @Override
    public Iterable<Product> findFirstByAlphabeticalOrder(int count) {
        return fetchFirsCountCompared(count, Comparator.comparing(Product::getLabel));
    }

    @Override
    public Iterable<Product> findAllInRange(double lo, double hi) {
        return this.products.values()
                .stream()
                .filter(p -> p.getPrice() > lo && p.getPrice() <= hi)
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllByPrice(double price) {
        return getAllMatching(p -> p.getPrice() == price);
    }

    @Override
    public Iterable<Product> findFirstMostExpensiveProducts(int count) {
        return fetchFirsCountCompared(count, Comparator.comparingDouble(Product::getPrice).reversed());
    }

    @Override
    public Iterable<Product> findAllByQuantity(int quantity) {
        return getAllMatching(p -> p.getQuantity() == quantity);
    }

    @Override
    public Iterator<Product> iterator() {
        return products.values().iterator();
    }

    private Iterable<Product> fetchFirsCountCompared(int count, Comparator<Product> comparator) {
        if (count <= 0 || this.getCount() < count) {
            return new ArrayList<>();
        }
        return this.products.values()
                .stream()
                .sorted(comparator)
                .limit(count)
                .collect(Collectors.toList());
    }

    private void validateLabelExists(String label) {
        if (!this.products.containsKey(label)) {
            throw new IllegalArgumentException();
        }
    }

    private Iterable<Product> getAllMatching(Predicate<Product> predicate) {
        return products.values().stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
