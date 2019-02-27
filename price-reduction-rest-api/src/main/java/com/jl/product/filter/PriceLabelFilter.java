package com.jl.product.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.jl.product.config.CacheConfig;
import com.jl.product.vo.Product;

public class PriceLabelFilter implements IProductFilter {

	private static final String SHOW_WAS_NOW_TEMPLATE = "Was %s%.2f,  now %s%s";
	private static final String SHOW_WAS_THEN_NOW_TEMPLATE = "Was %s%.2f, then1 %s%.2f, then2 %s%.2f, now %s%s";
	private static final String SHOW_PERC_DISCOUNT_TEMPLATE = "%.2f%% off -  now %s%s";

	@Override
	public List<Product> meetCriteria(List<Product> products, PriceLabelType labelType, ProductSortBy sortBy) {

		List<Product> listOfProducts = new ArrayList<>();

		switch (labelType) {
		case SHOW_WAS_NOW:
			listOfProducts = products.stream().map(this::decoratePriceLabelWithShowWasNow).collect(Collectors.toList());
			break;

		case SHOW_WAS_THEN_NOW:
			listOfProducts = products.stream().map(this::decoratePriceLabelWithShowWasThenNow)
					.collect(Collectors.toList());
			break;

		case SHOW_PERC_DISCOUNT:
			listOfProducts = products.stream().map(this::decorateProductForPriceDiscount).collect(Collectors.toList());
			break;
		}

		return listOfProducts;
	}

	private Product decoratePriceLabelWithShowWasNow(Product product) {

		String nowPriceString = product.getPrice().getNow().toString();

		double wasPriceString = Math.round(product.getPrice().getWas());
		String currencySymbol = CacheConfig.getCurrencySymbol((product.getPrice().getCurrency()));
		product.setPriceLabel(
				String.format(SHOW_WAS_NOW_TEMPLATE, currencySymbol, wasPriceString, currencySymbol, nowPriceString));

		return product;
	}

	private Product decoratePriceLabelWithShowWasThenNow(Product product) {
		Double wasPrice = product.getPrice().getWas();
		Double then1Price = product.getPrice().getThen1();
		Double then2Price = product.getPrice().getThen2();
		String nowPrice = product.getPrice().getNow().toString();
		String currencySymbol = CacheConfig.getCurrencySymbol(product.getPrice().getCurrency());

		product.setPriceLabel(String.format(SHOW_WAS_THEN_NOW_TEMPLATE, currencySymbol, wasPrice, currencySymbol,
				then1Price, currencySymbol, then2Price, currencySymbol, nowPrice));
		return product;
	}

	private Product decorateProductForPriceDiscount(Product product) {
		String currencySymbol = CacheConfig.getCurrencySymbol(product.getPrice().getCurrency());
		String nowPrice = product.getNowPrice();

		if (product.getPrice() != null) {
			double discount = calculateDiscount(product);
			product.setPriceLabel(String.format(SHOW_PERC_DISCOUNT_TEMPLATE, discount, currencySymbol, nowPrice));
		}
		return product;
	}

	private double calculateDiscount(Product product) {
		return Math.round((product.getReducedPrice() / product.getPrice().getWas()) * 100);

	}

}
