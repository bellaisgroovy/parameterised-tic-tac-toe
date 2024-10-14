package data;

import business.data.Board;
import business.data.SimpleBoard;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileBoardSaveReader implements BoardSaveReader{

    @Override
    public Board getBoard(String name) {
        Scanner scanner = findBoard(name);

        return readBoard(scanner);
    }

    public void setSaveFolder(File saveFolder) {
        this.saveFolder = saveFolder;
    }

    private File saveFolder = new File("src/main/resources/saves");

    private Board readBoard(Scanner scanner) {
        String[] sizesStringArray = scanner.nextLine().split(",");
        List<Integer> sizes = stringArrayToIntegerList(sizesStringArray);

        String[] flatBoardStringArray = scanner.nextLine().split(",");
        List<Integer> flatBoard = stringArrayToIntegerList(flatBoardStringArray);

        return new SimpleBoard(sizes, flatBoard);
    }

    private List<Integer> stringArrayToIntegerList(String[] array) {
        List<Integer> items = new ArrayList<>();
        for (String item : array) items.add(Integer.parseInt(item));
        return items;
    }

    private Scanner findBoard(String name) {
        File save = new File(saveFolder.getPath() + "/" + name + ".board");

        try {
            return new Scanner(save);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
