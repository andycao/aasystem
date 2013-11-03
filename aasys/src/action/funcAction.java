package action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;

import aasystem.Pay;
import aasystem.User;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class funcAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final long ONE_MONTH = (long)1000 * 60 * 60 * 24 * 30;

	//pay done is 1
	private final int PAY_NOT_DONE = 0;
	
	private double totalAmount;//total cost from start
	private double totalRacent;//racent total cost
	private double totalMyPays;//current user's total pays
	
	private int userID;//userid for calc user payed amount
	private Map<Integer,Double> currentAverage;//current 
	public funcAction() {
	}
	
	/**
	 * calc all amount does not matter it is done or not
	 * @return double amount
	 */
	public String calcTotalCostAll(){
		//set total to 0
		double total = 0.0;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			//get all pay
			Query query = session.createQuery("from Pay");
			@SuppressWarnings("unchecked")
			List<Pay> list = query.list();
			for(Pay pay : list){
				total += pay.getPayAmount();
			}
			this.setTotalAmount(total);
			
			//calc success
			return Action.SUCCESS;
		}catch(Exception e){
			System.err.println(e);
			return Action.ERROR;
		}finally{
			session.close();
		}
	}
	/**
	 * calculate current average amount for every user for all pay which is not done
	 * @return success,error,currentAverage map
	 */
	public String calcCurrentAverageToPay(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		initCurrentAverage();//init the current average
		try{
			//get all pay with paydone = 0
			Query query = session.createQuery("from Pay where PayDone="+PAY_NOT_DONE);
			@SuppressWarnings("unchecked")
			List<Pay> list = query.list();
			
			//do calc for every pay
			for(Pay pay : list){
				boolean result = this.doPayAverage(pay);
				if(!result){
					addActionError("账单计算错误，可能有账单未添加aa用户");
					return Action.ERROR;
				}
			}
			return Action.SUCCESS;
		}catch(Exception e){
			System.err.println(e);
			return Action.ERROR;
		}finally{
			session.close();
		}
	}
	
	/**
	 * get all one month recent pays
	 * @return success error
	 */
	public String calcRacentCostAllOneMonth(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Query query = session.createQuery("from Pay where payDate>=:monthdate and payDate<=:now order by payDate DESC");
			Date now = new Date();
			long nows = now.getTime();
			Date monthdate = new Date(nows-ONE_MONTH);
			
			query.setParameter("monthdate", monthdate);
			query.setParameter("now", now);
			
			@SuppressWarnings("unchecked")
			List<Pay> list = query.list();
			totalRacent = 0.0;//clear total
			for(Pay pay : list)
				totalRacent += pay.getPayAmount();
			return Action.SUCCESS;
			
		}catch(Exception e){
			System.err.println(e);
			return Action.ERROR;
		}finally{
			session.close();
		}

	}
	
	/**
	 * calc how much does the user payed all the time
	 * @return string
	 */
	public String calcCostAllByUser(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Query query = session.createQuery("from Pay where userID=:user");
			query.setParameter("user", userID);
			
			@SuppressWarnings("unchecked")
			List<Pay> list = query.list();
			totalMyPays = 0.0; //clear total amount
			for(Pay pay : list){
				totalMyPays += pay.getPayAmount();
			}
			return Action.SUCCESS;
		}catch(Exception e){
			System.err.println(e);
			return Action.ERROR;
		}finally{
			session.close();
		}
	}
	/**
	 * do average for a pay
	 * @param pay - obj
	 * @return boolean
	 */
	private boolean doPayAverage(Pay pay){
		double average = pay.getPayAmount()/pay.getUsers().size();
		double amountTest = 0.0;
		
		//for the user who has already payed the bill, we should minus the amount
		this.addUserPays(pay.getPayedUserID(), -pay.getPayAmount());
		
		for(User user : pay.getUsers()){
			//add amount to every user
			this.addUserPays(user.getUserID(), average);
			
			//test amount increment
			amountTest += average;
		}
		//test
		if(amountTest == pay.getPayAmount())
			return true;
		else
			return false;
	}
	private void initCurrentAverage(){
		try{
			currentAverage.clear();
		}catch(NullPointerException e){
			currentAverage = new HashMap<Integer,Double>();
		}
	}
	/**
	 * add the amount to userid for pay
	 * @param userid int
	 * @param amount double
	 * @return
	 */
	private boolean addUserPays(int userid,double amount){
		Double num = currentAverage.get(userid);
		if(num!=null){
			num += amount;
			currentAverage.put(userid, num);
			return true;
		} else{
			currentAverage.put(userid, amount);
			return true;
		}
			
	}
	/**
	 * @return the totalAmount
	 */
	public double getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount the totalAll to set
	 */
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * @return the currentAverage
	 */
	public Map<Integer,Double> getCurrentAverage() {
		return currentAverage;
	}

	/**
	 * @param currentAverage the currentAverage to set
	 */
	public void setCurrentAverage(Map<Integer,Double> currentAverage) {
		this.currentAverage = currentAverage;
	}

	/**
	 * @return the userid
	 */
	public int getUserid() {
		return userID;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(int userid) {
		this.userID = userid;
	}

	/**
	 * @return the totalRacent
	 */
	public double getTotalRacent() {
		return totalRacent;
	}

	/**
	 * @param totalRacent the totalRacent to set
	 */
	public void setTotalRacent(double totalRacent) {
		this.totalRacent = totalRacent;
	}

	/**
	 * @return the totalMyPays
	 */
	public double getTotalMyPays() {
		return totalMyPays;
	}

	/**
	 * @param totalMyPays the totalMyPays to set
	 */
	public void setTotalMyPays(double totalMyPays) {
		this.totalMyPays = totalMyPays;
	}
}
