package Presenters.Interfaces;

public interface IUserManager {
    void responseDeleteUser(String response);

    void responseBanUser(String response);

    void responseLogin(String response);

    void responseAddUser(String response);

    void responseCreateUserBase(String response);

    void responseCreateUser(String response);

    void responseMakeAdmin(String response);

}
