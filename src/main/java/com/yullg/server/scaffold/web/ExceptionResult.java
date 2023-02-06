package com.yullg.server.scaffold.web;

public class ExceptionResult<T> extends RestResult<T> {

	public ExceptionResult(int code, String message, T data) {
		super(code, message, data);
		if (RestResult.CODE_OK == code) {
			throw new IllegalArgumentException("The code must not be " + RestResult.CODE_OK);
		}
	}

	public ExceptionResult(int code, String message) {
		this(code, message, null);
	}

	public ExceptionResult() {
		this(RestResult.CODE_NO, RestResult.MESSAGE_NO);
	}

}