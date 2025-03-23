package de.fatih_inan.days;

import de.fatih_inan.Day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day15 implements Day {
    @Override
    public String part1(String input) {
        int teaspoons = 100;

        List<Ingredient> ingredients = readIngredients(input);

        List<Map<Ingredient, Integer>> distributions = calculateDistributions(teaspoons, ingredients);
        List<Integer> scores = distributions.stream().map(this::calculateScore).toList();
        int highestScore = scores.stream().max(Integer::compareTo).orElse(0);

        return String.valueOf(highestScore);
    }

    private List<Ingredient> readIngredients(String input) {
        String[] lines = input.split("\n");
        List<Ingredient> ingredients = new ArrayList<>();

        for (String line : lines) {
            line = line.replaceAll(":", "").replaceAll(",", "");
            String[] parts = line.split(" ");

            String name = parts[0];
            int capacity = Integer.parseInt(parts[2]);
            int durability = Integer.parseInt(parts[4]);
            int flavor = Integer.parseInt(parts[6]);
            int texture = Integer.parseInt(parts[8]);
            int calories = Integer.parseInt(parts[10]);

            Ingredient ingredient = new Ingredient(name, capacity, durability, flavor, texture, calories);
            ingredients.add(ingredient);
        }

        return ingredients;
    }

    private List<Map<Ingredient, Integer>> calculateDistributions(int teaspoons, List<Ingredient> ingredients) {
        if (ingredients.size() == 1) {
            List<Map<Ingredient, Integer>> result = new ArrayList<>();

            Map<Ingredient, Integer> map = new HashMap<>();
            map.put(ingredients.getFirst(), teaspoons);
            result.add(map);

            return result;
        }

        Ingredient first = ingredients.getFirst();
        List<Map<Ingredient, Integer>> result = new ArrayList<>();

        for (int i = 1; i <= teaspoons - ingredients.size() + 1; i++) {
            int firstTeaspoons = i;
            int restTeaspoons = teaspoons - firstTeaspoons;
            List<Map<Ingredient, Integer>> subDistributions = calculateDistributions(
                    restTeaspoons,
                    ingredients.subList(1, ingredients.size()));
            subDistributions.forEach(d -> d.put(first, firstTeaspoons));

            result.addAll(subDistributions);
        }

        return result;
    }

    private int calculateScore(Map<Ingredient, Integer> distribution) {
        int capacity = 0;
        int durability = 0;
        int flavor = 0;
        int texture = 0;

        for (Map.Entry<Ingredient, Integer> entry : distribution.entrySet()) {
            Ingredient ingredient = entry.getKey();
            int quantity = entry.getValue();

            capacity += quantity * ingredient.capacity;
            durability += quantity * ingredient.durability;
            flavor += quantity * ingredient.flavor;
            texture += quantity * ingredient.texture;
        }

        capacity = Math.max(0, capacity);
        durability = Math.max(0, durability);
        flavor = Math.max(0, flavor);
        texture = Math.max(0, texture);

        return capacity * durability * flavor * texture;
    }

    @Override
    public String part2(String input) {
        int teaspoons = 100;
        int wantedCalories = 500;

        List<Ingredient> ingredients = readIngredients(input);

        List<Map<Ingredient, Integer>> distributions = calculateDistributions(teaspoons, ingredients);
        List<Map<Ingredient, Integer>> distributionsWithCorrectCalories = distributions.stream()
                .filter(d -> calculateCalories(d) == wantedCalories)
                .toList();
        List<Integer> scores = distributionsWithCorrectCalories.stream().map(this::calculateScore).toList();
        int highestScore = scores.stream().max(Integer::compareTo).orElse(0);

        return String.valueOf(highestScore);
    }

    private int calculateCalories(Map<Ingredient, Integer> distribution) {
        int calories = 0;

        for (Map.Entry<Ingredient, Integer> entry : distribution.entrySet()) {
            Ingredient ingredient = entry.getKey();
            int quantity = entry.getValue();

            calories += quantity * ingredient.calories;
        }

        return calories;
    }

    private record Ingredient(String name, int capacity, int durability, int flavor, int texture, int calories) {
    }
}