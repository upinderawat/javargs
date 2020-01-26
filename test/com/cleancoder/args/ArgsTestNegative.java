package com.cleancoder.args;

import com.cleancoder.args.exceptions.ArgsException;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class ArgsTestNegative {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(ArgsTestNegative.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());
	}

	@Test
	public void testNoSchemaOneArgument() throws Exception {
		try {

			new Args("", new String[]{"-x"});
			fail();
		} catch (ArgsException e) {
			assertEquals(Character.valueOf('x'), e.getErrorArgumentId());
		}
	}

	@Test
	public void testNoSchemaMultipleArguments() throws Exception {
		try {
			new Args("", new String[]{"-x", "-y","-z"});
			fail();
		} catch (ArgsException e) {
			assertEquals(Character.valueOf('x'), e.getErrorArgumentId());
		}
	}

	@Test
	public void testNonLetterSchema() throws Exception {
		try {
			new Args("*", new String[]{});
			fail("Args constructor should have thrown exception");
		} catch (ArgsException e) {
			assertEquals(Character.valueOf('*'), e.getErrorArgumentId());
		}
	}

	@Test
	public void testInvalidArgumentFormat() throws Exception {
		try {
			new Args("f~", new String[]{});
			fail("Args constructor should have throws exception");
		} catch (ArgsException e) {
			assertEquals(Character.valueOf('f'), e.getErrorArgumentId());
		}
	}

	@Test
	public void testMissingStringArgument() throws Exception {
		try {
			new Args("x*", new String[]{"-x"});
			fail();
		} catch (ArgsException e) {
			System.out.println(e.errorMessage());
			assertEquals(Character.valueOf('x'), e.getErrorArgumentId());
		}
	}

	@Test
	public void testSpacesInFormat() throws Exception {
		Args args = new Args("x, y", new String[]{"-xy"});
		assertTrue(args.has('x'));
		assertTrue(args.has('y'));
		assertEquals(1, args.nextArgument());
	}

	@Test
	public void testInvalidInteger() throws Exception {
		try {
			new Args("x#", new String[]{"-x", "Forty two"});
			fail();
		} catch (ArgsException e) {
			assertEquals("Forty two", e.getErrorParameter());
		}
	}

	@Test
	public void testMissingInteger() throws Exception {
		try {
			new Args("x#", new String[]{"-x"});
			fail();
		} catch (ArgsException e) {
			assertEquals(Character.valueOf('x'), e.getErrorArgumentId());
		}
	}

	@Test
	public void testInvalidDouble() throws Exception {
		try {
			new Args("x##", new String[]{"-x", "Forty two"});
			fail();
		} catch (ArgsException e) {
			assertEquals(Character.valueOf('x'), e.getErrorArgumentId());
			assertEquals("Forty two", e.getErrorParameter());
		}
	}

	@Test
	public void testMissingDouble() throws Exception {
		try {
			new Args("x##", new String[]{"-x"});
			fail();
		} catch (ArgsException e) {
			assertEquals(Character.valueOf('x'), e.getErrorArgumentId());
		}
	}

	@Test
	public void testMissingStringArrayElement() throws Exception {
		try {
			new Args("x[*]", new String[] {"-x"});
			fail();
		} catch (ArgsException e) {
			assertEquals(Character.valueOf('x'), e.getErrorArgumentId());
		}
	}

	@Test(expected=ArgsException.class)
	public void malFormedMapArgument() throws Exception {
		Args args = new Args("f&", new String[] {"-f", "key1:val1,key2"});
	}

	@Test
	public void testExtraArgumentsThatLookLikeFlags() throws Exception {
		Args args = new Args("x,y", new String[]{"-x", "alpha", "-y", "beta"});
		assertTrue(args.has('x'));
		assertFalse(args.has('y'));
		assertTrue(args.getBoolean('x'));
		assertFalse(args.getBoolean('y'));
		assertEquals(1, args.nextArgument());
	}

}
