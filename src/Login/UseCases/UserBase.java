package Login.UseCases;

import Login.Entities.AdminUser;
import Login.Entities.RegUser;
import Login.Entities.User;
import Presenters.Interfaces.IUserManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserBase implements IUserBase {
    private HashMap<String, User> users;

    /**
     * Construct the UserBase object.
     */
    public UserBase() {
        this.users = new HashMap<>();
        User superUser = new AdminUser("ADMIN", "password");
        this.users.put("ADMIN", superUser);
    }
    /**
     * Create the user system from the database.
     * @param parsedCSV List[String[]]
     * @param presenter IUserManager
     */
    public void createUserBase(List<String[]> parsedCSV, IUserManager presenter){
        String response;
        if (parsedCSV.isEmpty()){
            return;
        } else if(parsedCSV.get(0).length < 1){
            response = "Error creating User Base. Data formatted incorrectly.";
            presenter.responseCreateUserBase(response);
        }else{
            for (String[] userCSV : parsedCSV) {
                User user;
                String username = userCSV[0];
                String password = userCSV[1];
                int userId = Integer.parseInt(userCSV[2]);
                String type = userCSV[3];
                boolean banStatus = Boolean.parseBoolean(userCSV[4]);
                String accountData = userCSV[5];
                String historyData = userCSV[6];
                if (type.equals("Admin")) {
                    user = new AdminUser(username, password, userId, banStatus, accountData, historyData);
                } else if (type.equals("Regular")) {
                    user = new RegUser(username, password, userId, banStatus, accountData, historyData);
                } else {
                    // Default to regular user
                    user = new RegUser(username, password, userId, banStatus, accountData, historyData);
                }
                this.users.put(username, user);
            }
            response = "Successfully created User base.";
            presenter.responseCreateUserBase(response);
        }
    }
/**
 * create user base for .ser file
 */
//    public void createUserBase(Object object, IUserManager presenter){
//        String response = "Error occurred while creating pet base.";
//        if ((object instanceof HashMap)){
//            this.users = (HashMap<String, User>) object;
//        }
//    }
    /**
     * Get the User object from a username.
     * @param username String
     * @return User
     */
    public User getUser(String username){
        return users.get(username);
    }

    /**
     * Check if there is a User with a given username.
     * @param username String
     * @return boolean
     */
    public boolean isUser(String username){
        return users.containsKey(username);
    }

    /**
     * Add a user to the user base.
     * @param user User
     */
    public void addUser(User user){
        users.put(user.getUsername(), user);
    }

    /**
     * Remove a user from the user base.
     */
    public void removeUser(User user){
        users.remove(user.getUsername());
    }

    /**
     * Get all users in the system.
     * @return List[User]
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    /**
     * Return the hashmap user system.
     *
     * @return HashMap[String, User]
     */
    public HashMap<String, User> getSystem() {
        return users;
    }


}
