package de.fatih_inan.days;

import de.fatih_inan.Day;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class Day16Test {
    Day day;

    @BeforeEach
    public void init() {
        day = new Day16();
    }

    @ParameterizedTest
    @MethodSource("provideValuesForPart1")
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
                Arguments.of("""
                                Sue 1: goldfish: 9, cars: 0, samoyeds: 9
                                Sue 2: perfumes: 1, trees: 3, goldfish: 5
                                Sue 3: pomeranians: 2, akitas: 1, trees: 5""".stripIndent(),
                        "2")
        );
    }

    public static Stream<Arguments> provideValuesForPart2() {
        return Stream.of(
                Arguments.of("", "")
        );
    }

}
