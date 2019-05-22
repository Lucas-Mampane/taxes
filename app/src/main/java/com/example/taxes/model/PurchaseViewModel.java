package com.example.taxes.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PurchaseViewModel {
    private static final BigDecimal LOCAL_TAX_RATE = BigDecimal.valueOf(0.10);
    private static final BigDecimal IMPORT_TAX_RATE = BigDecimal.valueOf(0.5);

    public Product createTaxedProduct(Product product) {
        String productName = product.getName();
        ProductType productType = product.getProductType();
        BigDecimal originalPrice = product.getOriginalPrice();
        if (localTaxApplies(productType, product.isImport())) {
            return new Product(productName, productType, false, originalPrice, getLocalProductTaxedPrice(originalPrice));
        }

        if (product.isImport()) {
            return new Product(productName, productType, true, originalPrice, getImportedProductTaxedPrice(originalPrice));
        }
        return new Product(productName, productType, false, originalPrice);
    }


    private boolean localTaxApplies(ProductType productType, boolean isImport) {
        return !productType.equals(ProductType.BOOK)
                && !productType.equals(ProductType.FOOD)
                && !productType.equals(ProductType.MEDICAL)
                && !isImport;
    }


    private BigDecimal getLocalProductTaxedPrice(BigDecimal price) {
        return price.add(price.multiply(LOCAL_TAX_RATE)).setScale(2 , RoundingMode.HALF_UP);
    }

    private BigDecimal getImportedProductTaxedPrice(BigDecimal price) {
        return price.add(price.multiply(IMPORT_TAX_RATE)).setScale(2 , RoundingMode.HALF_UP);
    }
}
