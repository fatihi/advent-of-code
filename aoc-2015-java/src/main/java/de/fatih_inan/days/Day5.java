package de.fatih_inan.days;

import de.fatih_inan.Day;

import java.util.Arrays;
import java.util.List;

public class Day5 implements Day {
    private final List<Character> vowels = List.of(
            'a', 'e', 'i', 'o', 'u'
    );

    private final List<String> forbiddenStrings = List.of(
            "ab", "cd", "pq", "xy"
    );

    @Override
    public String part1(String input) {
        return String.valueOf(
                Arrays.stream(input.split("\n"))
                        .filter(this::isNiceString)
                        .toList()
                        .size()
        );
    }

    private boolean isNiceString(String s) {
        return hasAtLeastThreeVowels(s) &&
                hasAtLeastOneRepeatingLetter(s) &&
                hasNoForbiddenString(s);
    }

    private boolean hasAtLeastThreeVowels(String s) {
        int counter = 0;
        for (char c : s.toCharArray()) {
            if (vowels.contains(c)) {
                counter++;
            }

            if (counter >= 3) {
                return true;
            }
        }
        return false;
    }

    private boolean hasAtLeastOneRepeatingLetter(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasNoForbiddenString(String s) {
        for (String forbiddenString : forbiddenStrings) {
            if (s.contains(forbiddenString)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String part2(String input) {
        return String.valueOf(
                Arrays.stream(input.split("\n"))
                        .filter(this::isNewNiceString)
                        .toList()
                        .size()
        );
    }

    private boolean isNewNiceString(String s) {
        return hasRepeatingLetterPair(s) &&
                hasRepeatingLetterWithLetterInBetween(s);
    }

    private boolean hasRepeatingLetterPair(String s) {
        for (int i = 0; i < s.length() - 3; i++) {
            for (int j = i + 2; j < s.length() - 1; j++) {
                if (s.substring(i, i + 2).equals(s.substring(j, j + 2))) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasRepeatingLetterWithLetterInBetween(String s) {
        for (int i = 0; i < s.length() - 2; i++) {
            if (s.charAt(i) == s.charAt(i + 2)) {
                return true;
            }
        }
        return false;
    }
}
