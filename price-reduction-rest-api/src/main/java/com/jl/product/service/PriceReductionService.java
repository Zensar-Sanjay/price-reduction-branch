package com.jl.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jl.product.filter.FilterExecutor;
import com.jl.product.filter.IProductFilter;
import com.jl.product.filter.PriceLabelFilter;
import com.jl.product.filter.PriceLabelType;
import com.jl.product.filter.PriceReductionFilter;
import com.jl.product.filter.ProductSortBy;
import com.jl.product.repository.PriceReductionRepository;
import com.jl.product.vo.Product;

@Service
public class PriceReductionService implements IPriceReductionService {

	@Autowired
	PriceReductionRepository priceReductionRepository;

	@Override
	public List<Product> getProductsHavePriceReduction(int categoryId, PriceLabelType labelType, ProductSortBy sortBy) {

		List<Product> products = priceReductionRepository.getProductsByCategoryId(categoryId).getProducts();

		List<IProductFilter> filters = createFilters();

		return new FilterExecutor(filters).executeFilters(products, labelType, sortBy);

	}

	private List<IProductFilter> createFilters() {
		IProductFilter priceLableFilter = new PriceLabelFilter();
		IProductFilter priceReductionFilter = new PriceReductionFilter();

		List<IProductFilter> filters = new ArrayList<IProductFilter>();

		filters.add(priceReductionFilter);
		filters.add(priceLableFilter);
		return filters;
	}
}