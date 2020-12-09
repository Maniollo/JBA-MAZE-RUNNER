package masmar.home.maze

import spock.lang.Specification


class EdgeSpec extends Specification {
    def "should edges be undirected"() {
        when:
        def vertexOne = new Vertex("1")
        def vertexTwo = new Vertex("2")
        then:
        new Edge(vertexOne, vertexTwo) == new Edge(vertexTwo, vertexOne)
    }
}
