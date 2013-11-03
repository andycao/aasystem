package action;

import java.util.Date;

import org.hibernate.Session;

import aasystem.Pay;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import util.HibernateUtil;
import util.SessionUtil;
public class preEditPayAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int payID;
	private int payDone;
	private String payType;
	private String payDetail;
	private double payAmount;
	private int payedUserID;
	private Date payDate;
	
	public preEditPayAction() {
		
	}

	public String execute(){
		//check logged in
		if(SessionUtil.getCurrentUser() == 0){
			addActionError("您还未登录，无法进行操作~！");
			return Action.LOGIN;
		}
	
		Session session = HibernateUtil.getSessionFactory().openSession();	
		try{
			//set all pay values
			Pay pay = (Pay) session.get(Pay.class, payID);
			
			//check if the current user is payuser
			if(SessionUtil.getCurrentUser() != pay.getPayedUserID()){
				addActionError("对不起,您无权修改这个记录");
				return Action.ERROR;
			}
			
			this.setPayType(pay.getPayType());
			this.setPayAmount(pay.getPayAmount());
			this.setPayDate(pay.getPayDate());
			this.setPayDone(pay.getPayDone());
			this.setPayDetail(pay.getPayDetail());
			this.setPayedUserID(pay.getPayedUserID());
			
			return Action.SUCCESS;
			
		}catch(NullPointerException e){
			addActionError("空对象异常发生了，可能是这个记录的信息不完整");
			return Action.SUCCESS;
		
		}catch(Exception e){
			System.err.println(e);
			addActionError("发生错误，请联系管理员");
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
	 * @return the payDone
	 */
	public int getPayDone() {
		return payDone;
	}

	/**
	 * @param payDone the payDone to set
	 */
	public void setPayDone(int payDone) {
		this.payDone = payDone;
	}

	/**
	 * @return the payType
	 */
	public String getPayType() {
		return payType;
	}

	/**
	 * @param payType the payType to set
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}

	/**
	 * @return the payDetail
	 */
	public String getPayDetail() {
		return payDetail;
	}

	/**
	 * @param payDetail the payDetail to set
	 */
	public void setPayDetail(String payDetail) {
		this.payDetail = payDetail;
	}

	/**
	 * @return the payAmount
	 */
	public double getPayAmount() {
		return payAmount;
	}

	/**
	 * @param payAmount the payAmount to set
	 */
	public void setPayAmount(double payAmount) {
		this.payAmount = payAmount;
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

	/**
	 * @return the payDate
	 */
	public String getPayDate() {
		return util.FormatUtil.myFormat().format(payDate);
	}

	/**
	 * @param payDate the payDate to set
	 */
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

}
