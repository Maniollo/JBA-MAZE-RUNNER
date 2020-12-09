package masmar.home.maze

import spock.lang.Specification


class GraphSpec extends Specification {
    def "should return MST"() {
        when:
        def node1 = new Vertex("0")
        def node2 = new Vertex("1")
        def node3 = new Vertex("2")
        def node4 = new Vertex("3")
        def node5 = new Vertex("4")
        def node6 = new Vertex("5")
        def node7 = new Vertex("6")
        def node8 = new Vertex("7")
        def node9 = new Vertex("8")
        def node10 = new Vertex("9")
        def node11 = new Vertex("10")
        def node12 = new Vertex("11")
        def vertices = [node1, node2, node3, node4, node5, node6, node7, node8, node9, node10, node11, node12]
        def edges = [
                new Edge(node1, node2, 2), new Edge(node2, node3, 2), new Edge(node3, node4, 1),
                new Edge(node1, node5, 1), new Edge(node2, node6, 4), new Edge(node3, node7, 3), new Edge(node4, node8, 2),
                new Edge(node5, node6, 5), new Edge(node6, node7, 4), new Edge(node7, node8, 8),
                new Edge(node5, node9, 8), new Edge(node6, node10, 2), new Edge(node7, node11, 7), new Edge(node8, node12, 5),
                new Edge(node9, node10, 7), new Edge(node10, node11, 3), new Edge(node11, node12, 6)
        ]
        def graph = new Graph(vertices, edges)

        then:
        graph.getMST() containsAll([new Edge(node1, node5, 1), new Edge(node1, node2, 2), new Edge(node2, node3, 2), new Edge(node3, node4, 1),
                                    new Edge(node2, node6, 4), new Edge(node3, node7, 3), new Edge(node4, node8, 2), new Edge(node8, node12, 5),
                                    new Edge(node6, node10, 2), new Edge(node9, node10, 7), new Edge(node10, node11, 3)]
        )
    }
}
