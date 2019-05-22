package com.example.taxes.model

import org.junit.Test

import java.math.BigDecimal
import java.util.ArrayList

import org.junit.Assert.*

class CheckOutViewModelTest {

    private lateinit var products: MutableList<Product>
    private lateinit var viewModel: CheckOutViewModel


    @Test
    fun testOutputOne() {
        products = ArrayList()
        products.add(Product("Book", ProductType.BOOK, false, BigDecimal.valueOf(12.49)))
        products.add(Product("Music CD", ProductType.GENERAL, false, BigDecimal.valueOf(14.99), BigDecimal.valueOf(16.49)))
        products.add(Product("Chocolate Bar", ProductType.FOOD, false, BigDecimal.valueOf(0.85)))

        viewModel = CheckOutViewModel(products)

        val taxValue = viewModel.totalTaxValue
        val total = viewModel.totalPurchaseValue

        assertEquals(BigDecimal.valueOf(1.50).setScale(2), taxValue)
        assertEquals(BigDecimal.valueOf(29.83), total)
    }

    @Test
    fun testOutputTwo() {
        products = ArrayList()
        products.add(Product("Imported box of chocolates", ProductType.FOOD, true, BigDecimal.valueOf(10.00), BigDecimal.valueOf(10.50)))
        products.add(Product("Imported bottle of perfume", ProductType.GENERAL, true, BigDecimal.valueOf(47.50), BigDecimal.valueOf(54.65)))

        viewModel = CheckOutViewModel(products)

        val taxValue = viewModel.totalTaxValue
        val total = viewModel.totalPurchaseValue

        assertEquals(BigDecimal.valueOf(7.65), taxValue)
        assertEquals(BigDecimal.valueOf(65.15), total)
    }

    @Test
    fun testOutputThree() {
        products = ArrayList()
        products.add(Product("Imported box of chocolates", ProductType.GENERAL, true, BigDecimal.valueOf(27.99), BigDecimal.valueOf(32.19)))
        products.add(Product("Bottle of perfume", ProductType.GENERAL, false, BigDecimal.valueOf(18.99), BigDecimal.valueOf(20.89)))
        products.add(Product("Packet of headache pills", ProductType.MEDICAL, false, BigDecimal.valueOf(9.75)))
        products.add(Product("Box of imported chocolates", ProductType.GENERAL, false, BigDecimal.valueOf(11.25), BigDecimal.valueOf(11.85)))

        viewModel = CheckOutViewModel(products)

        val taxValue = viewModel.totalTaxValue
        val total = viewModel.totalPurchaseValue

        assertEquals(BigDecimal.valueOf(6.70).setScale(2), taxValue)
        assertEquals(BigDecimal.valueOf(74.68), total)
    }

}