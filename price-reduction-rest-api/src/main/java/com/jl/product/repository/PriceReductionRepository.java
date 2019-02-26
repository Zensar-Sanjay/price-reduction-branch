package com.jl.product.repository;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.jl.product.exception.RestClientCommunicationException;
import com.jl.product.vo.RestAPIResponse;

@Repository
public class PriceReductionRepository implements IPriceReductionRepository {

	private static Logger logger = LoggerFactory.getLogger(PriceReductionRepository.class);

	@Autowired
	RestTemplate restTemplate;

	@Value("${rest.url.prefix}")
	private String restUrlPrefix;

	@Value("${rest.url.value}")
	private String restUrlValue;

	@Value("${rest.url.key}")
	private String restUrlkey;

	@Override
	public RestAPIResponse getProductsByCategoryId(int categoryId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<RestAPIResponse> entity = new HttpEntity<RestAPIResponse>(headers);
		try {
			return restTemplate.exchange(buildRestURL(categoryId), HttpMethod.GET, entity, RestAPIResponse.class)
					.getBody();
		} catch (HttpClientErrorException | HttpServerErrorException errorException) {
			logger.error(String.format("%s %s",
					"Provided product API URL is down or temporary unavaliable or Syntax error with product API URL",
					errorException.getStackTrace()));
			throw new RestClientCommunicationException(
					"Provided product API URL is down or temporary unavaliable or Syntax error with product API URL");
		}
	}

	private String buildRestURL(int categoryId) {
		return String.format("%s%s%s%s", restUrlPrefix, categoryId, restUrlValue, restUrlkey);
	}
}
