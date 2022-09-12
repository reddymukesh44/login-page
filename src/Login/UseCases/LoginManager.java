package Login.UseCases;

import Login.Entities.User;
import Presenters.Interfaces.ILoginManager;

import java.util.Date;

public class LoginManager {

    private User currentUser;
    private IObserverManager obMan;
    public LoginManager(IObserverManager obMan) {
        this.obMan = obMan;
    }
    public void recordLogin(User user) {
        String loginDate = String.valueOf(Date.from(java.time.Clock.systemUTC().instant()));
        user.addHistoryData(loginDate);
        obMan.updateUsers();
    }
    public void login(User user){
        this.recordLogin(user);
        this.setCurrentUser(user);
    }

    public void logout(ILoginManager presenter){
        this.currentUser = null;
        presenter.responseLogout("Logged out.");
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public void setCurrentUser(User user){
        this.currentUser = user;
    }
}
