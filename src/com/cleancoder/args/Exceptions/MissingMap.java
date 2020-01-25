package com.cleancoder.args.Exceptions;

public class MissingMap extends ArgsException {
	public MissingMap() {}
	public MissingMap (String errorParameter) {
		super(errorParameter);
	}

	public MissingMap(Character errorArgumentId, String errorParameter) {
		super(errorArgumentId, errorParameter);
	}

	@Override
	public String errorMessage() {
		return String.format("Could not find map string for -%c.", super.getErrorArgumentId());
	}
}
