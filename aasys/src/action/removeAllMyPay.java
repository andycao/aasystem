package action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import util.SessionUtil;
import aasystem.Pay;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class removeAllMyPay extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public removeAllMyPay() {
	}

	public String execute() {
		//get user id
		int user = SessionUtil.getCurrentUser();
		
		// check loged in
		if (user == 0) {
			addActionError("您还未登录，无法进行操作~！");
			return Action.LOGIN;
		}

		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			
			//get pays
			Query query = session.createQuery("select payID from Pay where UserID =:userid");
			query.setInteger("userid", user);
			@SuppressWarnings("unchecked")
			List<Integer> list = query.list();
			// remove pays
			for (int payid : list) {
				session.delete(session.get(Pay.class, payid));
			}
			session.getTransaction().commit();
			return Action.SUCCESS;
		} catch (HibernateException e) {
			addActionError("数据库异常，请从新登陆，或联系管理员");
			System.err.println(e);
			return Action.ERROR;
		} catch (Exception e) {
			addActionError("其他错误，请联系管理员");
			System.err.println(e);
			return Action.ERROR;
		} finally {
			session.close();
		}
	}
}
