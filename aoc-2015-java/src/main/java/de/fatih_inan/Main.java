package de.fatih_inan;

import com.google.common.io.Resources;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClassGraph.CIRCUMVENT_ENCAPSULATION = ClassGraph.CircumventEncapsulationMethod.NARCISSUS;
        try (ScanResult scanResult =
                     new ClassGraph()
                             .acceptPackages("de.fatih_inan.days")
                             .scan()) {
            ClassInfoList classes = scanResult.getAllClasses();
            Scanner scanner = new Scanner(System.in);

            boolean exit = false;
            while (!exit) {
                System.out.print("Which day? [1-" + classes.size() + "] : ");

                try {
                    String userInput = scanner.nextLine();
                    int dayNumber = Integer.parseInt(userInput);
                    Optional<ClassInfo> dayOption = classes.stream()
                            .filter(x -> x.getName().equals("de.fatih_inan.days.Day" + dayNumber))
                            .findFirst();

                    if (dayOption.isEmpty()) {
                        System.out.println("Day not found.");
                        continue;
                    }

                    @SuppressWarnings("unchecked")
                    Class<Day> dayClass = (Class<Day>) dayOption.get().loadClass();
                    Day day = dayClass.getDeclaredConstructor().newInstance();

                    System.out.print("Which part? [1,2] : ");

                    URL url = Resources.getResource("Day" + dayNumber + ".txt");
                    String puzzleInput = Resources.toString(url, StandardCharsets.UTF_8);

                    userInput = scanner.nextLine();

                    int part = Integer.parseInt(userInput);
                    while (part < 1 || part > 2) {
                        System.out.println("Please select part 1 or 2.");
                        part = Integer.parseInt(userInput);
                    }

                    switch (part) {
                        case 1:
                            System.out.println("Result: " + day.part1(puzzleInput));
                            break;
                        case 2:
                            System.out.println("Result: " + day.part2(puzzleInput));
                            break;
                    }
                } catch (NumberFormatException e) {
                    exit = true;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}