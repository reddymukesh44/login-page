package Login.Entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User is an abstract entity class that stores user information about the client
 *
 */
public abstract class User implements Serializable {
    protected String username;
    protected String password;
    protected final int userId;
    protected static int totalUsers;
    protected boolean banStatus = false;
    protected boolean adminStatus = false;

    protected AccountData accountData;
    protected HistoryData historyData;
    private ArrayList<String> followers = new ArrayList<String>();
    private ArrayList<String> following = new ArrayList<String>();

    /**
     * Constructs the user object.
     * <p>
     * This method should not be used to create users.  This method is to help
     * RegUser and Admin be instantiated in an OOP manner.
     * <p>
     * @param username String
     * @param password String
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.accountData = new AccountData();
        this.historyData = new HistoryData();
        this.userId = totalUsers;
        totalUsers += 1;

    }


    /**
     * Constructs the user object for reading from the database.
     * <p>
     * This method should not be used to create users.  This method is to help
     * RegUser and Admin be instantiated in an OOP manner.
     * </p>
     * @param username String
     * @param password String
     * @param userId int
     * @param banStatus boolean
     * @param accountData accountData
     * @param historyData historyData
     */
    public User(String username, String password, int userId, boolean banStatus,
                String accountData, String historyData) {
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.banStatus = banStatus;
        String[] parsedAccountData = accountData.split(";");
        this.accountData = new AccountData(Integer.parseInt(parsedAccountData[0]), parsedAccountData[1],
                Boolean.parseBoolean(parsedAccountData[2]),Integer.parseInt(parsedAccountData[3]),parsedAccountData[4]);
        this.historyData = new HistoryData(historyData);
        totalUsers += 1;
    }

    /**
     * Get the user's account data.
     * @return accountData
     */
    public AccountData getAccountData() {
        return accountData;
    }

    /**
     * Get the user's history data.
     * @return historyData
     */
    public HistoryData getHistoryData(){
        return historyData;
    }

    /**
     * Add history to the user.
     * @param loginHistory String
     */
    public void addHistoryData(String loginHistory) {this.historyData.addHistory(loginHistory);}

    /**
     * Get the user's username.
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the user's username.
     * @param username String
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the user's password.
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the user's password.
     * @param password String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the login status
     * @return
     */
    public int getUserId() {
        return userId;
    }

    // MAY REMOVE
    public int getTotalUsers() {
        return totalUsers;
    }

    // DO NOT USE, THIS CODE IS FOR UNIT TESTING PURPOSES
    // MAY REMOVE
    public void setTotalUsers(int num) {
        totalUsers = num;
    }

    /**
     * Set the user's ban status.
     * @param banStatus boolean
     */
    public void setBanStatus(boolean banStatus) {
        this.banStatus = banStatus;
        this.accountData.setBanStatus(banStatus);
    }


    /**
     * Get the user's ban status.
     * @return boolean
     */
    public boolean getBanStatus(){return this.banStatus;}

    /**
     * Get the user's admin status.
     * @return boolean
     */
    public boolean getAdminStatus() {
        return this.adminStatus;
    }

    /**
     * Set the user's admin status.
     * @param adminStatus boolean
     */
    public void setAdminStatus(boolean adminStatus) {
        this.adminStatus = adminStatus;
    }

    /**
     * Get the string representation of the user.
     * <p>
     * This is used to format data for the database.  It is not recommended for regular use.
     * </p>
     * @return String
     */

    /**
     * Follow another user.
     * @param user User
     */
    public void follow(User user) {
        this.following.add(user.getUsername());
        user.addFollowing(this);
    }

    private void addFollowing(User e) {
        this.followers.add(e.getUsername());
    }

    /**
     * Unfollow another user.
     * @param user User
     */
    public void unfollow(User user) {
        if (!this.following(user)) {
            return;
        } else {
            this.following.remove(user.getUsername());
            user.removeFollowing(this);
        }
    }

    private void removeFollowing(User e) {
        this.followers.remove(e.getUsername());
    }

    /**
     * Return if user is following another user.
     * @param user User
     * @return boolean
     */
    public boolean following(User user){
        if(following.contains(user.getUsername())){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get the user's followers list.
     * @return List[String]
     */
    public List<String> getFollowers() {
        return this.followers;
    }

    /**
     * Get the user's following list.
     * @return List[String]
     */
    public List<String> getFollowing() {
        return this.following;
    }

    /**
     * Prepare data for database.
     * @return String
     */
    @Override
    public String toString(){
        // Converting adminStatus from boolean to a String of Admin or Reg is unnecessarily messy
        String admin = "Regular";
        if (this.adminStatus){
            admin = "Admin";
        }

        String accountData = this.accountData.toString();
        String historyData = this.historyData.getHistory().toString();

        historyData = historyData.replace("[", "");
        historyData = historyData.replace("]", "");
        historyData = historyData.replace(",", ";");
        historyData = historyData.strip();

        accountData = accountData.replace("[", "");
        accountData = accountData.replace("]", "");
        accountData = accountData.replace(",", ";");
        accountData = accountData.strip();


        // Current format of history and account data makes passing them as a string in the db difficult
        return this.username + "," + this.password + "," + this.userId + ","+ admin + "," +
                this.banStatus + ","+ accountData + ","+ historyData ;
    }

}
