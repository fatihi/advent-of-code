package de.fatih_inan.days;

import de.fatih_inan.Day;

import java.util.Arrays;

public class Day8 implements Day {
    @Override
    public String part1(String input) {
        int result = Arrays.stream(input.split("\n"))
                .map(this::countEscaped)
                .reduce(Integer::sum)
                .orElse(0);

        return String.valueOf(result);
    }

    private int countEscaped(String input) {
        int counter = 2;
        for (int i = 1; i < input.length() - 1; i++) {
            char c = input.charAt(i);

            if (c == '\\') {
                if (input.charAt(i + 1) == 'x') {
                    counter += 3;
                    i += 3;
                } else {
                    counter++;
                    i++;
                }
            }
        }
        return counter;
    }

    @Override
    public String part2(String input) {
        int result = Arrays.stream(input.split("\n"))
                .map(this::countEscaping)
                .reduce(Integer::sum)
                .orElse(0);

        return String.valueOf(result);
    }

    private int countEscaping(String input) {
        int counter = 2;
        for (char c : input.toCharArray()) {
            if (c == '\\') {
                counter++;
            }

            if (c == '"') {
                counter++;
            }
        }
        return counter;
    }
}
