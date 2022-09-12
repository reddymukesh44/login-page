package ProgramBuilder;

import MainController.MainController;
import MainController.MainConsole;

import java.io.FileNotFoundException;

public class ProgramBuilder implements Builder {
    private SystemComponent sys;
    private ManagerComponent man;
    private PresenterComponent pre;
    private ControllerComponent con;

    public void buildSystem() {
        this.sys = new SystemComponent();
    }

    public void buildManager() {
        this.man = new ManagerComponent();
    }

    public void buildPresenter() {
        this.pre = new PresenterComponent();
    }

    public void buildController() throws FileNotFoundException {
        this.con = new ControllerComponent(sys, man, pre);
    }

    public MainController getProgram() {
        return new MainConsole(sys, man, pre, con);
    }
}
