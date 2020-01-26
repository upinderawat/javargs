package com.cleancoder.args.exceptions;

public class MissingInteger extends ArgsException {
	public MissingInteger() {}

	public MissingInteger (String errorParameter) {
		super(errorParameter);
	}

	public MissingInteger(Character errorArgumentId, String errorParameter) {
		super(errorArgumentId, errorParameter);
	}

	@Override
	public String errorMessage() {
		return String.format("Could not find integer parameter for -%c.", super.getErrorArgumentId());
	}
}
