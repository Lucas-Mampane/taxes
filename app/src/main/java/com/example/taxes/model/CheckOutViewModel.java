package com.example.taxes.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CheckOutViewModel {

    private static final BigDecimal TAX_ROUNDING = BigDecimal.valueOf(0.05);
    private List<Product> products;

    public CheckOutViewModel(List<Product> products) {
        this.products = products;
    }

    public BigDecimal getTotalPurchaseValue() {
        BigDecimal total = BigDecimal.ZERO;
        for (Product product: products) {
                total = total.add(product.getOriginalPrice());
        }
        total = total.add(getTotalTaxValue());
        return total;
    }

    public BigDecimal getTotalTaxValue() {
        BigDecimal total = BigDecimal.ZERO;
        for (Product product: products) {
            if (hasTax(product)) {
                total = total.add((product.getTaxedPrice().subtract(product.getOriginalPrice())));
            }
        }
        return getRoundedTaxTotal(total);
    }

    private boolean hasTax(Product product) {
        return product.getTaxedPrice() != null;
    }

    private BigDecimal getRoundedTaxTotal(BigDecimal bigDecimal) {
        BigDecimal rounded = bigDecimal.divide(TAX_ROUNDING, 0, RoundingMode.UP);
        return rounded.multiply(TAX_ROUNDING);
    }
}
