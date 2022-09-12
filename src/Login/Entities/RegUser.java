package Login.Entities;

/**
 * RegUser extends from the User class.  Regular users do not have much functionality in the
 * system compared to admin users.
 *
 */
public class RegUser extends User {
    /* Defines the regular user.  A user can
     * make an account and perform regular activities,
     * but it does not have the same privileges as an admin user
     */

    /**
     * Creates an instance of a regular user.
     * <p>
     *     This is called when making regular users.
     * </p>
     * @param username String
     * @param password String
     */
    public RegUser(String username, String password) {
        super(username, password);
        this.adminStatus = false;
    }

    /**
     * Creates an instance of a regular user.
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
    public RegUser(String username, String password, int userId, boolean banStatus,
                String accountData, String historyData){
        super(username, password, userId, banStatus, accountData, historyData);
        this.adminStatus = false;
    }
}
