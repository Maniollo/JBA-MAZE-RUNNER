package masmar.home.maze;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Graph {
    private List<Vertex> vertices;
    private List<Edge> edges;

    public Graph(List<Vertex> vertices, List<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    public Graph() {
    }


    public List<Edge> getMST() {
        Vertex current = vertices.get(0);

        Set<Vertex> visitedVertices = new HashSet<>(Set.of(current));
        Set<Vertex> awaitingVertices = new HashSet<>();
        List<Edge> includedEdges = new ArrayList<>();
        List<Edge> waitingEdges = new ArrayList<>();

        for (int i = 0; i < vertices.size() - 1; i++) {
            Optional<Vertex> possibleNext = findAdjacentNodeWithMinValue(current, awaitingVertices, visitedVertices, includedEdges, waitingEdges);
            if (possibleNext.isPresent()) {
                current = possibleNext.get();
                awaitingVertices.remove(current);
                visitedVertices.add(current);
            }
        }

        return includedEdges;
    }

    private Optional<Vertex> findAdjacentNodeWithMinValue(Vertex current, Set<Vertex> awaitingVertices, Set<Vertex> visitedVertices, List<Edge> includedEdges, List<Edge> waitingEdges) {
        awaitingVertices.addAll(findAdjacentNodes(current, visitedVertices));
        Set<Edge> potentialAwaitingEdges = awaitingVertices.stream()
                .map(vertex -> new Edge(vertex, current))
                .collect(Collectors.toSet());

        waitingEdges.addAll(potentialAwaitingEdges);

        List<Edge> potentialEdges = this.edges.stream()
                .filter(waitingEdges::contains)
                .sorted(Comparator.comparingInt(Edge::getValue))
                .collect(Collectors.toList());

        potentialEdges.removeIf(edge -> visitedVertices.contains(edge.getFirstNode()) && visitedVertices.contains(edge.getSecondNode()));

        Edge currentEdge = potentialEdges.get(0);
        Vertex maybeAdjacent = visitedVertices.contains(currentEdge.getFirstNode())
                ? currentEdge.getSecondNode()
                : currentEdge.getFirstNode();

        if (visitedVertices.contains(maybeAdjacent)) {
            return Optional.empty();
        }
        includedEdges.add(currentEdge);
        waitingEdges.remove(currentEdge);
        return Optional.of(maybeAdjacent);
    }

    private List<Vertex> findAdjacentNodes(Vertex current, Set<Vertex> alreadyVisited) {
        Stream<Vertex> asFirstNode = edges.stream()
                .filter(edge -> edge.getFirstNode().equals(current))
                .map(Edge::getSecondNode)
                .filter(vertex -> !alreadyVisited.contains(vertex));
        Stream<Vertex> asSecondNode = edges.stream()
                .filter(edge -> edge.getSecondNode().equals(current))
                .map(Edge::getFirstNode)
                .filter(vertex -> !alreadyVisited.contains(vertex));

        return Stream.concat(asFirstNode, asSecondNode).collect(Collectors.toList());
    }
}
