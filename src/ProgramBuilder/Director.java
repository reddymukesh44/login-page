package ProgramBuilder;

import MainController.MainController;

import java.io.FileNotFoundException;

public class Director {
    /**
     * Creates an instance of director.
     */
    public Director() {}

    /**
     * Denotes how the builder is to construct the program.
     * @param builder Builder
     */
    public MainController construct(Builder builder) throws FileNotFoundException {
        builder.buildSystem();
        builder.buildManager();
        builder.buildPresenter();
        builder.buildController();
        return builder.getProgram();
    }
}
