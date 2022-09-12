package Login.UseCases;

import Interfaces.IManager;
import Login.Entities.AdminUser;
import Login.Entities.RegUser;
import Login.Entities.User;
import Gateway.ISystem;
import Presenters.Interfaces.IUserManager;

import java.io.IOException;
import java.util.List;

public class UserManager implements IManager {
    private IUserBase userBase;
    private IObserverManager obMan;

    public UserManager(IUserBase userBase, IObserverManager obMan) {
        this.userBase = userBase;
        this.obMan = obMan;
    }

    public boolean createRegUser(String username, String password, IUserManager presenter){
        String response;
        if(userBase.isUser(username)){
            response = "Username is taken";
        }else{
            response = "User successfully created";
        }
        presenter.responseCreateUser(response);
        User user = new RegUser(username, password);
        userBase.addUser(user);
        obMan.updateUsers();
        return response.equals("User successfully created");
    }
    public boolean createAdminUser(String username, String password, IUserManager presenter, String currentUser){
        String response;
        if(!userBase.getUser(currentUser).getAdminStatus()){
            response = "User does not have permission to create Admin users.";
        } else if(userBase.isUser(username)){
            response = "Username is taken";
        }else{
            response = "User successfully created";
        }
        presenter.responseCreateUser(response);
        User user = new AdminUser(username, password);
        userBase.addUser(user);
        obMan.updateUsers();
        return response.equals("User successfully created");
    }

    public boolean deleteUser(ISystem system, IUserManager presenter, String username, String currentUser){
        String response;
        if (!userBase.getUser(currentUser).getAdminStatus()) {
            response = "Operation failed. User not authorized to delete.";
        } else if (!userBase.isUser(username)) {
            response = "Operation failed. User does not exist.";
        } else if (userBase.getUser(username).getAdminStatus()) {
            response = "Operation failed. Cannot delete admin.";
        } else {
            userBase.removeUser(userBase.getUser(username));
            system.removeRecord(username);
            obMan.updateUsers();
            response = "User has been deleted.";
        }
        presenter.responseDeleteUser(response);
        return response.equals("User has been deleted.");
    }

    public boolean addUser(User user, IUserManager presenter, ISystem system) throws IOException {
        String response;
        boolean added = false;
        if(userBase.isUser(user.getUsername())){
            response = "Operation failed. Username already exists";
        }else{
            response = "User successfully added to system.";
            userBase.addUser(user);
            system.addRecord(user + "\n");
            added = true;
        }
        presenter.responseAddUser(response);
        obMan.updateUsers();
        return added;
    }

    public String makeAdmin(String username,IUserManager presenter, User currentUser){
        String response;
        if(!currentUser.getAdminStatus()){
            response = "User does not have permission to grant admin status.";
        } else if(!userBase.isUser(username)){
            response = "User doesn't exists.";
        }else if(userBase.getUser(username).getAdminStatus()){
            response = "User is already an admin.";
        }else {
            userBase.getUser(username).setAdminStatus(true);
            response = "Successfully promoted to Admin.";
        }
        presenter.responseMakeAdmin(response);
        return response;
    }

    public boolean banUser(String username, IUserManager presenter, String currentUser){
        String response;
        if (!userBase.getUser(currentUser).getAdminStatus()){
            response = "Operation failed. User not authorized to ban.";
        }else if(!userBase.isUser(username)){
            response = "User Doesn't Exists.";
        }else if(userBase.getUser(username).getClass() == AdminUser.class){
            response = "Operation failed. Cannot ban admin.";
        }else if(userBase.getUser(username).getBanStatus()){
            response = "Operation failed. User is already banned.";
        }else{
            userBase.getUser(username).setBanStatus(true);
            obMan.updateUsers();
            response = "User successfully banned.";
        }
        presenter.responseBanUser(response);
        return response.equals("User successfully banned.");
    }

    public boolean verifyUser(String username, String password, IUserManager presenter){
        String response;

        // This if statement prevents the NullPointerException error
        if (!userBase.isUser(username)){
            response = "Login failed. User not found.";
        } else if (!userBase.getUser(username).getPassword().equals(password)) {
            response = "Login failed. Invalid username or password";
        }else if (userBase.getUser(username).getBanStatus()) {
            response = "Login failed. User is banned.";
        }else{
            response = "Login successful.";
        }
        presenter.responseLogin(response);
        return response.equals("Login successful.");
    }

    /**
     * Prepare the data for reading from a database.
     * @return String
     */
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        List<User> userList = userBase.getAllUsers();
        for(User user : userList) {
            result.append(user.toString()).append("\n");
        }
        return result.toString();
    }

    @Override
    public Object getData() {
        return this.userBase.getSystem();
    }
}
