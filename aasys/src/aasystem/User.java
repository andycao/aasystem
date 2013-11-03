package aasystem;

import java.util.HashSet;
import java.util.Set;
/**
 * user bean class
 * @author andyc
 *
 */
public class User {
	
	private int userID;
	private String userPassword;
	private String userName;
	private String userEmail;
	private String userMobile;
	private Set<Pay> pays = new HashSet<Pay>();//for many-to-many hibernate mapping,
	//the bills which the user need to pay
	public User(){
	}
	/**
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}
	/**
	 * @param userID the userID to set
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}
	/**
	 * @return the userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}
	/**
	 * @param userPassword the userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}
	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	/**
	 * @return the userMobile
	 */
	public String getUserMobile() {
		return userMobile;
	}
	/**
	 * @param userMobile the userMobile to set
	 */
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	/**
	 * @return the pays which the user has payed
	 */
	public Set<Pay> getPays() {
		return pays;
	}
	/**
	 * @param pays the pays to set
	 */
	public void setPays(Set<Pay> pays) {
		this.pays = pays;
	}

}
