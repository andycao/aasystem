package action;

import java.util.HashMap;

import org.hibernate.Session;

import aasystem.Pay;
import aasystem.User;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class editAAUser_Pre extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * generated serial version uid
	 */

	private int payID;
	private HashMap<Integer,String> payUser;
	public editAAUser_Pre() {
	}

	public String execute(){
		Session session = util.HibernateUtil.getSessionFactory().openSession();
		try{
			Pay pay = (Pay) session.get(Pay.class, payID);
			
			//check if the current user is valid for edit
			if(pay.getPayedUserID() != util.SessionUtil.getCurrentUser()){
				addActionError("您没有更改这条记录的权限，如有错误请联系创建者");
				return Action.ERROR;
			}
			
			//set the already exist pay user
			HashMap<Integer,String> users = new HashMap<Integer,String>();
			for(User user : pay.getUsers()){
				//set the userid and username into map
				users.put(user.getUserID(), user.getUserName());
			}
			setPayUser(users);//set to variable
			return Action.SUCCESS;
		}catch(Exception e){
			System.err.println(e);
			addActionError("其他异常，请联系管理员");
			return Action.ERROR;
		}finally{
			session.close();
		}
	}
	
	/**
	 * @return the payID
	 */
	public int getPayID() {
		return payID;
	}

	/**
	 * @param payID the payID to set
	 */
	public void setPayID(int payID) {
		this.payID = payID;
	}

	/**
	 * @return the payUser
	 */
	public HashMap<Integer, String> getPayUser() {
		return payUser;
	}

	/**
	 * @param payUser the payUser to set
	 */
	public void setPayUser(HashMap<Integer, String> payUser) {
		this.payUser = payUser;
	}
}
