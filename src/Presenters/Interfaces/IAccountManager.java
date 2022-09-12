package Presenters.Interfaces;

public interface IAccountManager {
    void responseLikeUser(String response);

    void responseUnlikeUser(String response);

    void responseCheckFavourite(String response);

    void responseAddFavourite(String response);

    void responseCheckLikes(String response);
}
