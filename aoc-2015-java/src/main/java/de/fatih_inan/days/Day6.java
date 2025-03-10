package de.fatih_inan.days;

import de.fatih_inan.Day;

public class Day6 implements Day {
    @Override
    public String part1(String input) {
        boolean[][] lights = new boolean[1000][1000];

        String[] commands = input.split("\n");
        for (String command : commands) {
            runCommand(lights, command);
        }

        return String.valueOf(countLights(lights));
    }

    private void runCommand(boolean[][] lights, String command) {
        if (command.startsWith("turn on")) {
            String[] split = command.split(" ");
            String[] topLeft = split[2].split(",");
            String[] bottomRight = split[4].split(",");
            int top = Integer.parseInt(topLeft[0]);
            int left = Integer.parseInt(topLeft[1]);
            int bottom = Integer.parseInt(bottomRight[0]);
            int right = Integer.parseInt(bottomRight[1]);

            for (int i = top; i <= bottom; i++) {
                for (int j = left; j <= right; j++) {
                    lights[i][j] = true;
                }
            }
        } else if (command.startsWith("toggle")) {
            String[] split = command.split(" ");
            String[] topLeft = split[1].split(",");
            String[] bottomRight = split[3].split(",");
            int top = Integer.parseInt(topLeft[0]);
            int left = Integer.parseInt(topLeft[1]);
            int bottom = Integer.parseInt(bottomRight[0]);
            int right = Integer.parseInt(bottomRight[1]);

            for (int i = top; i <= bottom; i++) {
                for (int j = left; j <= right; j++) {
                    lights[i][j] = !lights[i][j];
                }
            }
        } else if (command.startsWith("turn off")) {
            String[] split = command.split(" ");
            String[] topLeft = split[2].split(",");
            String[] bottomRight = split[4].split(",");
            int top = Integer.parseInt(topLeft[0]);
            int left = Integer.parseInt(topLeft[1]);
            int bottom = Integer.parseInt(bottomRight[0]);
            int right = Integer.parseInt(bottomRight[1]);

            for (int i = top; i <= bottom; i++) {
                for (int j = left; j <= right; j++) {
                    lights[i][j] = false;
                }
            }
        }
    }

    private int countLights(boolean[][] lights) {
        int count = 0;

        for (boolean[] row : lights) {
            for (boolean light : row) {
                if (light) {
                    count++;
                }
            }
        }

        return count;
    }

    @Override
    public String part2(String input) {
        int[][] lights = new int[1000][1000];

        String[] commands = input.split("\n");
        for (String command : commands) {
            runNewCommand(lights, command);
        }

        return String.valueOf(countNewLights(lights));
    }

    private void runNewCommand(int[][] lights, String command) {
        if (command.startsWith("turn on")) {
            String[] split = command.split(" ");
            String[] topLeft = split[2].split(",");
            String[] bottomRight = split[4].split(",");
            int top = Integer.parseInt(topLeft[0]);
            int left = Integer.parseInt(topLeft[1]);
            int bottom = Integer.parseInt(bottomRight[0]);
            int right = Integer.parseInt(bottomRight[1]);

            for (int i = top; i <= bottom; i++) {
                for (int j = left; j <= right; j++) {
                    lights[i][j] += 1;
                }
            }
        } else if (command.startsWith("toggle")) {
            String[] split = command.split(" ");
            String[] topLeft = split[1].split(",");
            String[] bottomRight = split[3].split(",");
            int top = Integer.parseInt(topLeft[0]);
            int left = Integer.parseInt(topLeft[1]);
            int bottom = Integer.parseInt(bottomRight[0]);
            int right = Integer.parseInt(bottomRight[1]);

            for (int i = top; i <= bottom; i++) {
                for (int j = left; j <= right; j++) {
                    lights[i][j] += 2;
                }
            }
        } else if (command.startsWith("turn off")) {
            String[] split = command.split(" ");
            String[] topLeft = split[2].split(",");
            String[] bottomRight = split[4].split(",");
            int top = Integer.parseInt(topLeft[0]);
            int left = Integer.parseInt(topLeft[1]);
            int bottom = Integer.parseInt(bottomRight[0]);
            int right = Integer.parseInt(bottomRight[1]);

            for (int i = top; i <= bottom; i++) {
                for (int j = left; j <= right; j++) {
                    lights[i][j] = Math.max(0, lights[i][j] - 1);
                }
            }
        }
    }

    private int countNewLights(int[][] lights) {
        int count = 0;

        for (int[] row : lights) {
            for (int light : row) {
                count += light;
            }
        }

        return count;
    }
}