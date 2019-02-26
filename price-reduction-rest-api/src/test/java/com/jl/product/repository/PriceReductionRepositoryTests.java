package com.jl.product.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jl.product.config.PriceReductionRestServiceApp;
import com.jl.product.exception.RestClientCommunicationException;
import com.jl.product.vo.Product;
import com.jl.product.vo.RestAPIResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PriceReductionRestServiceApp.class)
public class PriceReductionRepositoryTests {

	@Autowired
	IPriceReductionRepository priceReductionRepository;

	@Test
	public void testGetProductsByCategoryId() {
		int categoryId = 600001506;
		RestAPIResponse restAPIResponse = priceReductionRepository.getProductsByCategoryId(categoryId);
		assertTrue(!restAPIResponse.getProducts().isEmpty());
		assertNotNull(restAPIResponse.getProducts());
	}

	@Test(expected = RestClientCommunicationException.class)
	public void testGetProductsByInvalidCategoryId() {
		int invalidCategoryId = 50001505;
		priceReductionRepository.getProductsByCategoryId(invalidCategoryId);
	}

	@Test
	public void testWasPriceGreaterThanZero() {
		int categoryId = 600001506;
		RestAPIResponse restAPIResponse = priceReductionRepository.getProductsByCategoryId(categoryId);
		List<Product> products = restAPIResponse.getProducts();
		assertTrue(!restAPIResponse.getProducts().isEmpty());
		assertNotNull(restAPIResponse.getProducts());
		products.stream().forEach(p -> {
			assert (p.getPrice().getWas() >= 0.0);
		});
	}

}
