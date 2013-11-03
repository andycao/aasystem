package action;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;
import util.SessionUtil;
import aasystem.User;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class userAction extends ActionSupport{
	private String userName;
	private String userPassword;
	private String oldPassword;
	
	private String newPassword;
	private String newPassword2;
	private int userID;
	
	private static final long serialVersionUID = 1L;

	public userAction() {
		
	}
	
	public String login(){
		//check http session
		if(SessionUtil.getCurrentUser() != 0)
			return Action.SUCCESS;
		
		//username or password can be space? yes
		if(!userName.equals("") && !userPassword.equals("")){
			//get session
			Session session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from User where userName =:name and userPassword =:pass");
			query.setParameter("name",userName);
			query.setParameter("pass",userPassword);
			
			@SuppressWarnings("unchecked")
			List<User> list = query.list();
			if(list.size() == 1){
				//set the current user to session
				SessionUtil.setCurrentUser(list.get(0).getUserID());
				return Action.SUCCESS;// login success
			}else{
				addActionError(getText("login.error.wrongpass","pass error"));
				return Action.ERROR; // login failed
			}
		}else
			addActionError(getText("login.error.no-name-or-pass","enter name and pass"));
			return Action.INPUT;
	}
	
	/**
	 * logout action
	 * @return string
	 */
	public String logout(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> httpsession = context.getSession();
		httpsession.remove("user");
		return Action.SUCCESS;
	}
	/**
	 * change password action
	 * @return success login--need login
	 */
	public String changePassword(){
		//check whether has loggedin
		String str = this.login();
		//if str is success
		if(str.equals(Action.SUCCESS)){
			
			//check new password need to be same
			if(!newPassword.equals(newPassword2)){
				addActionError(getText("新密码不相同，请重新输入。。。"));
				return Action.ERROR;
			}
			//set new password
			return this.setUserNewPassword(userID, newPassword);
		}else{
			addActionError(getText("验证错误"));
			System.out.println(userID +" "+newPassword);
			return Action.ERROR;
		}
			
	}
	/**
	 * set the new pass
	 * @param userid int
	 * @param pass string
	 * @return success or error
	 */
	private String setUserNewPassword(int userid,String pass){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;
		try{
			trans = session.beginTransaction();
			User user = (User) session.get(User.class, userid);
			//check old password
			System.out.println(user.getUserPassword());
			if(!user.getUserPassword().equals(oldPassword)){
				addActionError("老密码验证错误");
				return Action.ERROR;
			}
			user.setUserPassword(pass);
			
			session.save(user);
			trans.commit();
			return Action.SUCCESS;
		}catch(Exception e){
			System.err.println(e);
			return Action.ERROR;
		}finally{
			session.close();
		}
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
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
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
	 * @return the oldPassword
	 */
	public String getOldPassword() {
		return oldPassword;
	}

	/**
	 * @param oldPassword the oldPassword to set
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/**
	 * @return the newPassword2
	 */
	public String getNewPassword2() {
		return newPassword2;
	}

	/**
	 * @param newPassword2 the newPassword2 to set
	 */
	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}
}
