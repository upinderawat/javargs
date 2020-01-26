package com.cleancoder.args.exceptions;

public class InvalidInteger extends ArgsException{
	public InvalidInteger() {}

	public InvalidInteger (String errorParameter) {
		super(errorParameter);
	}

	public InvalidInteger(Character errorArgumentId, String errorParameter) {
		super(errorArgumentId, errorParameter);
	}

	@Override
	public String errorMessage() {
		return String.format("Argument -%c expects an integer but was '%s'.", super.getErrorArgumentId(),super.getErrorParameter());
	}
}
