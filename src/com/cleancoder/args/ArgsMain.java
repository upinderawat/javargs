package com.cleancoder.args;

import com.cleancoder.args.Exceptions.ArgsException;

public class ArgsMain {
	public static void main(String[] args) {
		try {
			new Args("x#", new String[]{"-x", "42"});
//			Args arg = new Args("l,p#,d*", args);
//			boolean logging = arg.getBoolean('l');
//			int port = arg.getInt('p');
//			String directory = arg.getString('d');
//			executeApplication(logging, port, directory);
		} catch (ArgsException e) {
			System.out.printf("Argument error: %s\n", e.errorMessage());
		}
	}

	private static void executeApplication(boolean logging, int port, String directory) {
		System.out.printf("logging is %s, port:%d, directory:%s\n",logging, port, directory);
	}
}