package masmar.home.maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Maze {
    private static final Random RANDOM = new Random();
    private int[][] arr;

    public Maze(int height, int width) {
        initMazeMatrix(height, width);
        int newHeight = height % 2 == 0 ? height - 1 : height;
        int newWidth = width % 2 == 0 ? width - 1 : width;
        createMaze(prepareGrid(newHeight / 2, newWidth / 2));
    }

    public Maze(List<String> asText) {
        fromText(asText);
    }

    //    Only for testing
    public Maze(int height, int width, Graph graph) {
        initMazeMatrix(height, width);
        createMaze(graph);
    }
    private void fromText(List<String> asText) {
        int height = asText.size();
        int width = asText.get(0).length() / 2;
        arr = new int[height][width];
        for (int i = 0; i < height; i++) {
            String line = asText.get(i);
            for (int j = 0; j < line.length() / 2; j++) {
                char aChar = line.charAt(2 * j);
                if (aChar == '\u2588' || aChar == '?') {
                    arr[i][j] = 1;
                } else if (aChar == ' ') {
                    arr[i][j] = 0;
                } else {
                    throw new IllegalArgumentException("Cannot load the maze. It has an invalid format");
                }
            }
        }
    }

    private void initMazeMatrix(int height, int width) {
        boolean isHeightOdd = height % 2 != 0;
        boolean isWidthOdd = width % 2 != 0;
        arr = new int[height][width];
        if (isHeightOdd && isWidthOdd) {
            fillCore(height, width);
            arr[height - 2][width - 1] = 0;
        } else if (isHeightOdd) {
            fillCore(height, width - 1);
            for (int i = 0; i < height; i++) {
                arr[i][width - 1] = 1;
            }
            arr[height - 2][width - 1] = 0;
            arr[height - 2][width - 2] = 0;
        } else if (isWidthOdd) {
            fillCore(height - 1, width);
            for (int i = 0; i < width; i++) {
                arr[height - 1][i] = 1;
            }
            arr[height - 3][width - 1] = 0;
        } else {
            fillCore(height - 1, width - 1);
            for (int i = 0; i < width; i++) {
                arr[height - 1][i] = 1;
            }
            for (int i = 0; i < height; i++) {
                arr[i][width - 1] = 1;
            }
            arr[height - 3][width - 1] = 0;
            arr[height - 3][width - 2] = 0;
        }
        arr[1][0] = 0;
    }

    private void fillCore(int height, int width) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i % 2 != 0 && j % 2 != 0) {
                    arr[i][j] = 0;
                } else {
                    arr[i][j] = 1;
                }
            }
        }
    }

    private void createMaze(Graph graph) {
        List<Edge> edges = graph.getMST();
        applyEdgesOnMatrix(edges);
    }

    private void applyEdgesOnMatrix(List<Edge> edges) {
        int length = arr[0].length % 2 == 0 ? arr[0].length - 1 : arr[0].length;
        int width = length / 2;
        edges.forEach(
                edge -> {
                    int first = Integer.parseInt(edge.getFirstNode().getLabel());
                    int second = Integer.parseInt(edge.getSecondNode().getLabel());
                    int wallx = (calculateCoordinates(first / width) + (calculateCoordinates(second / width))) / 2;
                    int wally = ((calculateCoordinates(first % width)) + (calculateCoordinates(second % width))) / 2;

                    arr[wallx][wally] = 0;
                }
        );
    }

    private int calculateCoordinates(int x) {
        return (x + 1) * 2 - 1;
    }

    private Graph prepareGrid(int height, int width) {
        List<Edge> edges = new ArrayList<>();

        Map<Integer, Vertex> vertexesMap = IntStream.range(0, height * width)
                .mapToObj(id -> new Vertex(Integer.toString(id)))
                .collect(Collectors.toMap(vertex -> Integer.parseInt(vertex.getLabel()), Function.identity()));

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int currentVertexIdx = i * width + j;
                if (j + 1 < width) {    // edge to the right vertex
                    int value = RANDOM.nextInt(Math.max(height * 2, width * 2));
                    edges.add(new Edge(vertexesMap.get(currentVertexIdx), vertexesMap.get(currentVertexIdx + 1), value));
                }
                if (i + 1 < height) {   // edge to the bottom vertex
                    int value = RANDOM.nextInt(Math.max(height * 2, width * 2));
                    edges.add(new Edge(vertexesMap.get(currentVertexIdx), vertexesMap.get((i + 1) * width + j), value));
                }
            }
        }

        return new Graph(new ArrayList<>(vertexesMap.values()), edges);
    }

    public void print() {
        System.out.println(printAsText());
    }

    public String printAsText() {
        StringBuilder asText = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                String value = arr[i][j] == 1 ? "\u2588\u2588" : "  ";
                asText.append(value);
            }
            if (i != arr.length - 1) {
                asText.append("\n");
            }
        }
        return asText.toString();
    }

    public byte[] asByteArray() {
        return this.printAsText().getBytes();
    }
}
