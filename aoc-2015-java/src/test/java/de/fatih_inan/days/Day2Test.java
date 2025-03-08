package de.fatih_inan.days;

import de.fatih_inan.Day;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class Day2Test {
    Day day;

    @BeforeEach
    public void init() {
        day = new Day2();
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
                Arguments.of("1x1x1", "7"),
                Arguments.of("1x1x2", "11"),
                Arguments.of("2x2x2", "28"),
                Arguments.of("2x3x4", "58"),
                Arguments.of("1x1x10", "43")
        );
    }

    public static Stream<Arguments> provideValuesForPart2() {
        return Stream.of(
                Arguments.of("2x3x4", "34"),
                Arguments.of("1x1x10", "14")
        );
    }

}
