package com.jl.product.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jl.product.config.PriceReductionRestServiceApp;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PriceReductionRestServiceApp.class)
public class PriceReductionServiceTests {

	@Test
	public void testGetPriceReductionForProductWtihFilterShowWasNow() {
		assertTrue(Boolean.FALSE);
	}

	@Test
	public void testGetPriceReductionProductsWithFilterShowWasThenNow() {
		assertTrue(Boolean.FALSE);
	}

	@Test
	public void testGetPriceReductionProductsWithFilterShowPerDsct() {
		assertTrue(Boolean.FALSE);
	}

}
