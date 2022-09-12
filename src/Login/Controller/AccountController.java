package Login.Controller;

import Login.Entities.User;
import Login.UseCases.AccountManager;
import Login.UseCases.DataManager;
import Presenters.IPresenter;

public class AccountController {
    // variables
    private final DataManager dm;
    private final AccountManager am;
    private final IPresenter presenter;

    // methods
    public AccountController(IPresenter presenter, AccountManager am, DataManager dm){
        this.presenter = presenter;
        this.dm = dm;
        this.am = am;
    }

    public void showHistoryData(String username){
        dm.getHistoryData(username, presenter);
    }

    public void showAccountData(String username){
        dm.getAccountData(username, presenter);
    }

    public boolean likeUser(String username, User currentUser){
        return am.likeUser(username, presenter, currentUser);
    }

    public boolean unlikeUser(String username, User currentUser){
        return am.unLikeUser(username, presenter, currentUser);
    }

    public boolean checkLikes(String username){
        return am.checkLikes(username, presenter);
    }

    public boolean favouriteUser(String user1, String user2) {
        return am.addFavourite(user1, user2, presenter);
    }

    public boolean checkFavourite(String user) {
        return am.checkFavourite(user, presenter);
    }


}
