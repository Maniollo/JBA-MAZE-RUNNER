package masmar.home.maze;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        System.out.println("Please, enter the size of a maze");
        Scanner scanner = new Scanner(System.in);
        int height = scanner.nextInt();
        int width = scanner.nextInt();
        Maze maze = new Maze(height, width);
        maze.print();
    }
}
