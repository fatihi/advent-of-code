package de.fatih_inan.days;

import de.fatih_inan.Day;

public class Day10 implements Day {
    @Override
    public String part1(String input) {
        for (int i = 0; i < 40; i++) {
            input = runLookAndSay(input);
        }
        return String.valueOf(input.length());
    }

    private String runLookAndSay(String input) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            int counter = 0;
            while (i < input.length() && input.charAt(i) == c) {
                counter++;
                i++;
            }
            i--;
            result.append(counter).append(c);
        }
        return result.toString();
    }

    @Override
    public String part2(String input) {
        for (int i = 0; i < 50; i++) {
            input = runLookAndSay(input);
        }
        return String.valueOf(input.length());
    }
}
