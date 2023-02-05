package com.yullg.server.scaffold.web;

public class ExceptionResult<T> extends Result<T> {

	public ExceptionResult(int code, String message, T data) {
		super(code, message, data);
		if (Result.CODE_OK == code) {
			throw new IllegalArgumentException("The code must not be " + Result.CODE_OK);
		}
	}

	public ExceptionResult(int code, String message) {
		this(code, message, null);
	}

	public ExceptionResult() {
		this(Result.CODE_NO, Result.MESSAGE_NO);
	}

}