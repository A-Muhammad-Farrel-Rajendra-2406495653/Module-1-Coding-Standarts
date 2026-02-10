package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product {
    private static Integer classId = 0;
    private String productId;
    private String productName;
    private int productQuantity;

    public Product() {
        classId++;
        this.productId = classId.toString();
        System.out.println("spawn constructor, sekarang id nya: " + classId);
    }
}
