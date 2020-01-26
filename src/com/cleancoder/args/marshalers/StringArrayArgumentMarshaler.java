package com.cleancoder.args.marshalers;


import com.cleancoder.args.exceptions.ArgsException;
import com.cleancoder.args.exceptions.MissingString;

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

	@Override
	public Object get() {
		return strings.toArray(new String[0]);
	}

}
