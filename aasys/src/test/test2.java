package test;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.FormatUtil;
import util.HibernateUtil;
import aasystem.Pay;
import aasystem.User;
import action.funcAction;

public class test2 {

	public test2() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		test2 test = new test2();
//		test.setMoreUser();
//		test.calcTotal();
//		test.calcAverage();
//		test.calcRacent();
//		test.calcTotalByUser();
		HashMap<Integer, String> map = FormatUtil.getPayTypes();
		
		for(int i: map.keySet()){
			System.out.println(map.get(i));
		}
		System.out.println("@@@@@@@@@@@@@");
	}
	
	
	public void setMoreUser(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			Pay pay = (Pay) session.get(Pay.class, 6);
			User user1 = (User) session.get(User.class, 1);
			pay.getUsers().add(user1);
			pay.getUsers().add(user1);
			
			for(User user : pay.getUsers()){
				System.out.println(user.getUserName());
			}
			
			session.save(pay);
			transaction.commit();
			
		}catch(Exception e){
			System.err.println(e);
		}finally{
			session.close();
		}
	}
	
	public void getUserNo6(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();

			Pay pay = (Pay) session.get(Pay.class, 6);
			System.out.println(pay.getUsers().size());
			transaction.commit();
		}catch(Exception e){
			System.err.println(e);
		}finally{
			session.close();
		}
	}

	public void calcTotal(){
		funcAction func = new funcAction();
		func.calcTotalCostAll();
		System.out.println(func.getTotalAmount());
	}
	
	public void calcAverage(){
		funcAction func = new funcAction();
		func.calcCurrentAverageToPay();
		Map<Integer,Double> result = func.getCurrentAverage();
		double total = 0;
		for(int i :result.keySet()){
			System.out.println(i+" : "+result.get(i));
			total += result.get(i);
		}
		System.out.println("totla : "+total);
	}
	
	public void calcRacent(){
		funcAction func = new funcAction();
		func.calcRacentCostAllOneMonth();
		System.out.println(func.getTotalAmount());
	}
	public void calcTotalByUser(){
		funcAction func = new funcAction();
		func.setUserid(2);//set the user to calc
		func.calcCostAllByUser();
		System.out.println(func.getTotalAmount());
	}
}

