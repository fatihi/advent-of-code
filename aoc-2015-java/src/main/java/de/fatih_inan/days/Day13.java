package de.fatih_inan.days;

import de.fatih_inan.Day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day13 implements Day {
    @Override
    public String part1(String input) {
        List<Guest> guests = readGuests(input);

        List<List<Guest>> permutations = getPermutations(guests);
        int maximumHappiness = permutations.stream()
                .map(this::calculateHappiness)
                .max(Integer::compare)
                .orElse(0);

        return String.valueOf(maximumHappiness);
    }

    private List<Guest> readGuests(String input) {
        String[] lines = input.split("\n");

        List<Guest> guests = new ArrayList<>();

        String currentGuest = input.split(" ")[0];
        Map<String, Integer> currentHappinessMap = new HashMap<>();

        for (String line : lines) {
            String[] parts = line.split(" ");
            String name = parts[0];
            if (!currentGuest.equals(name)) {
                Guest guest = new Guest(currentGuest, currentHappinessMap);
                guests.add(guest);
                currentGuest = name;
                currentHappinessMap = new HashMap<>();
            }

            boolean positive = parts[2].equals("gain");
            int amount = Integer.parseInt(parts[3]);
            amount *= positive ? 1 : -1;
            String otherGuest = parts[10].substring(0, parts[10].length() - 1);

            currentHappinessMap.put(otherGuest, amount);
        }

        Guest guest = new Guest(currentGuest, currentHappinessMap);
        guests.add(guest);

        return guests;
    }

    private List<List<Guest>> getPermutations(List<Guest> guests) {
        if (guests.isEmpty()) {
            List<List<Guest>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }

        Guest first = guests.removeFirst();
        List<List<Guest>> result = new ArrayList<>();
        List<List<Guest>> permutations = getPermutations(guests);
        for (List<Guest> smallerPermutation : permutations) {
            for (int i = 0; i <= smallerPermutation.size(); i++) {
                List<Guest> temp = new ArrayList<>(smallerPermutation);
                temp.add(i, first);
                result.add(temp);
            }
        }

        return result;
    }

    private int calculateHappiness(List<Guest> guests) {
        int happiness = 0;

        for (int i = 0; i < guests.size(); i++) {
            Guest guest = guests.get(i);
            Guest left = i == 0 ? guests.getLast() : guests.get(i - 1);
            Guest right = i == guests.size() - 1 ? guests.getFirst() : guests.get(i + 1);

            happiness += guest.happinessMap.get(left.name) + guest.happinessMap.get(right.name);
        }

        return happiness;
    }

    @Override
    public String part2(String input) {
        List<Guest> guests = readGuests(input);
        guests.forEach(g -> g.happinessMap.put("ME", 0));

        List<String> names = guests.stream().map(Guest::name).toList();
        HashMap<String, Integer> myHappinessMap = new HashMap<>();
        for (String name : names) {
            myHappinessMap.put(name, 0);
        }
        guests.add(new Guest("ME", myHappinessMap));

        List<List<Guest>> permutations = getPermutations(guests);
        int maximumHappiness = permutations.stream()
                .map(this::calculateHappiness)
                .max(Integer::compare)
                .orElse(0);

        return String.valueOf(maximumHappiness);
    }

    private record Guest(String name, Map<String, Integer> happinessMap) {
    }
}