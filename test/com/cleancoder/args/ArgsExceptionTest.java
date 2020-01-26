package com.cleancoder.args;

import com.cleancoder.args.exceptions.*;
import junit.framework.TestCase;


public class ArgsExceptionTest extends TestCase {
	public void testUnexpectedMessage() throws Exception {
		ArgsException e = new UnexpectedArgument('x', null);
		assertEquals("Argument -x unexpected.", e.errorMessage());
	}

	public void testMissingStringMessage() throws Exception {
		ArgsException e = new MissingString('x', null);
		assertEquals("Could not find string parameter for -x.", e.errorMessage());
	}

	public void testInvalidIntegerMessage() throws Exception {
		ArgsException e = new InvalidInteger('x', "Forty two");
		assertEquals("Argument -x expects an integer but was 'Forty two'.", e.errorMessage());
	}

	public void testMissingIntegerMessage() throws Exception {
		ArgsException e = new MissingInteger('x', null);
		assertEquals("Could not find integer parameter for -x.", e.errorMessage());
	}

	public void testInvalidDoubleMessage() throws Exception {
		ArgsException e = new InvalidDouble('x', "Forty two");
		assertEquals("Argument -x expects a double but was 'Forty two'.", e.errorMessage());
	}

	public void testMissingDoubleMessage() throws Exception {
		ArgsException e = new MissingDouble('x', null);
		assertEquals("Could not find double parameter for -x.", e.errorMessage());
	}

	public void testMissingMapMessage() throws Exception {
		ArgsException e = new MissingMap('x', null);
		assertEquals("Could not find map string for -x.", e.errorMessage());
	}

	public void testMalformedMapMessage() throws Exception {
		ArgsException e = new MalformedMap('x', null);
		assertEquals("Map string for -x is not of form k1:v1,k2:v2...", e.errorMessage());
	}

	public void testInvalidArgumentName() throws Exception {
		ArgsException e = new InvalidArgumentName('#', null);
		assertEquals("'#' is not a valid argument name.", e.errorMessage());
	}

	public void testInvalidFormat() throws Exception {
		ArgsException e = new InvalidArgumentFormat('x', "$");
		assertEquals("'$' is not a valid argument format.", e.errorMessage());
	}
}

