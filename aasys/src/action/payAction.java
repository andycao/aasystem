package action;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import util.HibernateUtil;
import util.SessionUtil;
import aasystem.Pay;
import aasystem.User;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class payAction extends ActionSupport {

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

	private List<Integer> aaUser;
	// for aauser, list of userid

	private static final int PAYNOTDONE = 0;

	// payid autocreated
	// paydone is not done which is 0
	// done is 1

	public String addPay() {

		// check loged in
		if (SessionUtil.getCurrentUser() == 0) {
			addActionError("您还未登录，无法进行操作~！");
			return Action.LOGIN;
		}

		// check pay value
		if (payDate == null) {
			addActionError("账单时间不能为空，请重新输入");
			return ERROR;
		}

		Pay pay = new Pay();

		pay.setPayAmount(payAmount);
		pay.setPayDate(payDate);
		pay.setPayDetail(payDetail);
		pay.setPayDone(PAYNOTDONE);// not done by default
		pay.setPayType(payType);
		pay.setPayedUserID(payedUserID);// who has payed the bill

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		try {

			Set<User> payUsers = pay.getUsers();
			for (int userid : aaUser) {
				payUsers.add((User) session.get(User.class, userid));
			}

			Serializable ids = session.save(pay);
			session.getTransaction().commit();// commit

			Pay paytest = (Pay) session.get(Pay.class, ids);
			if (paytest != null && paytest.getPayedUserID() == payedUserID)
				return SUCCESS;
			else {
				addActionError("添加错误请再来一次");
				return ERROR;
			}
		} catch (Exception e) {
			System.err.println(e);
			addActionError("系统错误请联系管理员");
			return ERROR;
		} finally {
			session.close();
		}
	}

	/**
	 * edit the pay, search pay by payid, only creater can edit it
	 * 
	 * @return
	 */
	public String editPay() {
		Session session = HibernateUtil.getSessionFactory().openSession();

		// check loged in
		if (SessionUtil.getCurrentUser() == 0) {
			addActionError("您还未登录，无法进行操作~！");
			return Action.LOGIN;
		}

		try {
			session.beginTransaction();// begin transaction
			// get the pay
			Pay pay = (Pay) session.get(Pay.class, this.payID);

			// check if the current loggedin user is the pay creater
			if (SessionUtil.getCurrentUser() != pay.getPayedUserID()) {
				addActionError("对不起，您没有修改这条信息的权限！~~");
				return Action.ERROR;
			}

			pay.setPayAmount(payAmount);
			pay.setPayDate(payDate);
			pay.setPayDetail(payDetail);
			pay.setPayDone(payDone);
			pay.setPayType(payType);
			pay.setPayedUserID(payedUserID);

			// who need to pay

			session.getTransaction().commit();
			return Action.SUCCESS;
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			addActionError("数据错误，详细内容");
			addActionError(e.getMessage());
			return Action.ERROR;
		} catch (Exception e) {
			addActionError("其他错误，请联系管理员。");
			System.err.println(e);
			return Action.ERROR;
		} finally {
			session.close();
		}
	}

	/**
	 * remove the bill, only creater can remove it payID and payedUserID is
	 * required
	 * 
	 * @return success or error
	 */
	public String removePay() {

		// check loged in
		if (SessionUtil.getCurrentUser() == 0) {
			addActionError("您还未登录，无法进行操作~！");
			return Action.LOGIN;
		}

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		try {
			Pay thepay = (Pay) session.get(Pay.class, payID);
			// is creater
			if (thepay.getPayedUserID() == SessionUtil.getUserObj().getUserID()) {
				System.out
						.println("@@@@@@@@@@@@@@@@@@@@@@" + thepay.getPayDate());
				session.delete(session.get(Pay.class, payID));// remove the pay

				session.getTransaction().commit();// commit

				return Action.SUCCESS;
			} else {
				addActionError("您没有权限移除这条支付记录，用户只能移除自己的支付记录！");
				return Action.LOGIN;
			}
		} catch (HibernateException e) {
			addActionError("数据库异常，请从新登陆，或联系管理员");
			System.err.println(e);
			return Action.LOGIN;
		} catch (Exception e) {
			addActionError("其他异常,请谨慎操作");
			System.err.println(e);
			return Action.ERROR;
		} finally {
			session.close();
		}
	}

	/**
	 * edit the user who need to pay
	 * 
	 * @return success error
	 */
	public String addPayUser() {

		// check loged in
		if (SessionUtil.getCurrentUser() == 0) {
			addActionError("您还未登录，无法进行操作~！");
			return Action.LOGIN;
		}

		return Action.SUCCESS;
	}

	/* gets and sets ***************************************************** */

	/**
	 * @return the payType
	 */
	public String getPayType() {
		return payType;
	}

	/**
	 * @param payType
	 *            the payType to set
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
	 * @param payDetail
	 *            the payDetail to set
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
	 * @param payAmount
	 *            the payAmount to set
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
	 * @param payedUserID
	 *            the payedUserID to set
	 */
	public void setPayedUserID(int payedUserID) {
		this.payedUserID = payedUserID;
	}

	/**
	 * @return the payDate
	 */
	public Date getPayDate() {
		return payDate;
	}

	/**
	 * @param payDate
	 *            the payDate to set
	 */
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	/**
	 * @return the payID
	 */
	public int getPayID() {
		return payID;
	}

	/**
	 * @param payID
	 *            the payID to set
	 */
	public void setPayID(int payID) {
		this.payID = payID;
	}

	/**
	 * @return the aaUser
	 */
	public List<Integer> getAaUser() {
		return aaUser;
	}

	/**
	 * @param aaUser
	 *            the aaUser to set
	 */
	public void setAaUser(List<Integer> aaUser) {
		this.aaUser = aaUser;
	}

	/**
	 * @return the payDone
	 */
	public int getPayDone() {
		return payDone;
	}

	/**
	 * @param payDone
	 *            the payDone to set
	 */
	public void setPayDone(int payDone) {
		this.payDone = payDone;
	}

}
