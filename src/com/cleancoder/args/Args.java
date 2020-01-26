package com.cleancoder.args;

import com.cleancoder.args.exceptions.ArgsException;
import com.cleancoder.args.exceptions.InvalidArgumentFormat;
import com.cleancoder.args.exceptions.UnexpectedArgument;
import com.cleancoder.args.marshalers.*;

import java.util.*;


public class Args {
	private Map<Character, ArgumentMarshaler> marshalers;
	private Set<Character> argsFound;
	private ListIterator<String> currentArgument;

	public Args(String schema, String[] args) throws ArgsException {
		marshalers = new HashMap<>();
		argsFound = new HashSet<>();

		parseSchema(schema);
		parseArgumentStrings(Arrays.asList(args));
	}

	private void parseSchema(String schema) throws ArgsException {
		for (String element : schema.split(","))
			if (element.length() > 0)
				parseSchemaElement(element.trim());
	}
	private void parseSchemaElement(String element) throws ArgsException {
		char elementId = element.charAt(0);
		String elementTail = element.substring(1);
		validateSchemaElementId(elementId);
		populateMarshalerWithElement(elementId, elementTail);
	}

	private void validateSchemaElementId(char elementId) throws ArgsException {
		if (!Character.isLetter(elementId))
			throw new InvalidArgumentFormat(elementId, null);
	}
	private void populateMarshalerWithElement(char elementId, String elementTail) throws ArgsException{
		if (elementTail.length() == 0)
			marshalers.put(elementId, new BooleanArgumentMarshaler());
		else if (elementTail.equals("*"))
			marshalers.put(elementId, new StringArgumentMarshaler());
		else if (elementTail.equals("#"))
			marshalers.put(elementId, new IntegerArgumentMarshaler());
		else if (elementTail.equals("##"))
			marshalers.put(elementId, new DoubleArgumentMarshaler());
		else if (elementTail.equals("[*]"))
			marshalers.put(elementId, new StringArrayArgumentMarshaler());
		else if (elementTail.equals("&"))
			marshalers.put(elementId, new MapArgumentMarshaler());
		else
			throw new InvalidArgumentFormat(elementId, elementTail);
	}


	private void parseArgumentStrings(List<String> argsList) throws ArgsException {
		for (currentArgument = argsList.listIterator(); currentArgument.hasNext();) {
			String argString = currentArgument.next();
			if (argString.startsWith("-")) {
				parseArgumentCharacters(argString.substring(1));
			} else {
				currentArgument.previous();
				break;
			}
		}
	}

	private void parseArgumentCharacters(String argChars) throws ArgsException {
		for (int i = 0; i < argChars.length(); i++)
			parseArgumentCharacter(argChars.charAt(i));
	}

	private void parseArgumentCharacter(Character argChar) throws ArgsException {
		Optional<ArgumentMarshaler> argMarshalerOptinal = Optional.ofNullable(marshalers.get(argChar));
		argMarshalerOptinal.orElseThrow(()-> new UnexpectedArgument(argChar, null));
		argsFound.add(argChar);
		try {
			argMarshalerOptinal.get().set(currentArgument);
		} catch (ArgsException e) {
			e.setErrorArgumentId(argChar);
			throw e;
		}
	}

	public boolean has(Character arg) {
		return argsFound.contains(arg);
	}

	public int nextArgument() {
		return currentArgument.nextIndex();
	}

	public boolean getBoolean(Character arg) {
		return (boolean)marshalers.get(arg).get();
	}

	public String getString(Character arg) {
		return (String)marshalers.get(arg).get();
	}

	public int getInt(Character arg) {
		return (int)marshalers.get(arg).get();
	}

	public double getDouble(Character arg) {
		return (double)marshalers.get(arg).get();
	}

	public String[] getStringArray(Character arg) {
		return (String[])marshalers.get(arg).get();
	}

	public Map<String, String> getMap(Character arg) {
		return (Map<String, String>)marshalers.get(arg).get();
	}
}