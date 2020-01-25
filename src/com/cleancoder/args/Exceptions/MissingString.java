package com.cleancoder.args.Exceptions;

public class MissingString extends ArgsException {
	public MissingString() {}
	public MissingString (String errorParameter) {
		super(errorParameter);
	}

	public MissingString(Character errorArgumentId, String errorParameter) {
		super(errorArgumentId, errorParameter);
	}


	@Override
	public String errorMessage() {
		return String.format("Could not find string parameter for -%c.", super.getErrorArgumentId());
	}
}
