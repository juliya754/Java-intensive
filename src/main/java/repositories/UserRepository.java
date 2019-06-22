package repositories;

import model.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserRepository {
    public static String FILE_PATH = "F:\\Documents\\IdeaProjects\\ToDoList\\src\\test\\tododata\\credentials.txt";
    public List<User> getUser()
    {
        List<User>items = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(FILE_PATH)))
        {
            long itemsCount = scanner.nextLong();
            for (long itemNumber = 0; itemNumber < itemsCount; itemNumber++)
            {
                extractUserFromScanner(items, scanner);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e);
        }
        return items;
    }

    private void extractUserFromScanner(List<User> items, Scanner scanner) {
            Long id = scanner.nextLong();
            scanner.nextLine();
            String login = scanner.nextLine();
            String password = scanner.nextLine();
            items.add(new User(id, login, password));
    }
}
