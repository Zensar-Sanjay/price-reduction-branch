package com.jl.product.utils;

import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import com.jl.product.config.CacheConfig;
import com.jl.product.vo.ColorSwatch;
import com.jl.product.vo.Price;
import com.jl.product.vo.Product;

public class ProductDeserializer extends JsonDeserializer<Product> {

	private static final String PRICE = "price";
	private static final String CURRENCY = "currency";
	private static final int NOWPRICE_RULE_VALUE = 10;

	@Override
	public Product deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
		TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
		Product product = new Product();

		// set id and title into product
		product.setProductId(((TextNode) treeNode.get("productId")).textValue());
		product.setTitle(((TextNode) treeNode.get("title")).textValue());

		ObjectMapper mapper = new ObjectMapper();
		// set colorSwatches into product
		String colorSwatches = treeNode.get("colorSwatches").toString();
		ColorSwatch[] colorSwatchesArray = mapper.readValue(colorSwatches, ColorSwatch[].class);
		product.setColorSwatches(Arrays.asList(colorSwatchesArray));

		// set nowPrice into product
		String currency = "";
		if (null != treeNode.get(PRICE).get(CURRENCY)) {
			currency = ((TextNode) treeNode.get(PRICE).get(CURRENCY)).textValue();
			product.setCurrency(currency);
		}

		String priceString = treeNode.get(PRICE).toString();
		String wasString = treeNode.get(PRICE).get("was").toString();
		Price price = mapper.readValue(priceString, Price.class);
		price.setWas(Double.parseDouble(wasString.substring(1, wasString.length() - 1).isEmpty() ? "0.0"
				: wasString.substring(1, wasString.length() - 1)));

		if (null == treeNode.get(PRICE).get("now").get("to")) {
			String nowString = treeNode.get(PRICE).get("now").toString();
			computeNowPrice(product, currency, price, nowString);
		} else {
			String toPriceString = treeNode.get(PRICE).get("now").get("to").toString();
			computeNowPrice(product, currency, price, toPriceString);
		}
		return product;
	}

	private void computeNowPrice(Product product, String currency, Price price, String nowString) {
		double now = Double.parseDouble(nowString.substring(1, nowString.length() - 1));

		price.setNow(now);
		product.setPrice(price);
		product.setNowPrice(CacheConfig.getCurrencySymbol(currency) + Math.round(now));
		if ((now == Math.floor(now)) && !Double.isInfinite(now)) {
			if (now > NOWPRICE_RULE_VALUE) {
				product.setNowPrice(CacheConfig.getCurrencySymbol(currency) + now);
			} else {
				product.setNowPrice(CacheConfig.getCurrencySymbol(currency) + Math.round(now));
			}
		}

		if (price.getWas() > 0.0 && price.getWas() > now) {
			product.setReducedPrice((Math.round(price.getWas()) - now));
		} else {
			product.setReducedPrice(0.0);
		}

	}

}
