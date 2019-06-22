package repositories;

import model.TODO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class TODORepository {
    public static String FILE_PATH = "F:\\Documents\\IdeaProjects\\ToDoList\\src\\test\\tododata\\";
    public static String FILE_POSTFIX = "todo.txt";

    public List<TODO> getTodos(String username) {
        List<TODO> items = new ArrayList<>();
        String FilePath = getToDoFileByUserName(username);
        try (Scanner scanner = new Scanner(new FileInputStream(FilePath))) {
            long itemsCount = scanner.nextLong();
            for (long itemNumber = 0; itemNumber < itemsCount; itemNumber++) {
                extractTODOFromScanner(items, scanner);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return items;
    }

    private void extractTODOFromScanner(List<TODO> items, Scanner scanner) {
        Long id = scanner.nextLong();
        scanner.nextLine();
        String comment = scanner.nextLine();
        String isDone = scanner.nextLine();
        items.add(new TODO(id, Boolean.valueOf(isDone), comment));
    }

    public void deleteTODOItemFromList(List<TODO> getTodos, long id, String username, String action) {
        Iterator<TODO> iterator = getTodos.iterator();
        while (iterator.hasNext()) {
            TODO item = iterator.next();

            if (item.getId() == id)
                iterator.remove();
        }

        updateTODOFile(username, getTodos, action);

    }

    public void updateStatusTODOItemFromList(List<TODO> getTodos, long id, String username, String action) {
        for (TODO todoItem : getTodos) {
            if (todoItem.getId() == id)
                todoItem.setDone(!todoItem.isDone());
        }
        updateTODOFile(username, getTodos, action);
    }

    public void addTODOItemToList(List<TODO> getTodos, String username, long id, String comment, boolean isdone, String action) {
        getTodos.add(new TODO(id, isdone, comment));
        updateTODOFile(username, getTodos, action);
    }

    private void updateTODOFile(String username, List<TODO> getTodos, String action) {
        String FilePath = getToDoFileByUserName(username);
        try (Scanner scanner = new Scanner(new FileInputStream(FilePath))) {
            long itemsCount = scanner.nextLong();
            if ("delete".equals(action)) {
                itemsCount--;
            } else if ("additem".equals(action)) {
                itemsCount++;
            }
            File file = new File(FilePath);
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.print(""); //clear file
                writer.write(itemsCount + System.getProperty("line.separator"));
                for (TODO todoItem : getTodos) {
                    writer.write(todoItem.getId() + System.getProperty("line.separator")
                            + todoItem.getComment() + System.getProperty("line.separator")
                            + todoItem.isDone() + System.getProperty("line.separator") + System.getProperty("line.separator"));
                }
            } catch (FileNotFoundException e) {
                System.out.println(e);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }


    }

    private static String getToDoFileByUserName(String usernme) {
        return FILE_PATH + usernme + FILE_POSTFIX;
    }
}

