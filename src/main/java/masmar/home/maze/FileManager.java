package masmar.home.maze;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

class FileManager {

    public void saveMaze(String path, Maze maze) {
        try (OutputStream outputStream = new FileOutputStream(path, false)) {
            outputStream.write(maze.asByteArray());
        } catch (IOException e) {
            System.out.println("The Maze was not saved due to some problems.");
        }
    }

    public Maze loadMaze(String loadPath) {
        try {
            return new Maze(Files.readAllLines(Paths.get(loadPath)));
        } catch (IOException e) {
            System.out.println("The file ... does not exist");
        } catch (IllegalArgumentException e) {
            System.out.println("Cannot load the maze. It has an invalid format");
        }
        return null;
    }
}
