package de.fatih_inan.days;

import de.fatih_inan.Day;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class Day10Test {
    Day day;

    @BeforeEach
    public void init() {
        day = new Day10();
    }

    @ParameterizedTest
    @MethodSource("provideValuesForPart1")
    @Disabled
    public void part1Test(String input, String expected) {
        String result = day.part1(input);

        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("provideValuesForPart2")
    @Disabled
    public void part2Test(String input, String expected) {
        String result = day.part2(input);

        Assertions.assertEquals(expected, result);
    }

    private static Stream<Arguments> provideValuesForPart1() {
        return Stream.of(
                Arguments.of("1", "11"),
                Arguments.of("11", "21"),
                Arguments.of("21", "1211"),
                Arguments.of("1211", "111221"),
                Arguments.of("111221", "312211")
        );
    }

    public static Stream<Arguments> provideValuesForPart2() {
        return Stream.of(
                Arguments.of(")", "1"),
                Arguments.of("()())", "5")
        );
    }

}
