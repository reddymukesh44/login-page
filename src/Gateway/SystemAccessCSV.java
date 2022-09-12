package Gateway;

import Interfaces.IManager;

import java.beans.PropertyChangeEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// I think this is more of a gateway
public class SystemAccessCSV extends ISystem {
    /* The gateway between the database and the login system.  Read and
     * writes to the database
     */
    private final String filePath;
    private final IManager manager;

    public SystemAccessCSV(String filePath, IManager manager){
        super(filePath, manager);
        this.filePath = filePath;
        this.manager = manager;
    }
    @Override
    public void addRecord(String userInfo) throws IOException {
        // When append is set to true, data is appended rather than overwritten
        this.saveToFile(true, userInfo);
    }

    @Override
    public void dbRefresh() throws IOException {
        String dbData = this.manager.toString();
        // Append is set to false, so current db data will be overwritten by the given dbData
        this.saveToFile(false, dbData);
    }

    @Override
    public void removeRecord(String username) {
        // Remove from UserManager HashMap and remove from DB
    }

    @Override
    public void saveToFile(boolean append, String data) throws IOException {
        // Helper method to write data to the file path
        File f = new File(filePath);
        FileWriter fw = new FileWriter(f, append);
        fw.write(data);
        fw.close();
    }

    public List<String[]> readFromCSV(String filePath) throws FileNotFoundException {

        // FileInputStream can be used for reading raw bytes
        Scanner scanner = new Scanner(new FileInputStream(filePath));
        List<String[]> parsedCSV = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String[] userCSV = scanner.nextLine().split(",");
            parsedCSV.add(userCSV);
        }
        scanner.close();
        return parsedCSV;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            this.dbRefresh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
