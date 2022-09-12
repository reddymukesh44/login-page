package Presenters;

import Interfaces.DataType;

/**
 * ConsolePresenter is the main presenter for the program.
 *
 */
public class ConsolePresenter implements IPresenter {
    @Override
    public void showData(DataType dataObject) {
        System.out.println(dataObject.show());
    }
    @Override
    public void responseDeleteUser(String response) {
        System.out.print(response);
    }

    @Override
    public void responseBanUser(String response) {
        System.out.print(response);
    }

    @Override
    public void responseLogin(String response) {
        System.out.println(response);
    }

    @Override
    public void responseAddUser(String response) {
        System.out.println(response);
    }

    @Override
    public void responseCreateUserBase(String response) {
        System.out.println(response);
    }

    @Override
    public void responseCreateUser(String response) {
        System.out.println(response);
    }

    @Override
    public void responseMakeAdmin(String response) {
        System.out.println(response);
    }

    @Override
    public void responseLikeUser(String response) {
        System.out.println(response);
    }

    @Override
    public void responseUnlikeUser(String response) {
        System.out.println(response);
    }

    @Override
    public void responseCheckFavourite(String response) {
        System.out.println(response);
    }

    @Override
    public void responseAddFavourite(String response) {
        System.out.println(response);
    }


    @Override
    public void responseCheckLikes(String response) {
        System.out.println(response);
    }
    @Override
    public void responseFollowUser(String response){
        System.out.println(response);
    }
    @Override
    public void responseUnFollowUser(String response){
        System.out.println(response);
    }
    @Override
    public void responseCheckFollows(String response) {System.out.println(response);}
    @Override
    public void responseLogout(String response) {
        System.out.println(response);
    }

}
