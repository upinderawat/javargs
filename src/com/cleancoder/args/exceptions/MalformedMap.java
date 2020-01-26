package com.cleancoder.args.exceptions;

public class MalformedMap extends ArgsException {
	public MalformedMap() {}

	public MalformedMap (String errorParameter) {
		super(errorParameter);
	}

	public MalformedMap(Character errorArgumentId, String errorParameter) {
		super(errorArgumentId, errorParameter);
	}


	@Override
	public String errorMessage() {
		return String.format("Map string for -%c is not of form k1:v1,k2:v2...", super.getErrorArgumentId());
	}
}
