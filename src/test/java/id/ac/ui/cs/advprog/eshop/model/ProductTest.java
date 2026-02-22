package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    Product product;
    @BeforeEach
    void setUp() {
        this.product = new Product();
        this.product.setProductId("abc");
        this.product.setProductName("produk1");
        this.product.setProductQuantity(100);
    }

    @Test
    void testGetProductId() {
        assertEquals("abc", this.product.getProductId());
    }

    @Test
    void testGetProductName() {
        assertEquals("produk1", this.product.getProductName());
    }

    @Test
    void testGetProductQuantity() {
        assertEquals(100, this.product.getProductQuantity());
    }
}
