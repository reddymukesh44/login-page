package Gateway;

import Interfaces.IManager;

import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public abstract class ISystem implements PropertyChangeListener{
    protected final String filePath;
    protected final IManager manager;

    protected ISystem(String filePath, IManager manager) {
        this.filePath = filePath;
        this.manager = manager;
    }

    public abstract void addRecord(String recordInfo) throws IOException;
    public abstract void dbRefresh() throws IOException;
    public abstract void removeRecord(String identifier);

    public abstract void saveToFile(boolean append, String data) throws IOException;

    public abstract List<String[]> readFromCSV(String filePath) throws FileNotFoundException;
}
