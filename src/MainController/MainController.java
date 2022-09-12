package MainController;

import java.io.IOException;

/**
 * The interface that denotes which is the main controller of the program.
 *
 */
public interface MainController {
    /**
     * The main controller must have the ability to run the program.
     * @throws IOException
     */
    void run() throws IOException;
}
