package Presenters;


import Presenters.Interfaces.*;

/**
 * IPresenter defines what the presenter needs to show all possible data in the program.
 *
 */
public interface IPresenter extends ILoginManager, IUserManager, IAccountManager, IDataManager,
        IFollowManager {
}
