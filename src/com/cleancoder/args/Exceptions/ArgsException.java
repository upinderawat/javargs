package com.cleancoder.args.Exceptions;

public abstract class ArgsException extends Exception {
	private Character errorArgumentId = '\0';
	private String errorParameter = null;

	public ArgsException() {}

	public ArgsException(String errorParameter) {
		this.errorParameter = errorParameter;
	}


	public ArgsException(Character errorArgumentId, String errorParameter) {
		this.errorParameter = errorParameter;
		this.errorArgumentId = errorArgumentId;
	}

	public abstract String errorMessage();

	public Character getErrorArgumentId() {
		return errorArgumentId;
	}

	public void setErrorArgumentId(Character errorArgumentId) {
		this.errorArgumentId = errorArgumentId;
	}

	public String getErrorParameter() {
		return errorParameter;
	}

	public void setErrorParameter(String errorParameter) {
		this.errorParameter = errorParameter;
	}

}
