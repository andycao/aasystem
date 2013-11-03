package aasystem;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
/**
 * pay bean class
 * @author andyc
 *
 */
public class Pay {
	private int payID; //id for pay
	private String payType; //category
	private double payAmount; //double amount
	private String payDetail; //string description
	private int payedUserID; // who has payed the bill
	private int payDone; //done
	private Date payDate; //time of pay date
	private Set<User> users = new HashSet<User>();//many-to-many, users who need to pay the bill
	public Pay() {
	}
	public int getPayID() {
		return payID;
	}
	public void setPayID(int payID) {
		this.payID = payID;
	}
	public String getPayType(){
		return payType;
	}
	public int getPayTypeInt() {
		return Integer.parseInt(payType);
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public double getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(double payAmount) {
		this.payAmount = payAmount;
	}
	public String getPayDetail() {
		return payDetail;
	}
	public void setPayDetail(String payDetail) {
		this.payDetail = payDetail;
	}
	public int getPayDone() {
		return payDone;
	}
	public void setPayDone(int payDone) {
		this.payDone = payDone;
	}
	/**
	 * @return the payDate
	 */
	public Date getPayDate() {
		return payDate;
	}
	/**
	 * @param payDate the payDate to set
	 */
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	/**
	 * @return the users
	 */
	public Set<User> getUsers() {
		return users;
	}
	/**
	 * @param users the users to set
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	/**
	 * @return the payedUserID
	 */
	public int getPayedUserID() {
		return payedUserID;
	}
	/**
	 * @param payedUserID the payedUserID to set
	 */
	public void setPayedUserID(int payedUserID) {
		this.payedUserID = payedUserID;
	}
	
}
