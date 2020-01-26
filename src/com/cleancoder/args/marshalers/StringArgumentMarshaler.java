package com.cleancoder.args.marshalers;

import com.cleancoder.args.exceptions.ArgsException;
import com.cleancoder.args.exceptions.MissingString;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class StringArgumentMarshaler implements ArgumentMarshaler {
	private String stringValue = "";

	public void set(Iterator<String> currentArgument) throws ArgsException {
		try {
			stringValue = currentArgument.next();
		} catch (NoSuchElementException e) {
			throw new MissingString();
		}
	}

	@Override
	public Object get() {
		return stringValue;
	}

}
