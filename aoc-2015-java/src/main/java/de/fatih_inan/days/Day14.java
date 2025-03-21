package de.fatih_inan.days;

import de.fatih_inan.Day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14 implements Day {
    @Override
    public String part1(String input) {
        List<Reindeer> reindeers = readReindeers(input);

        int seconds = 2503;
        Map<String, Integer> distances = new HashMap<>();
        reindeers.forEach(reindeer -> distances.put(reindeer.name, 0));

        for (int i = 0; i < seconds; i++) {
            for (Reindeer reindeer : reindeers) {
                if (isReindeerMoving(reindeer, i)) {
                    distances.put(reindeer.name, distances.get(reindeer.name) + reindeer.speed);
                }
            }
        }

        return String.valueOf(distances.values().stream().max(Integer::compareTo).orElse(0));
    }

    private List<Reindeer> readReindeers(String input) {
        List<Reindeer> reindeers = new ArrayList<>();

        String[] lines = input.split("\n");

        for (String line : lines) {
            String[] parts = line.split(" ");
            String name = parts[0];
            int speed = Integer.parseInt(parts[3]);
            int endurance = Integer.parseInt(parts[6]);
            int resting = Integer.parseInt(parts[13]);
            reindeers.add(new Reindeer(name, speed, endurance, resting));
        }

        return reindeers;
    }

    private boolean isReindeerMoving(Reindeer reindeer, int i) {
        i = i % (reindeer.endurance + reindeer.resting);

        return i < reindeer.endurance;
    }

    @Override
    public String part2(String input) {
        List<Reindeer> reindeers = readReindeers(input);

        int seconds = 2503;
        Map<String, Integer> distances = new HashMap<>();
        Map<String, Integer> points = new HashMap<>();
        reindeers.forEach(reindeer -> distances.put(reindeer.name, 0));
        reindeers.forEach(reindeer -> points.put(reindeer.name, 0));

        for (int i = 0; i < seconds; i++) {
            for (Reindeer reindeer : reindeers) {
                if (isReindeerMoving(reindeer, i)) {
                    distances.put(reindeer.name, distances.get(reindeer.name) + reindeer.speed);
                }
            }
            int highestDistance = distances.values().stream().max(Integer::compareTo).orElse(0);
            for (String name : distances.keySet()) {
                if (distances.get(name) == highestDistance) {
                    points.put(name, points.get(name) + 1);
                }
            }
        }

        return String.valueOf(points.values().stream().max(Integer::compareTo).orElse(0));
    }

    private record Reindeer(String name, int speed, int endurance, int resting) {
    }
}