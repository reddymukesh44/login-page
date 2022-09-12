package ProgramBuilder;

import MainController.MainController;

import java.io.FileNotFoundException;

/**
 * This interface denotes the methods needed to build the program.
 *
 */
public interface Builder {
    void buildSystem();
    void buildManager();
    void buildPresenter();
    void buildController() throws FileNotFoundException;
    MainController getProgram();
}
