package com.jl.product.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jl.product.utils.ProductDeserializer;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = ProductDeserializer.class)
@JsonPropertyOrder({ "productId", "title", "reducedPrice", "nowPrice", "priceLabel", "colorSwatches" })
@Getter
@Setter
public class Product {

	private String productId;
	private String title;
	private String nowPrice;
	private List<ColorSwatch> colorSwatches;
	private String priceLabel;
	private Double reducedPrice;

	@JsonIgnore
	private Price price;
	@JsonIgnore
	private String currency;
}
