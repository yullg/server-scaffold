package com.yullg.server.scaffold.web;

public class ResultException extends RuntimeException {
	private static final long serialVersionUID = -8242218212510140190L;

	public final int code;

	public ResultException() {
		this(RestResult.CODE_NO, RestResult.MESSAGE_NO);
	}

	public ResultException(String message) {
		this(RestResult.CODE_NO, message);
	}

	public ResultException(int code, String message) {
		super(message);
		if (RestResult.CODE_OK == code) {
			throw new IllegalArgumentException("The code must not be " + RestResult.CODE_OK);
		}
		this.code = code;
	}

	public ResultException(Throwable cause) {
		this(RestResult.CODE_NO, RestResult.MESSAGE_NO, cause);
	}

	public ResultException(String message, Throwable cause) {
		this(RestResult.CODE_NO, message, cause);
	}

	public ResultException(int code, String message, Throwable cause) {
		super(message, cause);
		if (RestResult.CODE_OK == code) {
			throw new IllegalArgumentException("The code must not be " + RestResult.CODE_OK);
		}
		this.code = code;
	}

}