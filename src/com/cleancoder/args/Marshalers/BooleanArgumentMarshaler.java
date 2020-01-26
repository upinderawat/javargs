package com.cleancoder.args.Marshalers;

import com.cleancoder.args.Exceptions.ArgsException;

import java.util.Iterator;

public class BooleanArgumentMarshaler implements ArgumentMarshaler {
	private boolean booleanValue = false;

	public void set(Iterator<String> currentArgument) throws ArgsException {
		booleanValue = true;
	}

	@Override
	public Object get() {
		return booleanValue;
	}
}
