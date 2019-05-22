package com.example.taxes.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Objects;

public class Product implements Parcelable {
    private String name;
    private ProductType productType;
    private boolean isImport;
    private BigDecimal originalPrice;
    private BigDecimal taxedPrice;


    public Product(String name, ProductType productType, boolean isImport, BigDecimal originalPrice) {
        this.name = name;
        this.productType = productType;
        this.isImport = isImport;
        this.originalPrice = originalPrice;
    }

    public Product(String name, ProductType productType, boolean isImport, BigDecimal originalPrice, BigDecimal taxedPrice) {
        this.name = name;
        this.productType = productType;
        this.isImport = isImport;
        this.originalPrice = originalPrice;
        this.taxedPrice = taxedPrice;
    }

    protected Product(Parcel in) {
        name = in.readString();
        productType = ProductType.valueOf(in.readString());
        isImport = in.readByte() != 0;
        originalPrice = (BigDecimal) in.readSerializable();
        taxedPrice = (BigDecimal) in.readSerializable();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(productType.name());
        dest.writeByte((byte) (isImport ? 1 : 0));
        dest.writeSerializable(originalPrice);
        dest.writeSerializable(taxedPrice);
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public boolean isImport() {
        return isImport;
    }

    public void setImport(boolean anImport) {
        isImport = anImport;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getTaxedPrice() {
        return taxedPrice;
    }

    public void setTaxedPrice(BigDecimal taxedPrice) {
        this.taxedPrice = taxedPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return isImport == product.isImport &&
                Objects.equals(name, product.name) &&
                productType == product.productType &&
                Objects.equals(originalPrice, product.originalPrice) &&
                Objects.equals(taxedPrice, product.taxedPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, productType, isImport, originalPrice, taxedPrice);
    }
}
