package com.cleancoder.args;

import com.cleancoder.args.exceptions.ArgsException;

public class ArgsMain {
	public static void main(String[] args) {
		try {
			Args arg = new Args("x#,y", new String[]{"-x","42","-y"});
			arg.getString('x');
		} catch (ArgsException e) {
			System.out.printf("Argument error: %s\n", e.errorMessage());
		}
	}

	private static void executeApplication(String directory) {
		System.out.printf(" directory:%s\n" ,directory);
	}
}