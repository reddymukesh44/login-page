package Login.Entities;

import Interfaces.DataType;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * AccountData stores all the user's likes, favourites, and other user information.
 *
 */
public class AccountData implements DataType, Serializable {
	/* This class stores the fundamental account information
	 * of the user.  The account ID cannot be changed as it is used
	 * to determine account ownership.
	 */
	// accountID is based on the number of accounts
	private static int accountNum;
	private final int accountID;
	private String accountName;
	private boolean accountBanStatus;
	private User favourite;
	private int numLikes;
	private ArrayList<String> likedUsers = new ArrayList<String>();
	/**
	 * Constructs an instance of AccountData.
	 * <p>
	 *     This is called when the user signs up.  It makes an account with
	 *     a generic name based on the ID of the account.
	 * </p>
	 */
	public AccountData() {
		this.accountID = accountNum;
		this.accountName = Integer.toString(this.accountID);
		this.accountBanStatus = false;
		this.numLikes = 0;
		accountNum++;
	}

	/**
	 * Constructs an instance of AccountData.
	 * <p>
	 *     This is called in the use-case classes.
	 * </p>
	 * @param name String
	 */
	public AccountData(String name) {
		this.accountID = accountNum;
		this.accountName = name;
		this.accountBanStatus = false;
		this.numLikes = 0;
		accountNum++;
	}

	// Constructor for reading from db

	/**
	 * Constructs an instance of AccountData.
	 * <p>
	 *     This is called for reading from the database.
	 * </p>
	 * @param accountId int
	 * @param name String
	 * @param banStatus boolean
	 * @param likes int
	 * @param data String
	 */

	public AccountData(int accountId, String name, boolean banStatus, int likes, String data) {
		this.accountID = accountId;
		this.accountName = name;
		this.accountBanStatus = banStatus;
		this.numLikes = likes;

		String[] parsedData = data.split(";");
		for (String username : parsedData){
			likedUsers.add(username.strip());
		}
		accountNum++;
	}

	// DO NOT USE (ONLY IMPLEMENTED FOR UNIT TESTING PURPOSES)

	/**
	 * DO NOT USE
	 * <p>
	 *     Sets the number of accounts in the system.  Changing this number
	 *     will break how AccountData generates unique IDs.
	 * </p>
	 * @param num int
	 */
	public void setAccountNum(int num) {
		accountNum = num;
	}

	/**
	 * Get the account ID.
	 * @return int
	 */
	public int getAccountID() {
		return this.accountID;
	}

	/**
	 * Get the account name.
	 * @return String
	 */
	public String getName() {
		return this.accountName;
	}

	/**
	 * Set the account name.
	 * @param name
	 */
	public void setName(String name) {
		this.accountName = name;
	}

	/**
	 * Get the account's ban status.  (Potential removal in the future)
	 * @return boolean
	 */
	public boolean getBanStatus() {
		return this.accountBanStatus;
	}

	/**
	 * Set the account's ban status.  (Potential removal in the future)
	 * @param status boolean
	 */
	public void setBanStatus(boolean status) {
		this.accountBanStatus = status;
	}

	/**
	 * Get the number of likes the user has.
	 * @return int
	 */
	public int getNumLikes() {
		return this.numLikes;
	}

	/**
	 * Like a user.
	 * @param user User
	 */
	public void like(User user) {
		this.numLikes++;
		likedUsers.add(user.username);
	}

	/**
	 * Unlike a user.
	 * @param user User
	 */
	public void unlike(User user) {
		this.numLikes--;
		likedUsers.remove(user.username);
	}

	/**
	 * Check if a liked user is contained within a user's liked list.
	 * @param user User
	 * @return boolean
	 */
	public boolean contains(User user){
		if (likedUsers.contains(user.username)){
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Set the user's favourite user on the system.
	 * @param favourite User
	 */
	public void setFavourite(User favourite) {
		this.favourite = favourite;
	}

	/**
	 * Get the user's favourite user.
	 * @return User
	 */
	public User getFavourite() {
		return this.favourite;
	}

	/**
	 * Presents accountData for use in the DataManager class.
	 * @return String
	 */
	public String show() {
		return accountID + "," + accountName;
	}
	@Override
	public String toString() {
		String likedUsersString = "null";
		if (!likedUsers.isEmpty()){
			likedUsersString = likedUsers.toString();
		}
		return getAccountID() + ";" + getName() + ";"
				+ getBanStatus() + ";" + getNumLikes() + ";" + likedUsersString;
	}
}
