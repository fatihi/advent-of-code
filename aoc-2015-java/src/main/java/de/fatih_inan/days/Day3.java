package de.fatih_inan.days;

import de.fatih_inan.Day;

import java.util.HashSet;
import java.util.Set;

public class Day3 implements Day {
    @Override
    public String part1(String input) {
        Coordinate currentCoordinate = new Coordinate(0, 0);
        Set<Coordinate> coordinates = new HashSet<>();
        coordinates.add(currentCoordinate);

        for (char c : input.toCharArray()) {
            currentCoordinate = getNewCoordinate(currentCoordinate, c);
            coordinates.add(currentCoordinate);
        }

        return String.valueOf(coordinates.size());
    }

    private Coordinate getNewCoordinate(Coordinate currentCoordinate, char c) {
        return switch (c) {
            case '<' -> new Coordinate(currentCoordinate.x - 1, currentCoordinate.y);
            case '>' -> new Coordinate(currentCoordinate.x + 1, currentCoordinate.y);
            case '^' -> new Coordinate(currentCoordinate.x, currentCoordinate.y + 1);
            case 'v' -> new Coordinate(currentCoordinate.x, currentCoordinate.y - 1);
            default -> currentCoordinate;
        };
    }

    @Override
    public String part2(String input) {
        Coordinate santaCoordinate = new Coordinate(0, 0);
        Coordinate robotCoordinate = new Coordinate(0, 0);

        Set<Coordinate> coordinates = new HashSet<>();
        coordinates.add(santaCoordinate);

        for (int i = 0; i < input.length(); i++) {
            if (i % 2 == 0) {
                santaCoordinate = getNewCoordinate(santaCoordinate, input.charAt(i));
                coordinates.add(santaCoordinate);
            } else {
                robotCoordinate = getNewCoordinate(robotCoordinate, input.charAt(i));
                coordinates.add(robotCoordinate);
            }
        }

        return String.valueOf(coordinates.size());
    }

    private record Coordinate(int x, int y) {
    }
}
