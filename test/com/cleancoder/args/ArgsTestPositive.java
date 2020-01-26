package com.cleancoder.args;

import com.cleancoder.args.exceptions.ArgsException;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.Map;

import static org.junit.Assert.*;


public class ArgsTestPositive {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(ArgsTestPositive.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());
	}

	@Test
	public void testNoSchemaNoArguments() throws Exception {
		Args args = new Args("", new String[0]);
		assertEquals(0, args.nextArgument());
	}

	@Test
	public void testSimpleBooleanPresent() throws Exception {
		Args args = new Args("x", new String[]{"-x"});
		assertEquals(true, args.getBoolean('x'));
		assertEquals(1, args.nextArgument());
	}

	@Test
	public void testMulipleBooleanPresent() throws Exception {
		Args args = new Args("x, y", new String[]{"-x", "-y"});
		assertTrue(args.getBoolean('x'));
		assertTrue(args.getBoolean('y'));
	}

	@Test
	public void testSimpleStringPresent() throws Exception {
		Args args = new Args("x*", new String[]{"-x", "param"});
		assertTrue(args.has('x'));
		assertEquals("param", args.getString('x'));
		assertEquals(2, args.nextArgument());
	}

	@Test
	public void testMultipleStringPresent() throws Exception {
		Args args = new Args("x*,y*", new String[]{"-x", "upinder", "-y", "singh"});
		assertEquals("upinder", args.getString('x'));
		assertEquals("singh", args.getString('y'));
		assertEquals(4, args.nextArgument());
	}

	@Test
	public void testSimpleIntPresent() throws Exception {
		Args args = new Args("x#", new String[]{"-x", "42"});
		assertTrue(args.has('x'));
		assertEquals(42, args.getInt('x'));
		assertEquals(2, args.nextArgument());
	}

	@Test
	public void testMultipleIntPresent() throws Exception {
		Args args = new Args("x#, y#", new String[]{"-x", "42", "-y", "55"});
		assertTrue(args.has('x'));
		assertEquals(42, args.getInt('x'));
		assertEquals(55, args.getInt('y'));
		assertEquals(4, args.nextArgument());
	}

	@Test
	public void testSimpleDoublePresent() throws Exception {
		Args args = new Args("x##", new String[]{"-x", "42.3"});
		assertTrue(args.has('x'));
		assertEquals(42.3, args.getDouble('x'), .001);
	}

	@Test
	public void testMultipleDoublePresent() throws Exception {
		Args args = new Args("x##, y##", new String[]{"-x", "42.3", "-y", "24.23"});
		assertTrue(args.has('x'));
		assertTrue(args.has('y'));
		assertEquals(42.3, args.getDouble('x'), .001);
		assertEquals(24.23, args.getDouble('y'), .001);
	}

	@Test
	public void testSimpleStringArrayPresent() throws Exception {
		Args args = new Args("x[*]", new String[]{"-x", "alpha"});
		assertTrue(args.has('x'));
		String[] result = args.getStringArray('x');
		assertEquals(1, result.length);
		assertEquals("alpha", result[0]);
	}

	@Test
	public void testMultipleStringArrayPresent() throws Exception {
		Args args = new Args("x[*], y[*]", new String[]{"-x", "alpha", "-y", "beta"});
		assertTrue(args.has('x'));
		String[] result = args.getStringArray('x');
		assertEquals(1, result.length);
		assertEquals("alpha", result[0]);
		result = args.getStringArray('y');
		assertEquals(1, result.length);
		assertEquals("beta", result[0]);

	}

	@Test
	public void testManyStringArrayElements() throws Exception {
		Args args = new Args("x[*]", new String[]{"-x", "alpha", "-x", "beta", "-x", "gamma"});
		assertTrue(args.has('x'));
		String[] result = args.getStringArray('x');
		assertEquals(3, result.length);
		assertEquals("alpha", result[0]);
		assertEquals("beta", result[1]);
		assertEquals("gamma", result[2]);
	}

	@Test
	public void testSimpleMapPresent() throws Exception {
		Args args = new Args("f&", new String[]{"-f", "key1:val1"});
		assertTrue(args.has('f'));
		Map<String, String> map = args.getMap('f');
		assertEquals("val1", map.get("key1"));
	}

	@Test
	public void testMultipleMapPresent() throws Exception {
		Args args = new Args("f&", new String[]{"-f", "key1:val1,key2:val2"});
		assertTrue(args.has('f'));
		Map<String, String> map = args.getMap('f');
		assertEquals("val1", map.get("key1"));
		assertEquals("val2", map.get("key2"));
	}

	@Test
	public void testIntegratedArguments() throws Exception {
		Args args = new Args("a,b#,c##,d[*],e&,f##,g*,h[*],i&,k*,l#,m",
				new String[]{
						"-a", "-b", "12", "-c", "94.22", "-d", "abc", "-d", "edf",
						"-e", "key1:val1,key2:val2", "-f", "62.33", "-g", "upinder",
						"-h", "ghi", "-h", "jkl", "-i", "key3:val3,key4:val4", "-k",
						"singh", "-l", "76"
				});
		assertTrue(args.getBoolean('a'));
		assertFalse(args.getBoolean('m'));
		assertEquals(12, args.getInt('b'));
		assertEquals(76, args.getInt('l'));
		assertEquals(94.22, args.getDouble('c'), .001);
		assertEquals(62.33, args.getDouble('f'), .001);
		assertArrayEquals(new String[]{"abc","edf"}, args.getStringArray('d'));
		Map<String, String> map = args.getMap('e');
		assertEquals("val1", map.get("key1"));
		assertEquals("val2", map.get("key2"));
		assertEquals(62.33, args.getDouble('f'), .001);
		assertEquals("upinder", args.getString('g'));
		assertArrayEquals(new String[]{"ghi","jkl"}, args.getStringArray('h'));
		map = args.getMap('i');
		assertEquals("val3", map.get("key3"));
		assertEquals("val4", map.get("key4"));
		assertEquals("singh", args.getString('k'));
		assertEquals(76, args.getInt('l'));
	}

}
