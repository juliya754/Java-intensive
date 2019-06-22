package services;

import model.TODO;
import repositories.TODORepository;
import java.util.Collection;

public class TODOService {
    private TODORepository todoRepository = new TODORepository();
    public Collection<TODO> getUsersToDoList(String username){
        return todoRepository.getTodos(username);
    }
}