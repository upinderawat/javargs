package com.cleancoder.args.Exceptions;

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
		return String.format("'%s' is not a valid argument format.", super.getErrorParameter());
	}
}
