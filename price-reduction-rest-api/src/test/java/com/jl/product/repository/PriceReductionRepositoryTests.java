package com.jl.product.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jl.product.config.PriceReductionRestServiceApp;
import com.jl.product.exception.RestClientCommunicationException;
import com.jl.product.repository.IPriceReductionRepository;
import com.jl.product.vo.RestAPIResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PriceReductionRestServiceApp.class)
public class PriceReductionRepositoryTests {

	@Autowired
	IPriceReductionRepository priceReductionRepository;

	@Test
	public final void testGetProductsByCategoryId() {
		int categoryId = 600001506;
		RestAPIResponse restAPIResponse = priceReductionRepository.getProductsByCategoryId(categoryId);
		assertTrue(!restAPIResponse.getProducts().isEmpty());
		assertNotNull(restAPIResponse.getProducts());
	}

	@Test(expected = RestClientCommunicationException.class)
	public final void testGetProductsByInvalidCategoryId() {
		final int invalidCategoryId = 50001505;
		priceReductionRepository.getProductsByCategoryId(invalidCategoryId);
	}

}
