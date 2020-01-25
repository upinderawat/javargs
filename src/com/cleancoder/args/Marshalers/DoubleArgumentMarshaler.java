package com.cleancoder.args.Marshalers;
import com.cleancoder.args.Exceptions.ArgsException;
import com.cleancoder.args.Exceptions.InvalidDouble;
import com.cleancoder.args.Exceptions.MissingDouble;

import java.util.*;

public class DoubleArgumentMarshaler implements ArgumentMarshaler {
	private double doubleValue = 0;

	public void set(Iterator<String> currentArgument) throws ArgsException {
		String parameter = null;
		try {
			parameter = currentArgument.next();
			doubleValue = Double.parseDouble(parameter);
		} catch (NoSuchElementException e) {
			throw new MissingDouble();
		} catch (NumberFormatException e) {
			throw new InvalidDouble(parameter);
		}
	}

	public static double getValue(ArgumentMarshaler am) {
		if (am != null && am instanceof DoubleArgumentMarshaler)
			return ((DoubleArgumentMarshaler) am).doubleValue;
		else
			return 0.0;
	}
}
