package com.cleancoder.args.Marshalers;


import com.cleancoder.args.Exceptions.ArgsException;
import com.cleancoder.args.Exceptions.MissingString;

import java.util.*;

public class StringArrayArgumentMarshaler implements ArgumentMarshaler {
	private List<String> strings = new ArrayList<>();

	public void set(Iterator<String> currentArgument) throws ArgsException {
		try {
			strings.add(currentArgument.next());
		} catch (NoSuchElementException e) {
			throw new MissingString();
		}
	}

	public static String[] getValue(ArgumentMarshaler am) {
		if (am != null && am instanceof StringArrayArgumentMarshaler)
			return ((StringArrayArgumentMarshaler) am).strings.toArray(new String[0]);
		else
			return new String[0];
	}
}
