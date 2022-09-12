package Login.UseCases;

import Login.Entities.User;
import Presenters.Interfaces.IAccountManager;

/**
 * AccountManager denotes all the supported functionality for dealing with account information and
 * user interaction in the system.
 *
 */
public class AccountManager {
    IUserBase userBase;
    IObserverManager obMan;

    /**
     * Create an instance of AccountManager.
     * @param userBase IUserBase
     */
    public AccountManager(IUserBase userBase, IObserverManager obMan) {
        this.userBase = userBase;
        this.obMan = obMan;
    }

    /**
     * Like a user.
     * @param username String
     * @param presenter IAccountManager
     * @param currentUser User
     * @return boolean
     */
    public boolean likeUser(String username, IAccountManager presenter, User currentUser){
        String response;
        if(!userBase.isUser(username)){
            response = "User doesn't exist.";
        }else if(userBase.getUser(username).getBanStatus()){
            response = "Operation failed. User is banned.";
        } else if (userBase.getUser(username).getAccountData().contains(currentUser)) {
            response = "You have already liked this user.";
        } else{
            userBase.getUser(username).getAccountData().like(currentUser);
            response = "You have successfully liked " + username;
        }
        presenter.responseLikeUser(response);
        obMan.updateUsers();
        return response.equals("You have successfully liked " + username);
    }

    /**
     * Unlike a user from the system.
     * @param username String
     * @param presenter IAccountManager
     * @param currentUser User
     * @return boolean
     */
    public boolean unLikeUser(String username, IAccountManager presenter, User currentUser){
        String response;
        if(!userBase.isUser(username)){
            response = "User doesn't exist.";
        }else if(userBase.getUser(username).getBanStatus()){
            response = "Operation failed. User is banned.";
        } else if (!userBase.getUser(username).getAccountData().contains(currentUser)) {
            response = "You have not liked this user.";
        }else{
            userBase.getUser(username).getAccountData().unlike(currentUser);
            response = "You have successfully unliked " + username;
        }
        presenter.responseUnlikeUser(response);
        obMan.updateUsers();
        return response.equals("You have successfully unliked " + username);
    }

    /**
     * Check the user's number of likes.
     * @param username String
     * @param presenter IAccountManager
     * @return boolean
     */
    public boolean checkLikes(String username, IAccountManager presenter){
        String response;
        String temp = null;
        if(!userBase.isUser(username)){
            response = "User doesn't exist.";
        }else if(userBase.getUser(username).getBanStatus()){
            response = "Operation failed. User is banned.";
        }else{
            response = username+ " has "+ userBase.getUser(username).getAccountData().getNumLikes() + " likes.";
            temp = response;
        }
        presenter.responseCheckLikes(response);
        obMan.updateUsers();
        return response.equals(temp);
    }

    /**
     * Add user1's favourite to be user2.  Show the result to the presenter when completed.
     * @param user1 String
     * @param user2 String
     * @param presenter IAccountManager
     * @return boolean
     */
    public boolean addFavourite(String user1, String user2, IAccountManager presenter) {
        String response;
        boolean bool = false;
        if (!userBase.isUser(user1) || !userBase.isUser(user2)) {
            response = "One or more users do not exist!";
        } else if (userBase.getUser(user1).getBanStatus() || userBase.getUser(user2).getBanStatus()) {
            response = "One or more users are banned!";
        } else {
            User firstUser = userBase.getUser(user1);
            User secondUser = userBase.getUser(user2);
            firstUser.getAccountData().setFavourite(secondUser);
            response = "Made " + secondUser.getUsername() + " be "
                    + firstUser.getUsername() + "'s favourite user.";
            bool = true;
        }
        presenter.responseAddFavourite(response);
        obMan.updateUsers();
        return bool;
    }

    /**
     * Check user's current favourite user.
     * @param user String
     * @param presenter IAccountManager
     * @return boolean
     */
    public boolean checkFavourite(String user, IAccountManager presenter) {
        String response;
        boolean bool = false;
        if (!userBase.isUser(user)) {
            response = "Not a user.";
        } else if (userBase.getUser(user).getBanStatus()) {
            response = "User is banned.";
        } else if (userBase.getUser(user).getAccountData().getFavourite() == null) {
            response = "There is no favourite.";
        } else {
            response = user + "'s favourite is " + userBase.getUser(user).getAccountData().getFavourite();
            bool = true;
        }
        presenter.responseCheckFavourite(response);
        obMan.updateUsers();
        return bool;
    }

}
