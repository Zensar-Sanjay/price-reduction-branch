package com.jl.product.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Price {

	private double was;
	private double then1;
	private double then2;
	private Object now;
	private String currency;

}
