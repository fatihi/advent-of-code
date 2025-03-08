package de.fatih_inan.days;

import de.fatih_inan.Day;

import java.util.Arrays;
import java.util.List;

public class Day2 implements Day {
    @Override
    public String part1(String input) {
        return readDimensions(input).stream()
                .map(dimension -> {
                    int area1 = dimension.length * dimension.width;
                    int area2 = dimension.length * dimension.height;
                    int area3 = dimension.width * dimension.height;

                    int slack = Math.min(Math.min(area1, area2), area3);

                    return 2 * (area1 + area2 + area3) + slack;
                })
                .reduce(Integer::sum)
                .orElse(0)
                .toString();
    }

    @Override
    public String part2(String input) {
        return readDimensions(input).stream()
                .map(dimension -> {
                    int small1 = Math.min(dimension.length, dimension.width);
                    int small2 = Math.min(Math.max(dimension.length, dimension.width), dimension.height);

                    int bow = dimension.length * dimension.width * dimension.height;

                    return 2 * (small1 + small2) + bow;
                })
                .reduce(Integer::sum)
                .orElse(0)
                .toString();
    }

    private List<Dimension> readDimensions(String input) {
        return Arrays.stream(input.split("\n"))
                .map(x -> {
                    String[] parts = x.split("x");
                    int length = Integer.parseInt(parts[0]);
                    int width = Integer.parseInt(parts[1]);
                    int height = Integer.parseInt(parts[2]);
                    return new Dimension(length, width, height);
                })
                .toList();
    }

    private record Dimension(int length, int width, int height) {
    }
}
