package Login.Controller;

import Gateway.ISystem;
import Presenters.IPresenter;
import Login.UseCases.IUserBase;
import Login.UseCases.LoginManager;
import Login.UseCases.UserManager;

import java.io.*;

/**
 * LoginSystem manages the control functionality for logging in, logging out, and more.
 *
 */
public class LoginSystem {

    private IUserBase userBase;
    private final UserManager um;
    private final LoginManager lm;
    private final IPresenter presenter;
    private final ISystem sa;

    public LoginSystem(UserManager um, IUserBase userBase, IPresenter presenter, ISystem sa, LoginManager lm) {
        this.um = um;
        this.userBase = userBase;
        this.presenter = presenter;
        this.sa = sa;
        this.lm = lm;
    }
    public boolean login(String username, String password) {
        if (um.verifyUser(username, password, this.presenter)){
            lm.login(userBase.getUser(username));
            return true;
        }
        return false;
    }

    public boolean signUp(String username, String password) throws IOException {
            um.createRegUser(username, password, this.presenter);
            return um.addUser(userBase.getUser(username), presenter, sa);
    }

    public boolean adminLoggedIn(){
        if (lm.getCurrentUser() != null){
            return lm.getCurrentUser().getAdminStatus();
        }
        return false;
    }

    public void logout(){
        lm.logout(presenter);
    }
}
