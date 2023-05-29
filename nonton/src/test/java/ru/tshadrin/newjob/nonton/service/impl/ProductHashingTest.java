package ru.tshadrin.newjob.nonton.service.impl;

import junit.framework.Assert;
import junit.framework.TestCase;
import ru.tshadrin.newjob.nonton.domain.Product;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProductHashingTest extends TestCase {
    private ProductHashing productHashing;

    public void setUp() throws Exception {
        super.setUp();
        productHashing = new ProductHashing();
    }

    public void testAddProductSuccessfully() {
        boolean expected = true;
        boolean actual = productHashing.addProduct(new Product("DVN", "Sofa"));
        Assert.assertEquals(expected, actual);
    }

    public void testAddProductExists(){
        productHashing.addProduct(new Product("DVN", "Sofa"));
        boolean expected = false;
        boolean actual = productHashing.addProduct(new Product("DVN", "Sofa"));
        Assert.assertEquals(expected, actual);
    }

    public void testAddProductNull() {
        boolean expected = false;
        boolean actual = productHashing.addProduct(null);
        Assert.assertEquals(expected, actual);
    }

    public void testAddProductNotBlank() {
        boolean expected = false;
        List<Boolean> actual = Arrays.asList(
                productHashing.addProduct(new Product("", "Sofa")),
                productHashing.addProduct(new Product("DVN", "")),
                productHashing.addProduct(new Product(null, "Sofa")),
                productHashing.addProduct(new Product("DVN", null)),
                productHashing.addProduct(new Product(null, null)),
                productHashing.addProduct(new Product("", ""))
        );
        for (Boolean result : actual) {
            Assert.assertEquals(expected, result.booleanValue());
        }
    }

    public void testDeleteProductSuccessfully() {
        Product product = new Product("DVN", "Sofa");
        productHashing.addProduct(product);
        boolean expected = true;
        boolean actual = productHashing.deleteProduct(product);
        Assert.assertEquals(expected, actual);
    }

    public void testDeleteProductIfNotExists() {
        Product product = new Product("DVN", "Sofa");
        boolean expected = false;
        boolean actual = productHashing.deleteProduct(product);
        Assert.assertEquals(expected, actual);
    }

    public void testDeleteProductNotBlank() {
        boolean expected = false;
        List<Boolean> actual = Arrays.asList(
                productHashing.addProduct(new Product("", "Sofa")),
                productHashing.addProduct(new Product("DVN", "")),
                productHashing.addProduct(new Product(null, "Sofa")),
                productHashing.addProduct(new Product("DVN", null)),
                productHashing.addProduct(new Product(null, null)),
                productHashing.addProduct(new Product("", ""))
        );
        for (Boolean result : actual) {
            Assert.assertEquals(expected, result.booleanValue());
        }
    }

    public void testTestGetNameSuccessfully() {
        Product product = new Product("DVN", "Sofa");
        productHashing.addProduct(product);
        String expected = "Sofa";
        String actual = productHashing.getName("DVN");
        Assert.assertEquals(expected, actual);
    }

    public void testTestGetNameIfNotExists() {
        Product product = new Product("DVN", "Sofa");
        productHashing.addProduct(product);
        String expected = "";
        String actual = productHashing.getName("TBL");
        Assert.assertEquals(expected, actual);
    }

    public void testTestGetNameIfNotBlank() {
        String expected = "";
        List<String> actual = Arrays.asList(
                productHashing.getName(null),
                productHashing.getName("")
        );
        for (String result : actual) {
            Assert.assertEquals(expected, result);
        }
    }

    public void testFindByNameSuccessfully() {
        List<Product> products = Arrays.asList(
                new Product("DVN", "Sofa"),
                new Product("TBL", "Table"),
                new Product("DSK", "Table"),
                new Product("LNG", "Sofa")
        );
        for (Product product : products) {
            productHashing.addProduct(product);
        }
        // Делаем сортировку вручную, так как словарь делает это по умолчанию с ключами
        List<String> expected = Arrays.asList("DSK", "TBL");
        List<String> actual = productHashing.findByName("Table");
        for (int i = 0; i < expected.size(); i++) {
            Assert.assertEquals(expected.get(i), actual.get(i));
        }
    }

    public void testFindByNameIfNotExists() {
        List<Product> products = Arrays.asList(
                new Product("DVN", "Sofa"),
                new Product("TBL", "Table"),
                new Product("DSK", "Table"),
                new Product("LNG", "Sofa")
        );
        for (Product product : products) {
            productHashing.addProduct(product);
        }
        // Делаем сортировку вручную, так как словарь делает это по умолчанию с ключами
        List<String> expected = Collections.EMPTY_LIST;
        List<String> actual = productHashing.findByName("Chair");
        Assert.assertEquals(expected, actual);
    }

    public void testFindByNameNotBlank() {
        List<Product> products = Arrays.asList(
                new Product("DVN", "Sofa"),
                new Product("TBL", "Table"),
                new Product("DSK", "Table"),
                new Product("LNG", "Sofa")
        );
        for (Product product : products) {
            productHashing.addProduct(product);
        }
        // Делаем сортировку вручную, так как словарь делает это по умолчанию с ключами
        List<String> expected = Collections.EMPTY_LIST;
        List<String> actual = productHashing.findByName("");
        Assert.assertEquals(expected, actual);

        actual = productHashing.findByName(null);
        Assert.assertEquals(expected, actual);
    }
}