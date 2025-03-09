package de.fatih_inan.days;

import de.fatih_inan.Day;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class Day5Test {
    Day day;

    @BeforeEach
    public void init() {
        day = new Day5();
    }

    @ParameterizedTest
    @MethodSource("provideValuesForPart1")
    public void part1Test(String input, String expected) {
        String result = day.part1(input);

        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("provideValuesForPart2")
    public void part2Test(String input, String expected) {
        String result = day.part2(input);

        Assertions.assertEquals(expected, result);
    }

    private static Stream<Arguments> provideValuesForPart1() {
        return Stream.of(
                Arguments.of("ugknbfddgicrmopn", "1"),
                Arguments.of("aaa", "1"),
                Arguments.of("jchzalrnumimnmhp", "0"),
                Arguments.of("haegwjzuvuyypxyu", "0"),
                Arguments.of("dvszwmarrgswjxmb", "0")
        );
    }

    public static Stream<Arguments> provideValuesForPart2() {
        return Stream.of(
                Arguments.of("qjhvhtzxzqqjkmpb", "1"),
                Arguments.of("xxyxx", "1"),
                Arguments.of("uurcxstgmygtbstg", "0"),
                Arguments.of("ieodomkazucvgmuy", "0"),
                Arguments.of("asdfjklqqqq", "1"),
                Arguments.of("qqasadfjklqq", "1"),
                Arguments.of("qqadfqqjklqaq", "1")
        );
    }

}
