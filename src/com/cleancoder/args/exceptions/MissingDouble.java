package com.cleancoder.args.exceptions;

public class MissingDouble extends ArgsException {
	public MissingDouble() {}

	public MissingDouble (String errorParameter) {
		super(errorParameter);
	}

	public MissingDouble(Character errorArgumentId, String errorParameter) {
		super(errorArgumentId, errorParameter);
	}

	@Override
	public String errorMessage() {
		return String.format("Could not find double parameter for -%c.", super.getErrorArgumentId());
	}
}
