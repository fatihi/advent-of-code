package de.fatih_inan.days;

import de.fatih_inan.Day;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Day7 implements Day {
    @Override
    public String part1(String input) {
        List<Command> commands = Arrays.stream(input.split("\n"))
                .map(this::buildCommand).collect(Collectors.toList());
        HashMap<String, Integer> registers = new HashMap<>();
        return getRegisterA(commands, registers);
    }

    private static String getRegisterA(List<Command> commands, HashMap<String, Integer> registers) {
        int commandIndex = 0;
        do {
            if (commandIndex > commands.size() - 1) {
                commandIndex = 0;
            }

            Command command = commands.get(commandIndex);
            switch (command) {
                case RawValueCommand c -> {
                    registers.put(c.targetRegister, c.value);
                    commands.remove(commandIndex);
                }
                case RegisterValueCommand c -> {
                    if (registers.containsKey(c.sourceRegister)) {
                        registers.put(c.targetRegister, registers.get(c.sourceRegister));
                        commands.remove(commandIndex);
                    } else {
                        commandIndex++;
                    }
                }
                case ValueAndCommand c -> {
                    if (registers.containsKey(c.rightRegister)) {
                        registers.put(c.targetRegister,
                                (c.value & registers.get(c.rightRegister)) & 0xffff);
                        commands.remove(commandIndex);
                    } else {
                        commandIndex++;
                    }
                }
                case AndCommand c -> {
                    if (registers.containsKey(c.leftRegister) && registers.containsKey(c.rightRegister)) {
                        registers.put(c.targetRegister,
                                (registers.get(c.leftRegister) & registers.get(c.rightRegister)) & 0xffff);
                        commands.remove(commandIndex);
                    } else {
                        commandIndex++;
                    }
                }
                case OrCommand c -> {
                    if (registers.containsKey(c.leftRegister) && registers.containsKey(c.rightRegister)) {
                        registers.put(c.targetRegister,
                                (registers.get(c.leftRegister) | registers.get(c.rightRegister)) & 0xffff);
                        commands.remove(commandIndex);
                    } else {
                        commandIndex++;
                    }
                }
                case LeftShiftCommand c -> {
                    if (registers.containsKey(c.sourceRegister)) {
                        registers.put(c.targetRegister,
                                (registers.get(c.sourceRegister) << c.value) & 0xffff);
                        commands.remove(commandIndex);
                    } else {
                        commandIndex++;
                    }
                }
                case RightShiftCommand c -> {
                    if (registers.containsKey(c.sourceRegister)) {
                        registers.put(c.targetRegister,
                                (registers.get(c.sourceRegister) >> c.value) & 0xffff);
                        commands.remove(commandIndex);
                    } else {
                        commandIndex++;
                    }
                }
                case NotCommand c -> {
                    if (registers.containsKey(c.sourceRegister)) {
                        registers.put(c.targetRegister,
                                (~registers.get(c.sourceRegister)) & 0xffff);
                        commands.remove(commandIndex);
                    } else {
                        commandIndex++;
                    }
                }
            }

        } while (!commands.isEmpty());
        return String.valueOf(registers.get("a"));
    }

    @Override
    public String part2(String input) {
        int firstRunRegisterA = Integer.parseInt(part1(input));

        input = Arrays.stream(input.split("\n")).filter(s -> !s.endsWith(" b")).collect(Collectors.joining("\n"));
        List<Command> commands = Arrays.stream(input.split("\n"))
                .map(this::buildCommand).collect(Collectors.toList());

        HashMap<String, Integer> registers = new HashMap<>();
        registers.put("b", firstRunRegisterA);
        return getRegisterA(commands, registers);
    }

    private Command buildCommand(String input) {
        String[] parts = input.split(" ");

        if (parts.length == 3) {
            try {
                int value = Integer.parseInt(parts[0]);
                return new RawValueCommand(
                        parts[2],
                        value
                );
            } catch (NumberFormatException e) {
                return new RegisterValueCommand(
                        parts[2],
                        parts[0]
                );
            }
        } else if (parts.length == 4) {
            return new NotCommand(
                    parts[3],
                    parts[1]
            );
        } else {
            return switch (parts[1]) {
                case "AND" -> {
                    try {
                        int value = Integer.parseInt(parts[0]);
                        yield new ValueAndCommand(
                                parts[4],
                                value,
                                parts[2]
                        );
                    } catch (NumberFormatException e) {
                        yield new AndCommand(
                                parts[4],
                                parts[0],
                                parts[2]
                        );
                    }
                }
                case "OR" -> new OrCommand(
                        parts[4],
                        parts[0],
                        parts[2]
                );
                case "LSHIFT" -> new LeftShiftCommand(
                        parts[4],
                        parts[0],
                        Integer.parseInt(parts[2])
                );
                case "RSHIFT" -> new RightShiftCommand(
                        parts[4],
                        parts[0],
                        Integer.parseInt(parts[2])
                );
                default -> throw new IllegalArgumentException("Unknown command: " + parts[1]);
            };
        }
    }

    private sealed interface Command {
    }

    private record RawValueCommand(
            String targetRegister,
            int value
    ) implements Command {
    }

    private record RegisterValueCommand(
            String targetRegister,
            String sourceRegister
    ) implements Command {
    }

    private record AndCommand(
            String targetRegister,
            String leftRegister,
            String rightRegister
    ) implements Command {
    }

    private record ValueAndCommand(
            String targetRegister,
            int value,
            String rightRegister
    ) implements Command {
    }

    private record OrCommand(
            String targetRegister,
            String leftRegister,
            String rightRegister
    ) implements Command {
    }

    private record LeftShiftCommand(
            String targetRegister,
            String sourceRegister,
            int value
    ) implements Command {
    }

    private record RightShiftCommand(
            String targetRegister,
            String sourceRegister,
            int value
    ) implements Command {
    }

    private record NotCommand(
            String targetRegister,
            String sourceRegister
    ) implements Command {
    }
}
