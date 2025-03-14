package de.fatih_inan.days;

import de.fatih_inan.Day;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class Day8Test {
    Day day;

    @BeforeEach
    public void init() {
        day = new Day8();
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
                Arguments.of("\"\"", "2"),
                Arguments.of("\"aaa\\\"aaa\"", "3"),
                Arguments.of("\"bbb\\\"bbb\"", "3"),
                Arguments.of("\"b\\\"\\\"b\"", "4"),
                Arguments.of("\"\\x27\"", "5"),
                Arguments.of("""
                        ""
                        "aaa\\"aaa"
                        "\\x27\"""".stripIndent(), "10")
        );
    }

    public static Stream<Arguments> provideValuesForPart2() {
        return Stream.of(
                Arguments.of("\"\"", "4"),
                Arguments.of("""
                        ""
                        "abc"
                        "aaa\\"aaa"
                        "\\x27\"""".stripIndent(), "19")
        );
    }
}
