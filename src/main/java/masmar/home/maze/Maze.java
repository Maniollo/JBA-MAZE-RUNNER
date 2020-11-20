package masmar.home.maze;

class Maze {
    private static final int SIZE = 10;
    private final int[][] arr;

    public Maze() {
        arr = new int[][]{
                {1,1,1,1,1,1,1,1,1,1},
                {0,0,1,0,1,0,1,0,0,1},
                {1,0,1,0,0,0,1,0,1,1},
                {1,0,0,0,1,1,1,0,0,0},
                {1,0,1,0,0,0,0,0,1,1},
                {1,0,1,0,1,1,1,0,1,1},
                {1,0,1,0,1,0,0,0,1,1},
                {1,0,1,0,1,1,1,0,1,1},
                {1,0,1,0,0,0,1,0,0,1},
                {1,1,1,1,1,1,1,1,1,1},
        };
    }

    public void print() {
        System.out.println(printAsText());
    }

    public String printAsText() {
        StringBuilder asText = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                String value = arr[i][j] == 1 ? "\u2588\u2588" : "  ";
                asText.append(value);
            }
            if (i != SIZE - 1) {
                asText.append("\n");
            }
        }
        return asText.toString();
    }
}
