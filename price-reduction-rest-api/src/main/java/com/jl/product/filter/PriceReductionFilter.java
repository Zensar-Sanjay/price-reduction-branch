package com.jl.product.filter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.jl.product.vo.Product;

public class PriceReductionFilter implements IProductFilter {

	@Override
	public List<Product> meetCriteria(List<Product> products, PriceLabelType labelType, ProductSortBy sortBy) {

		if (ProductSortBy.valueOf("PRODUCT_PRICE_REDUCTION_ASC").equals(sortBy)) {
			Comparator<Product> productPriceComparatorASC = (Product p1, Product p2) -> p1.getReducedPrice()
					.compareTo(p2.getReducedPrice());
			return products.stream().sorted(productPriceComparatorASC).filter(p -> p.getReducedPrice() > 0.0)
					.collect(Collectors.toList());
		} else {
			Comparator<Product> productPriceComparatorDESC = (Product p1, Product p2) -> p2.getReducedPrice()
					.compareTo(p1.getReducedPrice());
			return products.stream().sorted(productPriceComparatorDESC).filter(p -> p.getReducedPrice() > 0.0)
					.collect(Collectors.toList());
		}

	}

}
