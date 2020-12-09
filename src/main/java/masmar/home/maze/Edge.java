package masmar.home.maze;

import java.util.Objects;

class Edge {
    private final Vertex firstNode;
    private final Vertex secondNode;
    private final int value;

    public Edge(Vertex firstNode, Vertex secondNode, int value) {
        this.firstNode = firstNode;
        this.secondNode = secondNode;
        this.value = value;
    }

    public Edge(Vertex firstNode, Vertex secondNode) {
        this(firstNode, secondNode, 0);
    }

    public int getValue() {
        return value;
    }

    public Vertex getFirstNode() {
        return firstNode;
    }

    public Vertex getSecondNode() {
        return secondNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return firstNode.equals(edge.firstNode) && secondNode.equals(edge.secondNode) || firstNode.equals(edge.secondNode) && secondNode.equals(edge.firstNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstNode, secondNode);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "firstNode=" + firstNode +
                ", secondNode=" + secondNode +
                ", value=" + value +
                '}';
    }
}
