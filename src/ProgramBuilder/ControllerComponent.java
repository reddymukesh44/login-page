package ProgramBuilder;

import Gateway.ISystem;
import Gateway.SystemAccessCSV;
import Login.Controller.AccountController;
import Login.Controller.FollowController;

import java.io.FileNotFoundException;

public class ControllerComponent {
    private ISystem userSA;
    private FollowController fc;
    private AccountController ac;
    public ControllerComponent(SystemComponent sys, ManagerComponent man, PresenterComponent pre) throws FileNotFoundException {
        // Controllers
        userSA = new SystemAccessCSV(sys.getUserPath(), man.getUm());
        fc = new FollowController(pre.getPresenter(), man.getFm(), userSA);
        ac = new AccountController(pre.getPresenter(), man.getAm(), man.getDm());
        man.getUb().createUserBase(userSA.readFromCSV(sys.getUserPath()),pre.getPresenter());
        man.getObMan().addObserver(userSA);
    }

    public ISystem getUserSA() {
        return userSA;
    }

    public FollowController getFc() {
        return fc;
    }

    public AccountController getAc() {
        return ac;
    }
}
