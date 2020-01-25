package com.cleancoder.args.Marshalers;

import com.cleancoder.args.Exceptions.ArgsException;
import com.cleancoder.args.Exceptions.InvalidInteger;
import com.cleancoder.args.Exceptions.MissingInteger;

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

	public static int getValue(ArgumentMarshaler am) {
		if (am != null && am instanceof IntegerArgumentMarshaler)
			return ((IntegerArgumentMarshaler) am).intValue;
		else
			return 0;
	}
}
