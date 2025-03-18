package de.fatih_inan.days;

import de.fatih_inan.Day;

public class Day11 implements Day {
    @Override
    public String part1(String input) {
        String password = input;
        while (true) {
            password = getNextPassword(password);
            if (isValidPassword(password)) {
                return password;
            }
        }
    }

    private String getNextPassword(String password) {
        StringBuilder passwordBuilder = new StringBuilder(password);
        for (int i = password.length() - 1; i >= 0; i--) {
            char c = password.charAt(i);
            if (c != 'z') {
                passwordBuilder.setCharAt(i, ++c);
                break;
            } else {
                passwordBuilder.setCharAt(i, 'a');
            }
        }
        return passwordBuilder.toString();
    }

    private boolean isValidPassword(String password) {
        return hasCorrectLength(password) &&
                hasOnlyLowercaseLetters(password) &&
                hasIncreasingLetters(password) &&
                hasOnlyAllowedLetters(password) &&
                hasTwoPairsOfLetters(password);
    }

    private boolean hasCorrectLength(String password) {
        return password.length() == 8;
    }

    private boolean hasOnlyLowercaseLetters(String password) {
        for (char c : password.toCharArray()) {
            if (!Character.isLetter(c) || !Character.isLowerCase(c)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasIncreasingLetters(String password) {
        for (int i = 0; i < password.length() - 2; i++) {
            char c = password.charAt(i);
            if (password.charAt(i + 1) == c + 1 && password.charAt(i + 2) == c + 2) {
                return true;
            }
        }
        return false;
    }

    private boolean hasOnlyAllowedLetters(String password) {
        char[] forbiddenChars = new char[]{'i', 'o', 'l'};

        for (char c : forbiddenChars) {
            if (password.contains(String.valueOf(c))) {
                return false;
            }
        }

        return true;
    }

    private boolean hasTwoPairsOfLetters(String password) {
        int count = 0;
        for (int i = 0; i < password.length() - 1; i++) {
            char c = password.charAt(i);
            if (password.charAt(i + 1) == c) {
                count++;
                i++;
                if (count == 2) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String part2(String input) {
        return part1(part1(input));
    }
}
