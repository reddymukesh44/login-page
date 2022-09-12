package Login.Controller;

import Gateway.ISystem;
import Presenters.IPresenter;
import Login.UseCases.FollowManager;

/**
 * The FollowController dictates all follow interaction that the user manipulates.
 *
 */
public class FollowController {
    //variables
    private final FollowManager fm;
    private final IPresenter presenter;
    private final ISystem sa;

    /**
     * Creates a FollowController instance.
     * @param presenter IPresenter
     * @param fm UserManager
     * @param sa ISystem
     */
    public FollowController(IPresenter presenter, FollowManager fm, ISystem sa){
        this.presenter = presenter;
        this.sa = sa;
        this.fm = fm;
    }

    public void getFollowing(String user) {
        fm.presentFollowing(user, presenter);
    }

    public void getFollowers(String user) {
        fm.presentFollowers(user, presenter);
    }

    public boolean follow(String currentUser, String otherUser) {
        return fm.followUser(currentUser, otherUser, presenter);
    }

    public boolean unfollow(String currentUser, String otherUser) {
        return fm.unfollowUser(currentUser, otherUser, presenter);
    }

}
