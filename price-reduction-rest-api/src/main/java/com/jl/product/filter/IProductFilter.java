package com.jl.product.filter;

import java.util.List;

import com.jl.product.vo.Product;

public interface IProductFilter {
	public List<Product> meetCriteria(List<Product> products, PriceLabelType labelType, ProductSortBy sortBy);
}
