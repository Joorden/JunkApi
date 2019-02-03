package nl.hsleiden.service;

import java.util.Collection;
import javax.inject.Inject;
import javax.inject.Singleton;
import nl.hsleiden.model.User;
import nl.hsleiden.persistence.UserDAO;

@Singleton
public class UserService
{
    private final UserDAO dao;
    
    @Inject
    public UserService(UserDAO dao)
    {
        this.dao = dao;
    }
    
    public User get(String email)
    {
        System.out.println(dao.getUser(email));
        return dao.getUser(email);
    }
    public void add(User user)
    {
        dao.addUser(user);
    }
    public void delete(String email) {
        dao.deleteUser(email);
    }
    public void update(User user, User authenticator) {dao.updateUser(user, authenticator);}
}

