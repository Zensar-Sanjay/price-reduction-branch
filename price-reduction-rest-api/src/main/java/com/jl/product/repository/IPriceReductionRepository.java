package com.jl.product.repository;

import com.jl.product.vo.RestAPIResponse;

public interface IPriceReductionRepository {

	public RestAPIResponse getProductsByCategoryId(int categoryId);

}