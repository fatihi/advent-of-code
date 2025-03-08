package de.fatih_inan.days;

import de.fatih_inan.Day;

public class Day1 implements Day {
    @Override
    public String part1(String input) {
        int floor = 0;
        for (char c : input.toCharArray()) {
            floor += getFloorChange(c);
        }
        return String.valueOf(floor);
    }

    @Override
    public String part2(String input) {
        int floor = 0;
        int counter = 0;
        for (char c : input.toCharArray()) {
            counter++;
            floor += getFloorChange(c);

            if (floor < 0) {
                break;
            }
        }
        return String.valueOf(counter);
    }

    private int getFloorChange(char input) {
        return switch (input) {
            case '(' -> 1;
            case ')' -> -1;
            default -> 0;
        };
    }
}
