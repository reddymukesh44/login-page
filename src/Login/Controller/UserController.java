package Login.Controller;

import Gateway.ISystem;
import Presenters.IPresenter;
import Login.UseCases.UserManager;

/**
 * UserController provides access to control anything related to the
 * user object.
 *
 */
public class UserController {
    //variables
    private final UserManager um;
    private final IPresenter presenter;
    private final ISystem sa;

    /**
     * Creates a UserController instance.
     * @param presenter IPresenter
     * @param um UserManager
     * @param sa ISystem
     */
    public UserController(IPresenter presenter, UserManager um, ISystem sa){
        this.presenter = presenter;
        this.sa = sa;
        this.um = um;
    }

    /**
     * Bans the user from the system.
     * <p>
     *     Only Admins can ban users.
     * </p>
     * @param username String
     * @param currentUser String
     * @return boolean
     */
    public boolean banUser(String username, String currentUser){
        return um.banUser(username, presenter, currentUser);
    }

    /**
     * Creates an admin user.
     * <p>
     *     Only Admins can create admins.
     * </p>
     * @param username String
     * @param password String
     * @param currentUser String
     * @return boolean
     */
    public boolean createAdminUser(String username, String password, String currentUser){
        return um.createAdminUser(username, password, presenter, currentUser);
    }

    /**
     * Create a regular user.
     * @param username String
     * @param password String
     * @return boolean
     */
    public boolean createRegUser(String username, String password){
        return um.createRegUser(username, password, presenter);
    }

    /**
     * Delete the user from the system.
     * <p>
     *     Only Admins can delete users.
     * </p>
     * @param username String
     * @param currentUser String
     * @return boolean
     */
    public boolean deleteUser(String username, String currentUser){
        return um.deleteUser(sa, presenter, username, currentUser);
    }
}


