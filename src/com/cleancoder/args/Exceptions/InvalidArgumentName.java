package com.cleancoder.args.Exceptions;

public class InvalidArgumentName extends ArgsException {
	public InvalidArgumentName() {
	}

	public InvalidArgumentName(String errorParameter) {
		super(errorParameter);
	}

	public InvalidArgumentName(Character errorArgumentId, String errorParameter) {
		super(errorArgumentId, errorParameter);
	}

	@Override
	public String errorMessage() {
		return String.format("'%c' is not a valid argument name.", super.getErrorArgumentId());
	}
}
