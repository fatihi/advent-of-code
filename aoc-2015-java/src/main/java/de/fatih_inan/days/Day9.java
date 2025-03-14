package de.fatih_inan.days;

import de.fatih_inan.Day;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Day9 implements Day {
    @Override
    public String part1(String input) {
        Graph graph = buildGraph(input);

        List<List<Node>> possibleRoutes = buildRoutes(graph.nodes);

        int shortestDistance = getSortedDistances(graph, possibleRoutes)
                .getFirst();

        return String.valueOf(shortestDistance);
    }

    private Graph buildGraph(String input) {
        String[] lines = input.split("\n");

        Set<Vertex> vertices = new HashSet<>();
        Set<Node> nodes = new HashSet<>();

        for (String line : lines) {
            String[] parts = line.split(" ");
            Node left = new Node(parts[0]);
            Node right = new Node(parts[2]);
            nodes.add(left);
            nodes.add(right);
            vertices.add(new Vertex(left, right, Integer.parseInt(parts[4])));
            vertices.add(new Vertex(right, left, Integer.parseInt(parts[4])));
        }

        return new Graph(nodes.stream().toList(), vertices.stream().toList());
    }

    private List<List<Node>> buildRoutes(List<Node> nodes) {
        if (nodes.size() == 1) {
            List<List<Node>> result = new LinkedList<>();
            List<Node> innerNodes = new LinkedList<>(nodes);
            result.add(innerNodes);
            return result;
        }

        List<List<Node>> routes = new LinkedList<>();

        for (Node node : nodes) {
            List<Node> filteredNodes = nodes.stream()
                    .filter(n -> !n.equals(node))
                    .toList();
            List<List<Node>> subRoutes = buildRoutes(filteredNodes);
            subRoutes.forEach(route -> route.addFirst(node));
            routes.addAll(subRoutes);
        }

        return routes;
    }

    private List<Integer> getSortedDistances(Graph graph, List<List<Node>> routes) {
        return routes.stream()
                .map(route -> {
                    int distance = 0;
                    for (int i = 0; i < route.size() - 1; i++) {
                        Node node1 = route.get(i);
                        Node node2 = route.get(i + 1);
                        distance += graph.vertices.stream()
                                .filter(v -> v.left.equals(node1) && v.right.equals(node2))
                                .findFirst()
                                .map(v -> v.distance)
                                .orElse(Integer.MAX_VALUE);
                    }
                    return distance;
                })
                .sorted()
                .toList();
    }

    @Override
    public String part2(String input) {
        Graph graph = buildGraph(input);

        List<List<Node>> possibleRoutes = buildRoutes(graph.nodes);

        int shortestDistance = getSortedDistances(graph, possibleRoutes)
                .getLast();

        return String.valueOf(shortestDistance);
    }

    private record Graph(List<Node> nodes, List<Vertex> vertices) {
    }

    private record Node(String name) {
    }

    private record Vertex(Node left, Node right, int distance) {
    }
}
