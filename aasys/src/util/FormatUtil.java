package util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import aasystem.User;
public class FormatUtil {
	static HashMap<Integer,String> types = new LinkedHashMap<Integer,String>();

	public FormatUtil() {

	}

	public static SimpleDateFormat myFormat(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df;
	}
	
	public static String myDecimalFormat(double num){
		DecimalFormat df = new DecimalFormat("00.00");
		return df.format(num);
	}
	
	public static HashMap<Integer, String> getPayTypes(){
		types.put(1, "���й���");
		types.put(2, "��������");
		types.put(3, "����¹���");
		types.put(4, "�ճ���Ʒ");
		types.put(5, "������Ŀ");
		return types;
	}
	
	/**
	 * get all users from database
	 * @return hashmap of user id and name
	 */
	public static HashMap<Integer,String> getUsers(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		HashMap<Integer,String> users = new LinkedHashMap<Integer,String>();
		try{
			Query query = session.createQuery("from User");
			@SuppressWarnings("unchecked")
			List<User> list = query.list();
			
			for(User user : list){
				users.put(user.getUserID(), user.getUserName());//add user
			}
			return users;
		}catch(Exception e){
			System.err.println(e);
			return null;
		}finally{
			session.close();
		}
	}

}
