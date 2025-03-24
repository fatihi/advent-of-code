package de.fatih_inan.days;

import de.fatih_inan.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day17 implements Day {
    @Override
    public String part1(String input) {
        int targetLiters = 150;

        List<Integer> containers = Arrays
                .stream(input.split("\n"))
                .map(Integer::valueOf).toList();

        List<List<Integer>> combinations = calculateCombinations(targetLiters, containers, new ArrayList<>(), 0);

        return String.valueOf(combinations.size());
    }

    private List<List<Integer>> calculateCombinations(int targetLiters, List<Integer> containers, List<Integer> currentContainers, int index) {
        if (targetLiters == 0) {
            List<List<Integer>> result = new ArrayList<>();
            result.add(new ArrayList<>(currentContainers));
            return result;
        }

        if (index == containers.size()) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();

        int currentContainer = containers.get(index);

        List<Integer> currentContainersCopy = new ArrayList<>(currentContainers);
        currentContainersCopy.add(currentContainer);

        List<List<Integer>> subCombinationsWithFirst = calculateCombinations(
                targetLiters - currentContainer,
                containers,
                currentContainersCopy,
                index + 1);
        List<List<Integer>> subCombinationsWithoutFirst = calculateCombinations(
                targetLiters,
                containers,
                currentContainers,
                index + 1);
        result.addAll(subCombinationsWithFirst);
        result.addAll(subCombinationsWithoutFirst);

        return result;
    }

    @Override
    public String part2(String input) {
        int targetLiters = 150;

        List<Integer> containers = Arrays
                .stream(input.split("\n"))
                .map(Integer::valueOf).toList();

        List<List<Integer>> combinations = calculateCombinations(targetLiters, containers, new ArrayList<>(), 0);

        int minimumNeededContainers = combinations.stream().mapToInt(List::size).min().orElse(0);

        int result = combinations.stream().filter(x -> x.size() == minimumNeededContainers).toList().size();

        return String.valueOf(result);
    }
}