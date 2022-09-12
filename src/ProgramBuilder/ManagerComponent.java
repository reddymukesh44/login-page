package ProgramBuilder;

//import Login.Controller.UseCases.*;
import Login.UseCases.*;

public class ManagerComponent {
    private IUserBase ub;
    private IObserverManager obMan;
    private UserManager um;
    private FollowManager fm;
    private AccountManager am;
    private DataManager dm;
    private LoginManager lm;
    public ManagerComponent() {
        // Use-Cases
        ub = new UserBase();
        obMan = new ObserverManager(ub);
        um = new UserManager(ub, obMan);
        fm = new FollowManager(ub, obMan);
        am = new AccountManager(ub, obMan);
        dm = new DataManager(ub);
        lm = new LoginManager(obMan);
    }

    public IUserBase getUb() {
        return ub;
    }

    public IObserverManager getObMan() {
        return obMan;
    }

    public UserManager getUm() {
        return um;
    }

    public FollowManager getFm() {
        return fm;
    }

    public AccountManager getAm() {
        return am;
    }


    public DataManager getDm() {
        return dm;
    }


    public LoginManager getLm() {
        return lm;
    }
}
