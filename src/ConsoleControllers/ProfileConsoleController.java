package ConsoleControllers;

import Interfaces.IMenu;
import Login.Controller.AccountController;
import Login.UseCases.LoginManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** The ProfileConsoleController determines actions that the user can take on the system.
 *
 */
public class ProfileConsoleController implements IMenu {
    private String userDB;
    private AccountController ac;
    private LoginManager lm;

    public ProfileConsoleController(String userDB, AccountController ac, LoginManager lm) {
        this.userDB = userDB;
        this.ac = ac;
        this.lm = lm;
    }

    @Override
    public void menu() throws IOException {
        String[] options = {"1-Like a user", "2-Unlike a user", "3-Favourite a user",
                "4-Check your likes", "5-Check your favourite", "6-Check a user's favourite","7-View Login History",
                "8-Close Menu"};
        System.out.println("Select an option:");
        boolean running = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (running) {
            for (String option : options) {
                System.out.println(option);
            }
            String choice = br.readLine();
            switch (choice) {
                case "1" :
                    String name1 = selectUser();
                    ac.likeUser(name1, lm.getCurrentUser());
                    break;
                case "2" :
                    String name2 = selectUser();
                    ac.unlikeUser(name2, lm.getCurrentUser());
                    break;
                case "3" :
                    String name3 = selectUser();
                    ac.favouriteUser(lm.getCurrentUser().getUsername(), name3);
                    break;
                case "4" :
                    ac.checkLikes(lm.getCurrentUser().getUsername());
                    break;
                case "5" :
                    ac.checkFavourite(lm.getCurrentUser().getUsername());
                    break;
                case "6" :
                    String name6 = selectUser();
                    ac.checkFavourite(name6);
                    break;
                case "7":
                    ac.showHistoryData(lm.getCurrentUser().getUsername());
                    break;
                case "8" :
                    running = false;
                    break;
                default :
                    System.out.println("Oops! Please select a valid option.");
            }
        }
    }

    private String selectUser() throws IOException {
        System.out.println("What is the user's name?");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }
}
