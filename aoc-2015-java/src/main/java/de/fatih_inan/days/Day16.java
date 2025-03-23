package de.fatih_inan.days;

import de.fatih_inan.Day;

import java.util.ArrayList;
import java.util.List;

public class Day16 implements Day {
    @Override
    public String part1(String input) {
        AuntSue correctAuntSue = new AuntSue(0, 3, 7, 2, 3, 0, 0, 5, 3, 2, 1);

        List<AuntSue> auntSues = readAuntSues(input);

        for (AuntSue auntSue : auntSues) {
            if (isAuntSue(auntSue, correctAuntSue)) {
                return String.valueOf(auntSue.number);
            }
        }
        return "-1";
    }


    private List<AuntSue> readAuntSues(String input) {
        String[] lines = input.replace(":", "").replace(",", "").split("\n");
        List<AuntSue> auntSues = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(" ");

            AuntSue auntSue = new AuntSue(
                    Integer.parseInt(parts[1]),
                    getAmount(line, "children"),
                    getAmount(line, "cats"),
                    getAmount(line, "samoyeds"),
                    getAmount(line, "pomeranians"),
                    getAmount(line, "akitas"),
                    getAmount(line, "vizslas"),
                    getAmount(line, "goldfish"),
                    getAmount(line, "trees"),
                    getAmount(line, "cars"),
                    getAmount(line, "perfumes")
            );

            auntSues.add(auntSue);
        }

        return auntSues;
    }

    private int getAmount(String line, String property) {
        String[] parts = line.split(" ");
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equals(property)) {
                return Integer.parseInt(parts[i + 1]);
            }
        }

        return -1;
    }

    private boolean isAuntSue(AuntSue auntSue, AuntSue correctAuntSue) {
        return (auntSue.children == -1 || auntSue.children == correctAuntSue.children) &&
                (auntSue.cats == -1 || auntSue.cats == correctAuntSue.cats) &&
                (auntSue.samoyeds == -1 || auntSue.samoyeds == correctAuntSue.samoyeds) &&
                (auntSue.pomeranians == -1 || auntSue.pomeranians == correctAuntSue.pomeranians) &&
                (auntSue.akitas == -1 || auntSue.akitas == correctAuntSue.akitas) &&
                (auntSue.vizslas == -1 || auntSue.vizslas == correctAuntSue.vizslas) &&
                (auntSue.goldfish == -1 || auntSue.goldfish == correctAuntSue.goldfish) &&
                (auntSue.trees == -1 || auntSue.trees == correctAuntSue.trees) &&
                (auntSue.cars == -1 || auntSue.cars == correctAuntSue.cars) &&
                (auntSue.perfumes == -1 || auntSue.perfumes == correctAuntSue.perfumes);
    }

    @Override
    public String part2(String input) {
        AuntSue correctAuntSue = new AuntSue(0, 3, 7, 2, 3, 0, 0, 5, 3, 2, 1);

        List<AuntSue> auntSues = readAuntSues(input);

        for (AuntSue auntSue : auntSues) {
            if (isAuntSuePart2(auntSue, correctAuntSue)) {
                return String.valueOf(auntSue.number);
            }
        }
        return "-1";
    }

    private boolean isAuntSuePart2(AuntSue auntSue, AuntSue correctAuntSue) {
        return (auntSue.children == -1 || auntSue.children == correctAuntSue.children) &&
                (auntSue.cats == -1 || auntSue.cats > correctAuntSue.cats) &&
                (auntSue.samoyeds == -1 || auntSue.samoyeds == correctAuntSue.samoyeds) &&
                (auntSue.pomeranians == -1 || auntSue.pomeranians < correctAuntSue.pomeranians) &&
                (auntSue.akitas == -1 || auntSue.akitas == correctAuntSue.akitas) &&
                (auntSue.vizslas == -1 || auntSue.vizslas == correctAuntSue.vizslas) &&
                (auntSue.goldfish == -1 || auntSue.goldfish < correctAuntSue.goldfish) &&
                (auntSue.trees == -1 || auntSue.trees > correctAuntSue.trees) &&
                (auntSue.cars == -1 || auntSue.cars == correctAuntSue.cars) &&
                (auntSue.perfumes == -1 || auntSue.perfumes == correctAuntSue.perfumes);
    }

    private record AuntSue(
            int number,
            int children,
            int cats,
            int samoyeds,
            int pomeranians,
            int akitas,
            int vizslas,
            int goldfish,
            int trees,
            int cars,
            int perfumes) {
    }
}