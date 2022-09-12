package Login.Entities;

import Interfaces.DataType;

import java.io.Serializable;
import java.util.*;

/**
 * HistoryData stores all log-in information and other types of data related to time and date.
 *
 */
public class HistoryData implements DataType, Serializable {
	/* Whenever a user logs into the system, this class
	 * stores the date and time of the log-in.
	 */

	Deque<String> history = new ArrayDeque<>() {
	};

	/**
	 * Creates HistoryData.
	 * <p>
	 *     This is called when a user signs up to the system.
	 * </p>
	 */
	public HistoryData() {
		Date date = Date.from(java.time.Clock.systemUTC().instant());
		history.add(date.toString().strip());
	}

	// Constructor for reading from db

	/**
	 * Creates HistoryData.
	 * <p>
	 *     This is called for reading from the database.
	 * </p>
	 * @param historyData
	 */
	public HistoryData(String historyData){
		String[] parsedData = historyData.split(";");
		for (String date : parsedData){
			history.add(date.strip());
		}
	}

	/**
	 * Add history whenever a user logs into the system.
	 * @param loginDate String
	 */
	public void addHistory(String loginDate){
		history.add(loginDate);
	}

	/**
	 * Get the history.
	 * @return Deque[String]
	 */
	public Deque<String> getHistory(){
		return this.history;
	}

	/**
	 * Get the history of when the user last logged into the system.
	 * @return String
	 */
	public String lastLogin()	{
		return history.peek();
	}

	/**
	 * Presents historyData for use in the DataManager class.
	 * @return String
	 */
	public String show() {
		return history.toString();
	}
	@Override
	public String toString() {
		return "HistoryData{" +
				"loginHis=" + history.toString() +
				'}';
	}

}
