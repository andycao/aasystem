package test;


import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;
import aasystem.*;
public class createUserTest {

	public void test() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			
			User user1 = new User();
			user1.setUserID(1);
			user1.setUserName("cao");
			user1.setUserPassword("cao");
			user1.setUserEmail("caohuayu@hotmail.com");
			user1.setUserMobile("110110");
			
			User user2 = new User();
			user2.setUserID(2);
			user2.setUserName("zhang");
			user2.setUserPassword("zhang");
			user2.setUserEmail("zhang@hotmail.com");
			user2.setUserMobile("20202020");
			
			session.save(user1);
			session.save(user2);
			transaction.commit();
			
			
		}catch(Exception e){
			
		}finally{
			session.close();
		}
	}

}
