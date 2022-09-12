package MainController;

import ConsoleControllers.FollowConsoleController;
import ConsoleControllers.ProfileConsoleController;
import Interfaces.IMenu;
import Login.Controller.LoginSystem;
import Login.UseCases.LoginManager;
import Login.Controller.UserController;
import ProgramBuilder.ControllerComponent;
import ProgramBuilder.ManagerComponent;
import ProgramBuilder.PresenterComponent;
import ProgramBuilder.SystemComponent;

import java.io.*;

/**
 * This class is a central controller that is the listening point for the user
 * and stores all the sub controllers and calls their methods according to user's actions.
 * <p>
 * There are several menu methods which display options to the user.
 * There are also action methods that are called depending on user input.
 * </p>
 * <p>
 * Note: enhanced 'switch' does not compile.  This may be due to the
 * version of Java this project runs in.  Please do not make the
 * IntelliJ recommended change.
 * </p>
 */
@SuppressWarnings("EnhancedSwitchMigration")
public class MainConsole implements IMenu, MainController {
    LoginSystem ls;
    LoginManager lm;
    UserController uc;
    FollowConsoleController fcc;
    ProfileConsoleController prc;
    public MainConsole(SystemComponent sys, ManagerComponent man, PresenterComponent pre, ControllerComponent con) {

        ls = new LoginSystem(man.getUm(), man.getUb(), pre.getPresenter(), con.getUserSA(), man.getLm());
        uc = new UserController(pre.getPresenter(), man.getUm(), con.getUserSA());
        fcc = new FollowConsoleController(sys.getUserPath(), con.getFc(), man.getLm());
        prc = new ProfileConsoleController(sys.getUserPath(), con.getAc(), man.getLm());
    }

    @Override
    public void run() throws IOException{
        menu();
    }
    @Override
    public void menu() throws IOException {
        boolean running = true;
        String[] options = {"1-Login",
                            "2-Sign Up",
                            "3-Exit"};

        System.out.println("Select an option:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(running){
            for(String option : options) {
                System.out.println(option);
            }
            String option = br.readLine();
            switch (option) {
                case "1" :
                    login();
                    break;
                case "2" :
                    signUp();
                    break;
                case "3" :
                    running = false;
                    exitProgram();
                    break;
                default :
                    System.out.println("Oops! Please select a valid option.");
            }
        }
    }

    public void login() throws IOException {
        boolean running = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(running) {
            System.out.println("Please enter your username:");
            String username = br.readLine();
            System.out.println("Please enter your password:");
            String password = br.readLine();

            if (ls.login(username, password)) {
                if (ls.adminLoggedIn()) {
                    adminMenu();
                } else {
                    regularMenu();
                }
            }
            else {
                System.out.println("Please try again.\n");
                running = false;
            }
        }
    }

    public void signUp() throws IOException {
        boolean running = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(running) {
            System.out.println("Please enter your username:");
            String username = br.readLine();
            System.out.println("Please enter your password:");
            String password = br.readLine();

            if (uc.createRegUser(username, password)){
                break;
            }
            else {
                System.out.println("Unexpected Error.");
                running = false;
            }
        }
    }
    public void adminMenu() throws IOException {
        String[] options = {
                "1-Create Admin User",
                "2-Delete User",
                "3-Ban User",
                "4-Follow Menu",
                "5-Profile Menu",
                "6-Logout",
                "7-Exit"};

        System.out.println("Select an option:");
        boolean running = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(running){
            for(String option : options) {
                System.out.println(option);
            }
            String option = br.readLine();
            switch (option) {
                case "1" :
                    createAdminUser();
                    break;
                case "2" :
                    deleteUser();
                    break;
                case "3" :
                    banUser();
                    break;
                case "4" :
                    fcc.menu();
                    break;
                case "5" :
                    prc.menu();
                    break;
                case "6" :
                    logout();
                    break;
                case "7" :
                    running = false;
                    exitProgram();
                    break;
                default :
                    System.out.println("Oops! Please select a valid option.");
            }
        }
    }

    public void regularMenu() throws IOException {
        String[] options = {
                "1-Follow Menu", "2-Profile Menu", "3-Logout", "4-Exit"};

        System.out.println("Select an option:");

        boolean running = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(running){
            for(String option : options) {
                System.out.println(option);
            }
            String option = br.readLine();
            switch (option) {
                case "1" :
                    fcc.menu();
                    break;
                case "2" :
                    prc.menu();
                    break;
                case "3" :
                    logout();
                    break;
                case "4" :
                    running = false;
                    exitProgram();
                    break;
                default :
                    System.out.println("Oops! Please select a valid option.");
            }
        }
    }

    public void createAdminUser() throws IOException {
        boolean running = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(running){
            System.out.println("Please enter new user's username:");
            String username = br.readLine();
            System.out.println("Please enter new user's password:");
            String password = br.readLine();

            if(uc.createAdminUser(username, password, lm.getCurrentUser().getUsername())){
                adminMenu();
            }
            else {
                System.out.println("Unexpected Error");
                running = false;
            }
        }
    }

    public void deleteUser() throws IOException {
        boolean running = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(running){
            System.out.println("Please enter the username you'd like to delete:");
            String username = br.readLine();

            if(uc.deleteUser(username, lm.getCurrentUser().getUsername())){
                adminMenu();
            }
            else {
                System.out.println("Unexpected Error");
                running = false;
            }
        }
    }

    public void banUser() throws IOException {
        boolean running = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(running){
            System.out.println("Please enter the username you'd like to ban:");
            String username = br.readLine();

            if(uc.banUser(username, lm.getCurrentUser().getUsername())){
                adminMenu();
            }
            else {
                System.out.println("Unexpected Error");
                running = false;
            }
        }
    }

    public void logout() throws IOException {
        ls.logout();
        menu();
    }
    public void exitProgram() throws IOException {
        System.exit(0);
    }


}
