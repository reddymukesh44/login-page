package Login.UseCases;

import Presenters.Interfaces.IFollowManager;

import java.util.List;

/**
 * The FollowManager denotes all the actions related to following users.
 *
 */
public class FollowManager {
    private IUserBase userBase;
    private IObserverManager obMan;

    /**
     * Create an instance of FollowManager.
     * @param userBase IUserBase
     * @param obMan IObserverManager
     */
    public FollowManager(IUserBase userBase, IObserverManager obMan) {
        this.userBase = userBase;
        this.obMan = obMan;
    }

    /**
     * Follow a user.
     * @param currentUser String
     * @param username String
     * @param presenter IFollowManager
     * @return boolean
     */
    public boolean followUser(String currentUser, String username, IFollowManager presenter){
        String response;
        if(!userBase.isUser(username)){
            response = "User Doesn't Exist.";
        }else if(userBase.getUser(username).getBanStatus()){
            response = "Operation failed. User is banned.";
        } else if (userBase.getUser(username).following(userBase.getUser(currentUser))) {
            response = "You have already followed this user.";
        } else{
            userBase.getUser(username).follow(userBase.getUser(currentUser));
            response = "You have successfully followed " + username;
        }
        presenter.responseFollowUser(response);
        obMan.updateUsers();
        return response.equals("You have successfully followed " + username);
    }

    /**
     * Unfollow a user.
     * @param currentUser String
     * @param username String
     * @param presenter IFollowManager
     * @return boolean
     */
    public boolean unfollowUser(String currentUser, String username, IFollowManager presenter) {
        String response;
        if (!userBase.isUser(username)) {
            response = "User Doesn't Exist.";
        } else if (userBase.getUser(username).getBanStatus()) {
            response = "Operation failed. User is banned.";
        } else if (!userBase.getUser(username).following(userBase.getUser(currentUser))) {
            response = "You have not followed this user.";
        } else {
            userBase.getUser(username).unfollow(userBase.getUser(currentUser));
            response = "You have successfully unfollowed " + username;
        }
        presenter.responseUnFollowUser(response);
        obMan.updateUsers();
        return response.equals("You have successfully unfollowed " + username);
    }

    /**
     * Present the followers list of a user.
     * @param user String
     * @param presenter IFollowManager
     */
    public void presentFollowers(String user, IFollowManager presenter) {
       List<String> followList = userBase.getUser(user).getFollowers();
       for (String e : followList) {
           presenter.responseCheckFollows(e);
       }
    }

    /**
     * Present the following list of a user.
     * @param user String
     * @param presenter IFollowManager
     */
    public void presentFollowing(String user, IFollowManager presenter) {
        List<String> followList = userBase.getUser(user).getFollowing();
        for (String e : followList) {
            presenter.responseCheckFollows(e);
        }
    }
}
