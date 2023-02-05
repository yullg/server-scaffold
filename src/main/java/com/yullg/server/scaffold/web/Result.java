package com.yullg.server.scaffold.web;

public class Result<T> {

	public static final int CODE_OK = 0;
	public static final int CODE_NO = -1;
	public static final String MESSAGE_OK = "OK";
	public static final String MESSAGE_NO = "NO";

	private final int code;
	private final String message;
	private final T data;

	protected Result(int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public Result(T data) {
		this(CODE_OK, MESSAGE_OK, data);
	}

	public Result(String message, T data) {
		this(CODE_OK, message, data);
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public T getData() {
		return data;
	}

}