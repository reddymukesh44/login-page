package Login.UseCases;

import Login.Entities.User;
import Presenters.Interfaces.IUserManager;

import java.util.HashMap;
import java.util.List;

/**
 * Defines the user base interface and all methods it should implement.  Part of the dependency
 * injection code design.
 */
public interface IUserBase {
    /**
     * Create the user base for the login system.
     * @param parsedCSV List[String[]]
     * @param presenter IUserManager
     */
    void createUserBase(List<String[]> parsedCSV, IUserManager presenter);

    /**
     * Get the user from the user base.
     * @param username String
     * @return User
     */
    User getUser(String username);

    /**
     * Checks if the user is in the user base.
     * @param username String
     * @return boolean
     */
    boolean isUser(String username);

    /**
     * Add a user to the system.
     * @param user User
     */
    void addUser(User user);

    /**
     * Remove a user from the system.
     * @param user User
     */
    void removeUser(User user);

    /**
     * Get all users in the system.
     * @return List[User]
     */
    List<User> getAllUsers();

    HashMap<String, User> getSystem();
}
