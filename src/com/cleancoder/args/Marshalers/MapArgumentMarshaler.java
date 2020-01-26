package com.cleancoder.args.Marshalers;

import com.cleancoder.args.Exceptions.ArgsException;
import com.cleancoder.args.Exceptions.MalformedMap;
import com.cleancoder.args.Exceptions.MissingMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;


public class MapArgumentMarshaler implements ArgumentMarshaler {
	private Map<String, String> map = new HashMap<>();

	public void set(Iterator<String> currentArgument) throws ArgsException {
		try {
			String[] mapEntries = currentArgument.next().split(",");
			for (String entry : mapEntries) {
				String[] entryComponents = entry.split(":");
				if (entryComponents.length != 2)
					throw new MalformedMap();
				map.put(entryComponents[0], entryComponents[1]);
			}
		} catch (NoSuchElementException e) {
			throw new MissingMap();
		}
	}

	@Override
	public Object get() {
		return map;
	}

}
