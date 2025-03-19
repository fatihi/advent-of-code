package de.fatih_inan.days;

import de.fatih_inan.Day;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Iterator;

public class Day12 implements Day {
    @Override
    public String part1(String input) {
        int counter = 0;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '-' || Character.isDigit(c)) {
                counter += getValue(input.substring(i));
                do {
                    i++;
                } while (Character.isDigit(input.charAt(i)));
            }
        }

        return String.valueOf(counter);
    }

    private int getValue(String substring) {
        int buffer = 0;
        boolean negative = false;

        for (char c : substring.toCharArray()) {
            if (c == '-') {
                negative = true;
            } else if (Character.isDigit(c)) {
                if (negative) {
                    buffer = buffer * 10 - Character.getNumericValue(c);
                } else {
                    buffer = buffer * 10 + Character.getNumericValue(c);
                }
            } else {
                return buffer;
            }
        }

        return buffer;
    }

    @Override
    public String part2(String input) {
        Object json = new JSONTokener(input).nextValue();

        return String.valueOf(getJSONValue(json));
    }

    private int getJSONValue(Object json) {
        return switch (json) {
            case JSONObject o -> getJSONObjectValue(o);
            case JSONArray a -> getJSONArrayValue(a);
            case String s -> getValue(s);
            case Integer i -> i;
            default -> 0;
        };
    }

    private int getJSONObjectValue(JSONObject object) {
        int buffer = 0;
        Iterator<String> keys = object.keys();

        while (keys.hasNext()) {
            String key = keys.next();
            Object value = object.get(key);
            if (value instanceof String && value.equals("red")) {
                return 0;
            } else {
                buffer += getJSONValue(value);
            }
        }

        return buffer;
    }

    private int getJSONArrayValue(JSONArray array) {
        int buffer = 0;

        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);

            buffer += getJSONValue(value);
        }

        return buffer;
    }
}
