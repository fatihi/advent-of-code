package de.fatih_inan.days;

import de.fatih_inan.Day;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class Day13Test {
    Day day;

    @BeforeEach
    public void init() {
        day = new Day13();
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
                        Alice would gain 54 happiness units by sitting next to Bob.
                        Alice would lose 79 happiness units by sitting next to Carol.
                        Alice would lose 2 happiness units by sitting next to David.
                        Bob would gain 83 happiness units by sitting next to Alice.
                        Bob would lose 7 happiness units by sitting next to Carol.
                        Bob would lose 63 happiness units by sitting next to David.
                        Carol would lose 62 happiness units by sitting next to Alice.
                        Carol would gain 60 happiness units by sitting next to Bob.
                        Carol would gain 55 happiness units by sitting next to David.
                        David would gain 46 happiness units by sitting next to Alice.
                        David would lose 7 happiness units by sitting next to Bob.
                        David would gain 41 happiness units by sitting next to Carol.
                        """.stripIndent(), "330")
        );
    }

    public static Stream<Arguments> provideValuesForPart2() {
        return Stream.of(
                Arguments.of("""
                        Alice would gain 54 happiness units by sitting next to Bob.
                        Alice would lose 79 happiness units by sitting next to Carol.
                        Alice would lose 2 happiness units by sitting next to David.
                        Bob would gain 83 happiness units by sitting next to Alice.
                        Bob would lose 7 happiness units by sitting next to Carol.
                        Bob would lose 63 happiness units by sitting next to David.
                        Carol would lose 62 happiness units by sitting next to Alice.
                        Carol would gain 60 happiness units by sitting next to Bob.
                        Carol would gain 55 happiness units by sitting next to David.
                        David would gain 46 happiness units by sitting next to Alice.
                        David would lose 7 happiness units by sitting next to Bob.
                        David would gain 41 happiness units by sitting next to Carol.
                        """.stripIndent(), "286")
        );
    }

}
