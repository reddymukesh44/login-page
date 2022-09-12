package Login.UseCases;

import Interfaces.DataType;
import Presenters.Interfaces.IDataManager;

/**
 * DataManager manages the data in the system (it currently manages user specific data)
 *
 */
public class DataManager {
    // Change this variable to change the userBase constructor
    IUserBase userBase;
    public DataManager(IUserBase userBase){
        this.userBase = userBase;
    }
    /**
     * Return the user's account data.
     * @param user String
     * @param presenter IDataManager
     */
    public void getAccountData(String user, IDataManager presenter){
        DataType accountData = userBase.getUser(user).getAccountData();
        presenter.showData(accountData);
    }

    /**
     * Return the user's history data.
     * @param user String
     * @param presenter IDataManager
     */
    public void getHistoryData(String user, IDataManager presenter){
        DataType historyData = userBase.getUser(user).getHistoryData();
        presenter.showData(historyData);
    }
}
