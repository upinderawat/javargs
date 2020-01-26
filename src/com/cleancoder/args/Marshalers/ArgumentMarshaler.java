package com.cleancoder.args.Marshalers;

import com.cleancoder.args.Exceptions.ArgsException;

import java.util.Iterator;

public interface ArgumentMarshaler {
	void set(Iterator<String> currentArgument) throws ArgsException;
	Object get();
}
