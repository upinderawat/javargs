package com.cleancoder.args;

import com.cleancoder.args.exceptions.ArgsException;

public class ArgsMain {
	public static void main(String[] args) {
		try {
//			Args arg = new Args("b, a[*], p#", new String[]{"-b", "-a", "e3","-a","e4","-p", "12"});
			Args arg = new Args("x,y", new String[]{"-x", "alpha", "-y", "beta"});//			int port = arg.getInt('p');
			System.out.println(arg.getBoolean('x'));
			System.out.println(arg.getBoolean('y'));
		} catch (ArgsException e) {
			System.out.printf("Argument error: %s\n", e.errorMessage());
		}
	}

	private static void executeApplication(String directory) {
		System.out.printf(" directory:%s\n" ,directory);
	}
}