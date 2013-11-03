package util;

import java.util.Map;

import org.hibernate.Session;

import aasystem.User;

import com.opensymphony.xwork2.ActionContext;

public class SessionUtil {

	public SessionUtil() {
	}
	
	/**
	 * set the current login user to this user -- should be username
	 * @param myuser
	 */
	public static void setCurrentUser(int myuser){
		//get the action context
		ActionContext cxt = ActionContext.getContext();
		//http session, map format
		Map<String,Object> session = cxt.getSession();
		//set the user parameter to by the user object
		session.put("user", myuser);
	}
	
	/**
	 * get the session user id, return 0 if there is no user exists
	 * @return string username 
	 */
	public static int getCurrentUser(){
		//get the action context
		ActionContext cxt = ActionContext.getContext();
		//http session
		Map<String,Object> session = cxt.getSession();
		if(session.get("user") == null)
			return 0;
		else
			return (Integer) session.get("user");
	}
	
	/**
	 * current user obj
	 * @return current user obj
	 */
	public static User getUserObj(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			User user = (User) session.get(User.class, getCurrentUser());
			return user;
		}catch(Exception e){
			System.err.println(e);
			return null;
		}finally{
			session.close();
		}

	}
}
