//package Gateway;
//
//import Interfaces.IManager;
//
//import java.beans.PropertyChangeEvent;
//import java.io.*;
//
//
///* The gateway between the database and the login system.  Read and
// * writes to the database
// */
//public class SystemAccessSER extends ISystem{
//    public SystemAccessSER(String filePath, IManager manager) {
//        super(filePath, manager);
//    }
//
//
//    @Override
//    public void addRecord(Object data) throws IOException {
//        this.dbRefresh(data);
//    }
//
//    @Override
//    public void dbRefresh(Object data) throws IOException {
//        this.saveToFile(this.filePath, data);
//    }
//
//    @Override
//    public void removeRecord(Object data) {
//        // Remove from UserManager HashMap and remove from DB
//    }
//
//    public Object readFromFile(String filePath) throws FileNotFoundException{
//        try{
//            InputStream file = new FileInputStream(filePath);
//            InputStream buffer = new BufferedInputStream(file);
//            ObjectInput input = new ObjectInputStream(buffer);
//
//            Object data = input.readObject();
//            input.close();
//            return data;
//        }catch(EOFException e){
//            System.out.println("New file");
//        }catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
//    }
//
//    public void saveToFile(String filePath, Object object) throws IOException{
//        OutputStream file = new FileOutputStream(filePath);
//        OutputStream buffer = new BufferedOutputStream(file);
//        ObjectOutput output = new ObjectOutputStream(buffer);
//
//        output.writeObject(object);
//        output.close();
//    }
//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//        try {
//            this.dbRefresh(evt.getNewValue());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
