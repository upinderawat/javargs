package com.cleancoder.args.exceptions;

public class InvalidArgumentFormat extends ArgsException{
	public InvalidArgumentFormat() {}

	public InvalidArgumentFormat (String errorParameter) {
		super(errorParameter);
	}

	public InvalidArgumentFormat(Character errorArgumentId, String errorParameter) {
		super(errorArgumentId, errorParameter);
	}

	@Override
	public String errorMessage() {
		Character errorArgumentId = super.getErrorArgumentId();
		String errorParameter = super.getErrorParameter();
		return String.format("'%s' is not a valid argument format.", errorParameter);

	}
}
