import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ProductStockTest {

    private Instock instock;
    private Product product;

    @Before
    public void setUp() {
        this.instock = new Instock();
        this.product = new Product("default_test_label", 100, 1);
    }

    // count
    @Test
    public void testGetCountShouldBeReturnAllWhenAddedTwo() {
        addProducts();
        assertEquals(Integer.valueOf(7), instock.getCount());
    }

    @Test
    public void testGetCountShouldReturnZeroWhenEmpty() {
        ProductStock instock = new Instock();

        assertEquals(Integer.valueOf(0), instock.getCount());
    }

    // add
    @Test
    public void testAddProductShouldContainTheProductByValidateContains() {
        this.instock.add(this.product);
        Boolean contains = this.instock.contains(this.product);
        assertNotNull(contains);
        assertTrue(contains);
    }

    @Test
    public void testContainsShouldBeReturnFalseWhenProductNotIsPresent() {
        this.instock.add(this.product);
        Boolean contains = this.instock.contains(new Product("test_label", 100, 30));
        assertNotNull(contains);
        assertFalse(contains);
    }

    @Test
    public void testContainsShouldBeReturnWhenEmpty() {
        Boolean contains = this.instock.contains(new Product("test_label", 100, 30));
        assertNotNull(contains);
        assertFalse(contains);
    }

    @Test
    public void testAddShouldNotAllowAdditionOfTheSameProduct() {
        this.instock.add(this.product);
        this.instock.add(this.product);
        Integer count = this.instock.getCount();
        assertNotNull(count);
        assertEquals(Integer.valueOf(1), count);
    }

    //Change
    @Test
    public void testChangeQuantityShouldBeUpdateTheProductQuantityValue() {
        this.instock.add(this.product);
        int quantityBeforeUp = this.product.getQuantity();
        this.instock.changeQuantity(this.product.getLabel(), 7);
        assertEquals(quantityBeforeUp + 7, product.getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeQuantityShouldFailForMissingProduct() {
        addProducts();
        int quantityBeforeUp = this.product.getQuantity();
        this.instock.changeQuantity(this.product.getLabel(), 7);
    }

    //find
    @Test
    public void testFindShouldBeReturnTheCorrect1thProductAdded() {
        assertFindReturnProduct(0, "test_label_1");
    }

    @Test
    public void testFindShouldBeReturnTheCorrect4thProductAdded() {
        assertFindReturnProduct(3, "test_label_4");
    }

    @Test
    public void testFindShouldBeReturnTheCorrectLastProductAdded() {
        assertFindReturnProduct(6, "test_label_7");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindShouldFailWhenIndexOutBoundWhichNegative() {
        this.instock.find(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindShouldFailWhenIndexOutBoundWhichIndexIsEqualToCount() {
        addProducts();
        this.instock.find(this.instock.getCount());
    }

    @Test
    public void testFindShouldBeReturnCorrectProduct() {
        addProducts();
        this.instock.add(this.product);
        Product foundByLabel = this.instock.findByLabel(this.product.getLabel());
        assertNotNull(foundByLabel);
        assertEquals(this.product.getLabel(), foundByLabel.getLabel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindByLabelShouldThrowIfProductMissing() {
        addProducts();
        instock.findByLabel(product.getLabel());
    }

    @Test
    public void testFindFirstByAlphabeticalOrderShouldReturnEmptyCollectionsOutByRange() {
        addProducts();
        Iterable<Product> iterable = instock.findFirstByAlphabeticalOrder(instock.getCount() + 1);
        assertNotNull(iterable);
        List<Product> products = new ArrayList<>();
        iterable.forEach(products::add);
        assertTrue(products.isEmpty());
    }

    @Test
    public void testFindByAlphabeticalShouldBeReturnCorrectNumberOfProductThatOrderAlphabetical() {
        addProducts();
        int count = instock.getCount() / 2;
        Iterable<Product> iterable = this.instock.findFirstByAlphabeticalOrder(count);
        List<String> actualLabelProduct = new ArrayList<>();
        List<String> expectedLabelProduct = new ArrayList<>();
        assertNotNull(iterable);
        iterable.forEach(p -> {
            actualLabelProduct.add(p.getLabel());
            expectedLabelProduct.add(p.getLabel());
        });
        expectedLabelProduct.sort(Comparator.naturalOrder());
        assertArrayEquals(actualLabelProduct.toArray(), expectedLabelProduct.toArray());
    }

    @Test
    public void testFindAllRangeShouldBeReturnEmptyCollectionIfNotHaveProductsInThisRange() {
        addProducts();
        Iterable<Product> allInRange = instock.findAllInRange(100, 110);
        assertEmptySequence(allInRange);
    }

    @Test
    public void testFindAllRangeShouldReturnCorrectRangeWithOrderedPriceDescending() {
        addProducts();
        double lowRange = 5;
        double highRange = 100.5;
        List<Product> actual = getListFromIterable(instock.findAllInRange(lowRange, highRange));
        List<Product> expected = new ArrayList<>();
        addProductsList(expected);
        expected = expected.stream().filter(p -> p.getPrice() > lowRange && p.getPrice() <= highRange)
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .collect(Collectors.toList());

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i).getPrice(), actual.get(i).getPrice(), 0.00);
        }

    }

    @Test
    public void testFindAllByPriceShouldReturnOnlyEqualsPrice() {
        instock.add(product);
        instock.add(new Product("test_label", product.getPrice(), 1));
        List<Product> actual = getListFromIterable(instock.findAllByPrice(product.getPrice()));
        assertEquals(2, actual.size());
        assertTrue(actual.stream().allMatch(p -> p.getPrice() == product.getPrice()));

    }

    @Test
    public void testFindAllByPriceShouldReturnEmptyCollectionOfNotSuchPrice() {
        addProducts();
        List<Product> actual = getListFromIterable(instock.findAllByPrice(90));
        assertTrue(actual.isEmpty());
    }

    @Test
    public void testFirstMostExpensiveProductShouldBeReturnCorrectNumberOfOrderedByPriceDescending() {
        int expectedCount = 4;
        List<Product> expected = new ArrayList<>();
        addProductsList(expected);
        List<Double> expectedLabels =
                expected.stream()
                        .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                        .limit(expectedCount)
                        .map(Product::getPrice)
                        .collect(Collectors.toList());
        addProducts();
        List<Product> actualProducts = getListFromIterable(instock.findFirstMostExpensiveProducts(expectedCount));
        assertEquals(expectedCount, actualProducts.size());
        for (int i = 0; i < actualProducts.size(); i++) {
            assertEquals(expectedLabels.get(i), actualProducts.get(i).getPrice(), 0);
        }
    }

    @Test
    public void testFindMostExpensiveProductShouldBeReturnEmptyCollectionIfOutOfRange() {
        addProducts();
        Iterable<Product> allInRange = instock.findFirstMostExpensiveProducts(instock.getCount() + 1);
        assertEmptySequence(allInRange);
    }

    @Test
    public void testFindAllByQuantityShouldReturnOnlyEqualsQuantity() {
        addProducts();
        instock.add(product);
        instock.add(new Product("test_label", product.getPrice(), product.getQuantity()));
        List<Product> actual = getListFromIterable(instock.findAllByQuantity(product.getQuantity()));
        assertEquals(2, actual.size());
        assertTrue(actual.stream().allMatch(p -> p.getQuantity() == product.getQuantity()));

    }

    @Test
    public void testFindAllByQuantityShouldReturnEmptyCollectionOfNotSuchQuantity() {
        addProducts();
        List<Product> actual = getListFromIterable(instock.findAllByQuantity(product.getQuantity()));
        assertTrue(actual.isEmpty());
    }

    @Test
    public void testGetIterableShouldReturnOrderOfAddition() {
        List<Product> expected = new ArrayList<>();
        addProductsList(expected);
        addProducts();
        Iterator<Product> iterator = instock.iterator();
        List<Product> actual = new ArrayList<>();
        while (iterator.hasNext()) {
            actual.add(iterator.next());
        }
        System.out.println();
        final int[] count = {0};
        expected.forEach(p -> {
            assertEquals(p.getLabel(), actual.get(count[0]).getLabel());
            assertEquals(p.getPrice(), actual.get(count[0]).getPrice(),0);
            assertEquals(p.getQuantity(), actual.get(count[0]).getQuantity());
            count[0] = count[0] +1;
        });

    }

    private void addProducts() {
        this.instock.add(new Product("test_label_1", 100, 6));
        this.instock.add(new Product("test_label_2", 10, 100));
        this.instock.add(new Product("test_label_3", 60.50, 30));
        this.instock.add(new Product("test_label_4", 80, 23));
        this.instock.add(new Product("test_label_5", 5, 200));
        this.instock.add(new Product("test_label_6", 40.45, 20));
        this.instock.add(new Product("test_label_7", 0.45, 2000));
    }

    private <T> List<T> getListFromIterable(Iterable<T> iterable) {
        assertNotNull(iterable);
        List<T> actualProduct = new ArrayList<>();
        iterable.forEach(actualProduct::add);
        return actualProduct;
    }

    private void assertEmptySequence(Iterable<Product> allInRange) {
        assertNotNull(allInRange);
        List<Double> rangeCollect = new ArrayList<>();
        allInRange.forEach(p -> rangeCollect.add(p.getPrice()));
        assertTrue(rangeCollect.isEmpty());
    }

    private void assertFindReturnProduct(int i, String label) {
        addProducts();
        Product actionProduct = instock.find(i);
        assertNotNull(actionProduct);
        assertEquals(label, actionProduct.getLabel());
    }

    private void addProductsList(List<Product> expected) {
        expected.add(new Product("test_label_1", 100, 6));
        expected.add(new Product("test_label_2", 10, 100));
        expected.add(new Product("test_label_3", 60.50, 30));
        expected.add(new Product("test_label_4", 80, 23));
        expected.add(new Product("test_label_5", 5, 200));
        expected.add(new Product("test_label_6", 40.45, 20));
        expected.add(new Product("test_label_7", 0.45, 2000));

    }
}
