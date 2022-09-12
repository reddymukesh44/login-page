package Main;

import MainController.MainController;
import ProgramBuilder.Director;
import ProgramBuilder.Builder;
import ProgramBuilder.ProgramBuilder;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Director director = new Director();
        Builder builder = new ProgramBuilder();
        MainController console = director.construct(builder);
        console.run();
    }
}