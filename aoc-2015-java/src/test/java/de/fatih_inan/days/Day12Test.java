package de.fatih_inan.days;

import de.fatih_inan.Day;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class Day12Test {
    Day day;

    @BeforeEach
    public void init() {
        day = new Day12();
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
                Arguments.of("{}", "0"),
                Arguments.of("""
                        {"a": 1}
                        """.stripIndent(), "1"),
                Arguments.of("""
                        {"a": 2}
                        """.stripIndent(), "2"),
                Arguments.of("""
                        {"a": 2, "b": 3}
                        """.stripIndent(), "5"),
                Arguments.of("""
                        {"a": 2, "b": 13}
                        """.stripIndent(), "15"),
                Arguments.of("""
                        {"a": 2, "b": 13, "c": -4}
                        """.stripIndent(), "11"),
                Arguments.of("""
                        {"a": -2, "b": 13, "c": -4}
                        """.stripIndent(), "7")
        );
    }

    public static Stream<Arguments> provideValuesForPart2() {
        return Stream.of(
                Arguments.of("{}", "0"),
                Arguments.of("""
                        {"a": 1}
                        """.stripIndent(), "1"),
                Arguments.of("""
                        {"a": 2}
                        """.stripIndent(), "2"),
                Arguments.of("""
                        {"a": 2, "b": 3}
                        """.stripIndent(), "5"),
                Arguments.of("""
                        {"a": 2, "b": 13}
                        """.stripIndent(), "15"),
                Arguments.of("""
                        {"a": 2, "b": 13, "c": -4}
                        """.stripIndent(), "11"),
                Arguments.of("""
                        {"a": -2, "b": 13, "c": -4}
                        """.stripIndent(), "7"),
                Arguments.of("""
                        {"a": -2, "b": "red", "c": -4}
                        """.stripIndent(), "0")
        );
    }

}
