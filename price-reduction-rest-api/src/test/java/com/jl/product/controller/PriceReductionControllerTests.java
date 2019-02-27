package com.jl.product.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class PriceReductionControllerTests extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void testGetPriceReductionProducts() throws Exception {
		String restUrl = "/api/vi/categories/600001506/products/PRODUCT_PRICE_REDUCTION_ASC/prices?labelType=SHOW_WAS_NOW";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(restUrl).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertTrue(content.length() > 0);
	}

}
