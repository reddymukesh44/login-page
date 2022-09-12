package Login.UseCases;

import Login.Entities.User;

import java.beans.PropertyChangeEvent;
import java.util.HashMap;

/**
 * Responsible for updating users and sending requests.
 *
 */
public class UpdateUser {
    IUserBase ub;

    public UpdateUser(IUserBase ub){
        this.ub = ub;
    }

    // This update method should be called whenever pets are updated
    public PropertyChangeEvent newEvent(){
        // Naive implementation
        HashMap<String, User> users = ub.getSystem();
        return new PropertyChangeEvent(users, "users", users, users);
    }
}
