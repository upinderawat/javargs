package com.cleancoder.args.exceptions;

public class InvalidDouble extends ArgsException{
	public InvalidDouble() {}

	public InvalidDouble (String errorParameter) {
		super(errorParameter);
	}

	public InvalidDouble(Character errorArgumentId, String errorParameter) {
		super(errorArgumentId, errorParameter);
	}

	@Override
	public String errorMessage() {
		return String.format("Argument -%c expects a double but was '%s'.", super.getErrorArgumentId(),super.getErrorParameter());
	}
}
