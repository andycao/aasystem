package test;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;
import aasystem.*;
public class test1 {

	public test1() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try{
			//begin transaction then hib can do save
			transaction = session.beginTransaction();
			/*
			User user1 = new User();
			user1.setUserID(1);
			user1.setUserName("cao");
			user1.setUserPassword("cao");
				
			User user2 = new User();
			user2.setUserID(2);
			user2.setUserName("zhang");
			user2.setUserPassword("zhang");
			*/
//			
//			Pay pay1 = new Pay();
//			pay1.setPayAmount(13.0);
//			pay1.setPayDate(new Date());
//			pay1.setPayDone(0);
//			pay1.setPayType("play");
//			pay1.setUserID(1);
			
			
			Pay pay2 = new Pay();
			pay2.setPayAmount(19.23);
			pay2.setPayDate(new Date());
			pay2.setPayDone(1);
			pay2.setPayType("shop");
			pay2.setPayedUserID(2);
			
			User user1 = (User) session.get(User.class, 1);
			User user2 = (User) session.get(User.class, 2);
			/*
			//many to many -search test
			for(Pay pay:user1.getPays()){
				System.out.println(pay.getPayID()+" "+pay.getPayType()+" "+pay.getPayAmount());
			}
			*/
			pay2.getUsers().add(user1);
			pay2.getUsers().add(user2);
			
			session.save(pay2);
			transaction.commit();
		}catch(HibernateException e){
			System.err.println(e);
		}finally{
			session.close();
		}
	}

}
