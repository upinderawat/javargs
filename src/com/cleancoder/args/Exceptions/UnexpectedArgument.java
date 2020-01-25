package com.cleancoder.args.Exceptions;

public class UnexpectedArgument extends ArgsException {
	public UnexpectedArgument() {}
	public UnexpectedArgument (String errorParameter) {super(errorParameter);}

	public UnexpectedArgument(Character errorArgumentId, String errorParameter) {
		super(errorArgumentId, errorParameter);
	}


	@Override
	public String errorMessage() {
		return String.format("Argument -%c unexpected.", super.getErrorArgumentId());
	}
}
