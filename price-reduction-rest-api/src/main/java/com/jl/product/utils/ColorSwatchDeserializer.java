package com.jl.product.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import com.jl.product.config.CacheConfig;
import com.jl.product.vo.ColorSwatch;

public class ColorSwatchDeserializer extends JsonDeserializer<ColorSwatch> {

	@Override
	public ColorSwatch deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException {
		TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
		ColorSwatch colorSwatch = new ColorSwatch();
		colorSwatch.setColor(((TextNode) treeNode.get("color")).textValue());
		colorSwatch.setSkuId(((TextNode) treeNode.get("skuId")).textValue());
		colorSwatch.setRgbColor(CacheConfig.getHexadecimal(((TextNode) treeNode.get("basicColor")).textValue()));
		return colorSwatch;
	}

}
