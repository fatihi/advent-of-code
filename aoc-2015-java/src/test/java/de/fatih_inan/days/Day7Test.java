package de.fatih_inan.days;

import de.fatih_inan.Day;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class Day7Test {
    Day day;

    @BeforeEach
    public void init() {
        day = new Day7();
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
                Arguments.of("""
                        123 -> a""".stripIndent(), "123"),
                Arguments.of("""
                        123 -> x
                        456 -> y
                        x AND y -> a""".stripIndent(), "72"),
                Arguments.of("""
                        123 -> x
                        456 -> y
                        x OR y -> a""".stripIndent(), "507"),
                Arguments.of("""
                        123 -> x
                        x LSHIFT 2 -> a""".stripIndent(), "492"),
                Arguments.of("""
                        456 -> x
                        x RSHIFT 2 -> a""".stripIndent(), "114"),
                Arguments.of("""
                        123 -> x
                        NOT x -> a""".stripIndent(), "65412"),
                Arguments.of("""
                        123 -> x
                        x -> a""".stripIndent(), "123"),
                Arguments.of("""
                        x -> a
                        123 -> x""".stripIndent(), "123"),
                Arguments.of("""
                        123 -> b
                        b LSHIFT 2 -> x
                        x -> a""".stripIndent(), "492")
        );
    }

    public static Stream<Arguments> provideValuesForPart2() {
        return Stream.of(
                Arguments.of("""
                        123 -> b
                        b LSHIFT 2 -> x
                        x -> a""".stripIndent(), "1968")
        );
    }

}
