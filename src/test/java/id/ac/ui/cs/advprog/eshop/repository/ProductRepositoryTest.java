package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("abc");
        product.setProductName("produk1");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("abc");
        product1.setProductName("produk1");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("def");
        product2.setProductName("produk2");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1, savedProduct);
        savedProduct = productIterator.next();
        assertEquals(product2, savedProduct);
        assertFalse(productIterator.hasNext());
    }



    @Test
    void testFindProductById() {
        Product product1 = new Product();
        product1.setProductId("abc");
        product1.setProductName("produk1");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product found = productRepository.find("abc");
        Product unfound = productRepository.find("def");
        assertEquals(product1, found);
        assertNull(unfound);
        assertNull(productRepository.find(null));
    }

    @Test
    void testFindNonExistProduct() {
        Product nonExistProduct = new Product();
        nonExistProduct.setProductId("abc");
        nonExistProduct.setProductName("nonexist");
        nonExistProduct.setProductQuantity(100);

        assertNull(productRepository.find(nonExistProduct.getProductId()));
    }

    @Test
    void testEditProduct() {
        Product product1 = new Product();
        product1.setProductId("abc");
        product1.setProductName("produk1");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product1Edited = new Product();
        product1Edited.setProductId("abc");
        product1Edited.setProductName("produk1Edited");
        product1Edited.setProductQuantity(20000);

        productRepository.edit(product1Edited);
        Product expected = productRepository.find(product1Edited.getProductId());

        assertNotNull(expected);
        assertEquals(expected.getProductName(), productRepository.find(expected.getProductId()).getProductName());
        assertEquals(expected.getProductQuantity(), productRepository.find(expected.getProductId()).getProductQuantity());
    }

    @Test
    void testEditNonExistProduct() {
        Product nonExistProduct = new Product();
        nonExistProduct.setProductId("abc");
        nonExistProduct.setProductName("nonexist");
        nonExistProduct.setProductQuantity(100);

        assertNull(productRepository.edit(nonExistProduct));
    }
}
