package com.example.demo.model;

import java.math.BigDecimal;

public class Discount {

    private BigDecimal discountPercentage;

    public Discount() {}

    public Discount(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public BigDecimal applyDiscount(BigDecimal totalAmount) {
        return totalAmount.subtract(
                totalAmount.multiply(discountPercentage).divide(new BigDecimal(100))
        );
    }

    // Getters y Setters
    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
