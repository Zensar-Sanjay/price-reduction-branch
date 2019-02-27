package com.jl.product.service;

import java.util.List;

import com.jl.product.filter.PriceLabelType;
import com.jl.product.filter.ProductSortBy;
import com.jl.product.vo.Product;

public interface IPriceReductionService {

	public List<Product> getProductsHavePriceReduction(int categoryId, PriceLabelType labelType, ProductSortBy sortBy);
}