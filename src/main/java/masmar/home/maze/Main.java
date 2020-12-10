package masmar.home.maze;

import java.util.Objects;
import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        Maze maze = null;
        FileManager fileManager = new FileManager();

        while (true) {
            menu(maze);
            int option = SCANNER.nextInt();

            switch (option) {
                case 0:
                    exit();
                    return;
                case 1:
                    maze = createNewMaze();
                    break;
                case 2:
                    maze = loadMze(fileManager);
                    break;
                case 3:
                    saveMaze(maze, fileManager);
                    break;
                case 4:
                    displayMaze(maze);
                    break;
                default:
                    System.out.println("Incorrect option. Please try again");
            }

        }
    }

    private static void exit() {
        System.out.println("Bye!");
    }

    private static void displayMaze(Maze maze) {
        maze.print();
        System.out.println();
    }

    private static void saveMaze(Maze maze, FileManager fileManager) {
        String savePath = SCANNER.next();
        fileManager.saveMaze(savePath, maze);
    }

    private static Maze loadMze(FileManager fileManager) {
        Maze maze;
        String loadPath = SCANNER.next();
        maze = fileManager.loadMaze(loadPath);
        return maze;
    }

    private static Maze createNewMaze() {
        Maze maze;
        System.out.println("Enter the size of a new maze");
        int m = SCANNER.nextInt();
        maze = new Maze(m, m);
        displayMaze(maze);
        return maze;
    }

    private static void menu(Maze maze) {
        System.out.println("=== Menu ===");
        System.out.println("1. Generate a new maze");
        System.out.println("2. Load a maze");
        if (Objects.nonNull(maze)) {
            System.out.println("3. Save the maze");
            System.out.println("4. Display the maze");
        }
        System.out.println("0. Exit");
    }
}
