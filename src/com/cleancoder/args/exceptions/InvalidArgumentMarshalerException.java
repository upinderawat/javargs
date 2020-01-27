package com.cleancoder.args.exceptions;

public class InvalidArgumentMarshalerException extends ArgsException {
	public InvalidArgumentMarshalerException() {}

	public InvalidArgumentMarshalerException (String errorParameter) {
		super(errorParameter);
	}

	public InvalidArgumentMarshalerException(Character errorArgumentId, String errorParameter) {
		super(errorArgumentId, errorParameter);
	}

	@Override
	public String errorMessage() {
		String errorParameter = super.getErrorParameter();
		return errorParameter;
	}
}
