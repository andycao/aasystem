package action;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import aasystem.Pay;
import aasystem.User;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import util.HibernateUtil;

public class editAAUser extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Integer> aaUser; 
	private int payID;
	public editAAUser() { 
	}

	public String execute(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			Pay pay = (Pay) session.get(Pay.class, payID);//get the pay object
			Set<User> payUsers = pay.getUsers();
			payUsers.clear();//clear
			for(int userid : aaUser){
				payUsers.add((User)session.get(User.class, userid));
			}

			transaction.commit();
			return Action.SUCCESS;
		}catch(Exception e){
			System.err.println(e);
			addActionError("其他错误，请联系管理员");
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
	 * @return the aaUser
	 */
	public List<Integer> getAaUser() {
		return aaUser;
	}

	/**
	 * @param aaUser the aaUser to set
	 */
	public void setAaUser(List<Integer> aaUser) {
		this.aaUser = aaUser;
	}
	
}
