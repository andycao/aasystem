package action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import aasystem.Pay;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import util.HibernateUtil;

public class finishAA extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int PAY_DONE = 1;
	private final int PAY_NOT_DONE = 0;
	public finishAA() {
	}
	
	/**
	 * change all record to paydone
	 */
	public String execute(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Pay where PayDone="+PAY_NOT_DONE);
			@SuppressWarnings("unchecked")
			List<Pay> list = query.list();
			for(Pay pay : list){
				pay.setPayDone(this.PAY_DONE);//set pay done
			}
			transaction.commit();
			return Action.SUCCESS;
		}catch(HibernateException e){
			System.err.println(e);
			addActionError("数据库错误");
			return Action.ERROR;
		}catch(Exception e){
			System.err.println(e);
			addActionError("其他错误，请联系管理员");
			return Action.ERROR;
		}finally{
			session.close();
		}
	}
}
