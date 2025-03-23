package de.fatih_inan.days;

import de.fatih_inan.Day;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class Day15Test {
    Day day;

    @BeforeEach
    public void init() {
        day = new Day15();
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
                        Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8
                        Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3
                        """.stripIndent(), "62842880")
        );
    }

    public static Stream<Arguments> provideValuesForPart2() {
        return Stream.of(
                Arguments.of("""
                        Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8
                        Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3
                        """.stripIndent(), "57600000")
        );
    }

}
