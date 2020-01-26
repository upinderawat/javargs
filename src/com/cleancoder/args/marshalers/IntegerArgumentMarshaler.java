package com.cleancoder.args.marshalers;

import com.cleancoder.args.exceptions.ArgsException;
import com.cleancoder.args.exceptions.InvalidInteger;
import com.cleancoder.args.exceptions.MissingInteger;

import java.util.*;

public class IntegerArgumentMarshaler implements ArgumentMarshaler {
	private int intValue = 0;

	public void set(Iterator<String> currentArgument) throws ArgsException {
		String parameter = null;
		try {
			parameter = currentArgument.next();
			intValue = Integer.parseInt(parameter);
		} catch (NoSuchElementException e) {
			throw new MissingInteger();
		} catch (NumberFormatException e) {
			throw new InvalidInteger(parameter);
		}
	}

	@Override
	public Object get() {
		return intValue;
	}

}
