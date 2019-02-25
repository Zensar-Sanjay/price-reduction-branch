package com.jl.product.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.util.StringUtils;

@Configuration
public class CacheConfig {

	private static final Map<String, String> colorMap = new HashMap<String, String>();
	private static final Map<String, String> currencyMap = new HashMap<String, String>();
	private static final String UNDEFINED = "Undefined";

	@Bean
	public Map<String, String> colorCacheBean() {
		colorMap.put("Pink", "#ffc0cb");
		colorMap.put("Orange", "#ffa500");
		colorMap.put("Fuchsia", "#FF00FF");
		colorMap.put("Red", "#FF0000");
		colorMap.put("Blue", "#0000FF");
		colorMap.put("Green", "#008000");
		colorMap.put("Black", "#000000");
		colorMap.put("Gray", "#808080");
		colorMap.put("Purple", "#800080");
		colorMap.put("White", "#FFFFFF");
		colorMap.put("Lime", "#00FF00");
		colorMap.put("Yellow", "#FFFF00");
		colorMap.put("Cyan", "#00FFFF");
		colorMap.put("Silver", "#C0C0C0");
		colorMap.put("Magenta", "#FF00FF");
		colorMap.put("Grey", "#808080");
		colorMap.put("Maroon", "#800000");
		colorMap.put("Olive", "#808000");
		colorMap.put("Teal", "#008080");
		colorMap.put("Navy", "#000080");
		return colorMap;
	}

	@Bean
	public Map<String, String> currencyCacheBean() {
		currencyMap.put("GBP", "£");
		currencyMap.put("USD", "$");
		return currencyMap;
	}

	public static String getHexadecimal(String colorName) {
		return StringUtils.isNotBlank(colorMap.get(colorName)) ? colorMap.get(colorName) : UNDEFINED;
	}

	public static String getCurrencySymbol(String currency) {
		return StringUtils.isNotBlank(currencyMap.get(currency)) ? currencyMap.get(currency) : "£";
	}

}
