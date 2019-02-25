package com.jl.product.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jl.product.utils.ColorSwatchDeserializer;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = ColorSwatchDeserializer.class)
@Getter
@Setter
public class ColorSwatch {

	private String color;
	private String rgbColor;
	private String skuId;

	@JsonIgnore
	private String basicColor;

}
