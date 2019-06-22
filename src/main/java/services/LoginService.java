package services;

import model.User;
import repositories.UserRepository;

import java.util.List;

public class LoginService {
    private UserRepository userRepository = new UserRepository();

    public boolean doLogin(String login, String password)
    {
        List<User> users = userRepository.getUser();
        User user = findUser(login, users);
        return user != null && user.getPassword().equals(password);
    }

    private User findUser(String login, List<User> users)
    {
        for (User userFromList: users)
        {
            if(userFromList.getLogin().equals(login))
                return  userFromList;
        }
        return null;
    }

}

