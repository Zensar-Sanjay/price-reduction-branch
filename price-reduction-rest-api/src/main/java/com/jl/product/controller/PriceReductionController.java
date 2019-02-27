package com.jl.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jl.product.filter.PriceLabelType;
import com.jl.product.filter.ProductSortBy;
import com.jl.product.service.IPriceReductionService;
import com.jl.product.vo.Product;

@RestController
@RequestMapping("/api/vi/")
public class PriceReductionController {
	@Autowired
	IPriceReductionService priceReductionService;

	@GetMapping(value = "/categories/{categoryid}/products/{sortBy}/prices", produces = {MediaType.APPLICATION_JSON_VALUE })	
	public ResponseEntity<List<Product>> getProductsHavePriceReduction(
			@PathVariable(name = "categoryid") int categoryId,
			@RequestParam(value = "labelType", required = false, defaultValue = "SHOW_WAS_NOW") PriceLabelType labelType,
			@PathVariable("sortBy") ProductSortBy sortBy) {

		List<Product> listOfProduct = priceReductionService.getProductsHavePriceReduction(categoryId, labelType,sortBy);
		return null==listOfProduct ? ResponseEntity.noContent().build() : ResponseEntity.ok(listOfProduct);
		
	}
}
