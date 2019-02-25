package com.jl.product.exception;

public class RestClientCommunicationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RestClientCommunicationException(String exception) {
		super(exception);
	}

}
