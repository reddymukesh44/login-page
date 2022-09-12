package ProgramBuilder;

import java.io.File;

public class SystemComponent implements Paths {
    private String userDB;
    public SystemComponent() {
        // Database Config
        File UserDB = new File("src/DB/userDB.csv");
        this.userDB = UserDB.getAbsolutePath();
    }

    public String getUserPath() {
        return this.userDB;
    }

}
