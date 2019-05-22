package com.example.taxes.model

import org.junit.Test

import java.math.BigDecimal

import org.junit.Assert.*

class PurchaseViewModelTest {

    private val purchaseViewModel = PurchaseViewModel()

    @Test
    fun testLocalProductIsTaxedCorrectly() {
        val product = Product("Music CD", ProductType.GENERAL, false, BigDecimal.valueOf(14.99))
        val taxedProduct = purchaseViewModel.createTaxedProduct(product)

        assertNotNull(taxedProduct.taxedPrice)
        assertEquals(BigDecimal.valueOf(16.49), taxedProduct.taxedPrice)
    }

    @Test
    fun testImportProductIsTaxedCorrectly() {
        val product = Product("Imported Box Chocolates", ProductType.FOOD, true, BigDecimal.valueOf(10.00))
        val taxedProduct = purchaseViewModel.createTaxedProduct(product)

        assertNotNull(taxedProduct.taxedPrice)
        assertEquals(BigDecimal.valueOf(15.00).setScale(2), taxedProduct.taxedPrice)
    }

    @Test
    fun testExemptedMedicalProductIsNotTaxed() {
        val product = Product("Cough Mixture", ProductType.MEDICAL, false, BigDecimal.valueOf(1.99))
        val taxedProduct = purchaseViewModel.createTaxedProduct(product)

        assertNull(taxedProduct.taxedPrice)
    }

    @Test
    fun testExemptedFoodProductIsNotTaxed() {
        val product = Product("Hot Dog", ProductType.FOOD, false, BigDecimal.valueOf(1.99))
        val taxedProduct = purchaseViewModel.createTaxedProduct(product)

        assertNull(taxedProduct.taxedPrice)
    }

    @Test
    fun testExemptedBookProductIsNotTaxed() {
        val product = Product("Harry Potter Book", ProductType.BOOK, false, BigDecimal.valueOf(1.99))
        val taxedProduct = purchaseViewModel.createTaxedProduct(product)

        assertNull(taxedProduct.taxedPrice)
    }
}