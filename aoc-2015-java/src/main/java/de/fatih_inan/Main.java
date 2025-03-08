package de.fatih_inan;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;

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
                System.out.print("Which day do you want to run? [1-" + classes.size() + "] :");

                try {
                    String input = scanner.nextLine();
                    int dayNumber = Integer.parseInt(input);
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

                    System.out.print("Which part do you want to run? [1,2] :");
                    input = scanner.nextLine();

                    int part = Integer.parseInt(input);
                    while (part < 1 || part > 2) {
                        System.out.println("Please select part 1 or 2.");
                        part = Integer.parseInt(input);
                    }

                    switch (part) {
                        case 1:
                            System.out.println("Result: " + day.part1("part1"));
                            break;
                        case 2:
                            System.out.println("Result: " + day.part2("part2"));
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