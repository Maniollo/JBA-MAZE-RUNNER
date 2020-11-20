package masmar.home.maze

import spock.lang.Specification


class MazeSpec extends Specification {
    def "should print maze"() {
        expect:
        new Maze().printAsText() ==
                "████████████████████\n" +
                "    ██  ██  ██    ██\n" +
                "██  ██      ██  ████\n" +
                "██      ██████      \n" +
                "██  ██          ████\n" +
                "██  ██  ██████  ████\n" +
                "██  ██  ██      ████\n" +
                "██  ██  ██████  ████\n" +
                "██  ██      ██    ██\n" +
                "████████████████████"
    }
}
