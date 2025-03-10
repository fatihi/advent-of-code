package de.fatih_inan.days;

import de.fatih_inan.Day;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class Day6Test {
    Day day;

    @BeforeEach
    public void init() {
        day = new Day6();
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
                Arguments.of("turn on 0,0 through 999,999", "1000000"),
                Arguments.of("toggle 0,0 through 999,0", "1000"),
                Arguments.of("turn on 0,0 through 999,0\ntoggle 500,0 through 999,0", "500"),
                Arguments.of("turn off 499,499 through 500,500", "0"),
                Arguments.of("turn on 0,0 through 999,999\nturn off 499,499 through 500,500", "999996")
        );
    }

    public static Stream<Arguments> provideValuesForPart2() {
        return Stream.of(
                Arguments.of("turn on 0,0 through 0,0", "1"),
                Arguments.of("toggle 0,0 through 999,999", "2000000")
        );
    }

}
