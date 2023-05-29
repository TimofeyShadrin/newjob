package ru.tshadrin.newjob.nonton.service.impl;

import junit.framework.Assert;
import junit.framework.TestCase;
import ru.tshadrin.newjob.nonton.domain.Product;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProductListingTest extends TestCase {
    private ProductListing productListing;

    public void setUp() throws Exception {
        super.setUp();
        productListing = new ProductListing();
    }


    public void testAddProductSuccessfully() {
        boolean expected = true;
        boolean actual = productListing.addProduct(new Product("DVN", "Sofa"));
        Assert.assertEquals(expected, actual);
    }

    public void testAddProductExists(){
        productListing.addProduct(new Product("DVN", "Sofa"));
        boolean expected = false;
        boolean actual = productListing.addProduct(new Product("DVN", "Sofa"));
        Assert.assertEquals(expected, actual);
    }

    public void testAddProductNull() {
        boolean expected = false;
        boolean actual = productListing.addProduct(null);
        Assert.assertEquals(expected, actual);
    }

    public void testAddProductNotBlank() {
        boolean expected = false;
        List<Boolean> actual = Arrays.asList(
                productListing.addProduct(new Product("", "Sofa")),
                productListing.addProduct(new Product("DVN", "")),
                productListing.addProduct(new Product(null, "Sofa")),
                productListing.addProduct(new Product("DVN", null)),
                productListing.addProduct(new Product(null, null)),
                productListing.addProduct(new Product("", ""))
        );
        for (Boolean result : actual) {
            Assert.assertEquals(expected, result.booleanValue());
        }
    }

    public void testDeleteProductSuccessfully() {
        Product product = new Product("DVN", "Sofa");
        productListing.addProduct(product);
        boolean expected = true;
        boolean actual = productListing.deleteProduct(product);
        Assert.assertEquals(expected, actual);
    }

    public void testDeleteProductIfNotExists() {
        Product product = new Product("DVN", "Sofa");
        boolean expected = false;
        boolean actual = productListing.deleteProduct(product);
        Assert.assertEquals(expected, actual);
    }

    public void testDeleteProductNotBlank() {
        boolean expected = false;
        List<Boolean> actual = Arrays.asList(
                productListing.addProduct(new Product("", "Sofa")),
                productListing.addProduct(new Product("DVN", "")),
                productListing.addProduct(new Product(null, "Sofa")),
                productListing.addProduct(new Product("DVN", null)),
                productListing.addProduct(new Product(null, null)),
                productListing.addProduct(new Product("", ""))
        );
        for (Boolean result : actual) {
            Assert.assertEquals(expected, result.booleanValue());
        }
    }

    public void testTestGetNameSuccessfully() {
        Product product = new Product("DVN", "Sofa");
        productListing.addProduct(product);
        String expected = "Sofa";
        String actual = productListing.getName("DVN");
        Assert.assertEquals(expected, actual);
    }

    public void testTestGetNameIfNotExists() {
        Product product = new Product("DVN", "Sofa");
        productListing.addProduct(product);
        String expected = "";
        String actual = productListing.getName("TBL");
        Assert.assertEquals(expected, actual);
    }

    public void testTestGetNameIfNotBlank() {
        String expected = "";
        List<String> actual = Arrays.asList(
                productListing.getName(null),
                productListing.getName("")
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
            productListing.addProduct(product);
        }
        List<String> expected = Arrays.asList("DSK", "TBL");
        List<String> actual = productListing.findByName("Table");
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
            productListing.addProduct(product);
        }
        // Делаем сортировку вручную, так как словарь делает это по умолчанию с ключами
        List<String> expected = Collections.EMPTY_LIST;
        List<String> actual = productListing.findByName("Chair");
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
            productListing.addProduct(product);
        }
        // Делаем сортировку вручную, так как словарь делает это по умолчанию с ключами
        List<String> expected = Collections.EMPTY_LIST;
        List<String> actual = productListing.findByName("");
        Assert.assertEquals(expected, actual);

        actual = productListing.findByName(null);
        Assert.assertEquals(expected, actual);
    }
}