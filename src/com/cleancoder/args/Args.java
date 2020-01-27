package com.cleancoder.args;

import com.cleancoder.args.exceptions.*;
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
				throw new InvalidArgumentFormat(argString);
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

	public boolean getBoolean(Character arg) throws ArgsException{
		if(marshalers.containsKey(arg)){
			ArgumentMarshaler booleanMarshaler = marshalers.get(arg);
			if(booleanMarshaler instanceof BooleanArgumentMarshaler){
				return (boolean)booleanMarshaler.get();
			}
			else{
				throw new InvalidArgumentMarshalerException("\nExpected: BooleanMarshaler\nActual: "+ booleanMarshaler.toString());
			}
		}
		else{
			throw new InvalidArgumentName(arg, null);
		}
	}

	public String getString(Character arg) throws ArgsException{
		if(marshalers.containsKey(arg)){
			ArgumentMarshaler stringMarshaler = marshalers.get(arg);
			if(stringMarshaler instanceof StringArgumentMarshaler){
				return (String)stringMarshaler.get();
			}
			else{
				throw new InvalidArgumentMarshalerException("\nExpected: StringMarshaler\nActual: "+ stringMarshaler.toString());
			}
		}
		else{
			throw new InvalidArgumentName(arg, null);
		}
	}

	public int getInt(Character arg) throws ArgsException{
		if(marshalers.containsKey(arg)){
			ArgumentMarshaler intMarshaler = marshalers.get(arg);
			if(intMarshaler instanceof IntegerArgumentMarshaler){
				return (int)intMarshaler.get();
			}
			else{
				throw new InvalidArgumentMarshalerException("\nExpected: IntMarshaler\nActual: "+ intMarshaler.toString());
			}
		}
		else{
			throw new InvalidArgumentName(arg, null);
		}
	}

	public double getDouble(Character arg) throws ArgsException{
		if(marshalers.containsKey(arg)){
			ArgumentMarshaler doubleMarshaler = marshalers.get(arg);
			if(doubleMarshaler instanceof DoubleArgumentMarshaler){
				return (double)doubleMarshaler.get();
			}
			else{
				throw new InvalidArgumentMarshalerException("\nExpected: DoubleMarshaler\nActual: "+ doubleMarshaler.toString());
			}
		}
		else{
			throw new InvalidArgumentName(arg, null);
		}
	}

	public String[] getStringArray(Character arg) throws ArgsException{
		if(marshalers.containsKey(arg)){
			ArgumentMarshaler stringArray = marshalers.get(arg);
			if(stringArray instanceof StringArrayArgumentMarshaler){
				return (String[]) stringArray.get();
			}
			else{
				throw new InvalidArgumentMarshalerException("\nExpected: StringArrayMarshaler\nActual: "+stringArray.toString());
			}
		}
		else{
			throw new InvalidArgumentName(arg, null);
		}
	}

	public Map<String, String> getMap(Character arg) throws ArgsException{
		if(marshalers.containsKey(arg)){
			ArgumentMarshaler mapMarshaler = marshalers.get(arg);
			if(mapMarshaler instanceof MapArgumentMarshaler){
				return (Map<String, String>)mapMarshaler.get();
			}
			else{
				throw new InvalidArgumentMarshalerException("\nExpected: MapMarshaler\nActual: "+mapMarshaler.toString());
			}
		}
		else{
			throw new InvalidArgumentName(arg, null);
		}
	}
}