package com.jl.product.service;

import static com.jl.product.filter.PriceLabelType.SHOW_PERC_DISCOUNT;
import static com.jl.product.filter.PriceLabelType.SHOW_WAS_NOW;
import static com.jl.product.filter.PriceLabelType.SHOW_WAS_THEN_NOW;
import static com.jl.product.filter.ProductSortBy.PRODUCT_PRICE_REDUCTION_ASC;
import static com.jl.product.filter.ProductSortBy.PRODUCT_PRICE_REDUCTION_DESC;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jl.product.config.PriceReductionRestServiceApp;
import com.jl.product.vo.Product;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PriceReductionRestServiceApp.class)
public class PriceReductionServiceTests {

	@Autowired
	IPriceReductionService priceReductionService;

	@Test
	public void testGetPriceReductionForProductWtihFilterShowWasNow() {
		int categoryId = 600001506;

		List<Product> products = priceReductionService.getProductsHavePriceReduction(categoryId, SHOW_WAS_NOW,
				PRODUCT_PRICE_REDUCTION_ASC);
		assertNotNull(products);
		assertTrue(!products.isEmpty());
	}

	@Test
	public void testGetPriceReductionProductsWithFilterShowWasThenNow() {
		int categoryId = 600001506;
		List<Product> products = priceReductionService.getProductsHavePriceReduction(categoryId, SHOW_WAS_THEN_NOW,
				PRODUCT_PRICE_REDUCTION_DESC);
		assertNotNull(products);
		assertTrue(!products.isEmpty());
	}

	@Test
	public void testGetPriceReductionProductsWithFilterShowPerDsct() {
		int categoryId = 600001506;
		List<Product> products = priceReductionService.getProductsHavePriceReduction(categoryId, SHOW_PERC_DISCOUNT,
				PRODUCT_PRICE_REDUCTION_DESC);
		assertNotNull(products);
		assertTrue(!products.isEmpty());
	}

}
