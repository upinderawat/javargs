package com.cleancoder.args.Marshalers;

import com.cleancoder.args.Exceptions.ArgsException;
import com.cleancoder.args.Exceptions.MissingString;

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

	public static String getValue(ArgumentMarshaler am) {
		if (am != null && am instanceof StringArgumentMarshaler)
			return ((StringArgumentMarshaler) am).stringValue;
		else
			return "";
	}
}
