package de.fatih_inan;

import com.google.common.io.Resources;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        ClassGraph.CIRCUMVENT_ENCAPSULATION = ClassGraph.CircumventEncapsulationMethod.NARCISSUS;
        try (ScanResult scanResult =
                     new ClassGraph()
                             .acceptPackages("de.fatih_inan.days")
                             .scan()) {
            ClassInfoList classes = scanResult.getAllClasses();
            try {
                // for (int i = 1; i <= classes.size(); i++) {
                for (int i = classes.size(); i <= classes.size(); i++) {
                    int dayNumber = i;
                    Optional<ClassInfo> dayOption = classes.stream()
                            .filter(x -> x.getName().equals("de.fatih_inan.days.Day" + dayNumber))
                            .findFirst();

                    @SuppressWarnings("unchecked")
                    Class<Day> dayClass = (Class<Day>) dayOption.get().loadClass();
                    Day day = dayClass.getDeclaredConstructor().newInstance();

                    URL url = Resources.getResource("Day" + dayNumber + ".txt");
                    String puzzleInput = Resources.toString(url, StandardCharsets.UTF_8);

                    System.out.println("Day " + dayNumber + " part 1: " + day.part1(puzzleInput));
                    System.out.println("Day " + dayNumber + " part 2: " + day.part2(puzzleInput));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}