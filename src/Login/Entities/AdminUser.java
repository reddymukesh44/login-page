package Login.Entities;

/**
 * Admin extends from the User class.  Admins have the power to moderate in the
 * system.  They have more functionality to moderate.
 *
 */
public class AdminUser extends User {
    /* The Admin User can ban and remove other users from the system.
     * Note: maybe consider adding a ban and remove method?
     */

    /**
     * Creates an instance of AdminUser.
     * <p>
     *     This is called when making admin users.
     * </p>
     * @param username String
     * @param password String
     */
    public AdminUser(String username, String password) {
        super(username, password);
        this.adminStatus = true;
    }

    /**
     * Creates an instance of AdminUser.
     * <p>
     *     This is called for reading from the database.
     * </p>
     * @param username String
     * @param password String
     * @param userId int
     * @param banStatus boolean
     * @param accountData accountData
     * @param historyData historyData
     */
    public AdminUser(String username, String password, int userId, boolean banStatus,
                   String accountData, String historyData){
        super(username, password, userId, banStatus, accountData, historyData);
        this.adminStatus = true;
    }
}
