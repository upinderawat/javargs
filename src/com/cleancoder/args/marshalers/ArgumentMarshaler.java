package com.cleancoder.args.marshalers;

import com.cleancoder.args.exceptions.ArgsException;

import java.util.Iterator;

public interface ArgumentMarshaler {
	void set(Iterator<String> currentArgument) throws ArgsException;
	Object get();
}
