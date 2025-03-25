package de.fatih_inan.days;

import de.fatih_inan.Day;

public class Day18 implements Day {
    @Override
    public String part1(String input) {
        return runGameOfLife(input, 1);
    }

    private String runGameOfLife(String input, int part) {
        int iterations = 100;

        boolean[][] startingLights = readInput(input);
        if (part == 2) {
            startingLights[0][0] = true;
            startingLights[0][startingLights[0].length - 1] = true;
            startingLights[startingLights.length - 1][0] = true;
            startingLights[startingLights.length - 1][startingLights[0].length - 1] = true;
        }
        boolean[][] endingLights = animate(startingLights, iterations, part);
        int openLights = countLights(endingLights);

        return String.valueOf(openLights);
    }

    private boolean[][] readInput(String input) {
        String[] lines = input.split("\n");
        int height = lines.length;
        int width = lines[0].length();
        boolean[][] result = new boolean[height][width];

        for (int y = 0; y < height; y++) {
            String line = lines[y];
            for (int x = 0; x < width; x++) {
                char c = line.charAt(x);
                if (c == '#') {
                    result[y][x] = true;
                }
            }
        }

        return result;
    }

    private boolean[][] animate(boolean[][] startingLights, int iterations, int part) {
        if (iterations == 0) {
            return startingLights;
        }

        boolean[][] beforeCurrentStep = animate(startingLights, iterations - 1, part);
        boolean[][] result = new boolean[beforeCurrentStep.length][beforeCurrentStep[0].length];

        for (int y = 0; y < beforeCurrentStep.length; y++) {
            for (int x = 0; x < beforeCurrentStep[y].length; x++) {
                if (part == 2 && isCorner(x, y, beforeCurrentStep.length, beforeCurrentStep[0].length)) {
                    result[y][x] = true;
                }

                if (isOn(beforeCurrentStep, x, y)) {
                    result[y][x] = true;
                }
            }
        }

        return result;
    }

    private boolean isCorner(int x, int y, int height, int width) {
        return (x == 0 || x == width - 1) && (y == 0 || y == height - 1);
    }

    private boolean isOn(boolean[][] currentLights, int x, int y) {
        int openNeighbors = 0;

        for (int y2 = y - 1; y2 <= y + 1; y2++) {
            for (int x2 = x - 1; x2 <= x + 1; x2++) {
                if (y2 >= 0 && x2 >= 0 && y2 < currentLights.length && x2 < currentLights[y2].length) {
                    if (currentLights[y2][x2]) {
                        openNeighbors++;
                    }
                }
            }
        }

        return openNeighbors == 3 || (currentLights[y][x] && openNeighbors == 4);
    }

    private int countLights(boolean[][] lights) {
        int count = 0;

        for (boolean[] line : lights) {
            for (boolean light : line) {
                if (light) {
                    count++;
                }
            }
        }

        return count;
    }

    @Override
    public String part2(String input) {
        return runGameOfLife(input, 2);
    }
}