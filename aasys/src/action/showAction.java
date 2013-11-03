/**
 * 
 */
package action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import aasystem.Pay;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author andyc
 *
 */
public class showAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Pay> showList;
	public showAction() {
	}

	/**
	 * get all pays list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getAllPayListDescTime(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Query query = session.createQuery("from Pay order by PayDate DESC");
			setShowList(query.list());
			if(showList.size() > 0)
				return Action.SUCCESS;
			else 
				return Action.ERROR;
		}catch(HibernateException e){
			System.err.println(e);
			return Action.ERROR;
		}catch(Exception e){
			System.err.println(e);
			return Action.ERROR;
		}finally{
			session.close();
		}
	}
	/**
	 * get the pays which are not done list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String allPayNotDoneListDescTime(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			//0 means pay not done
			Query query = session.createQuery("from Pay where PayDone=0 order by PayDate DESC");
			setShowList(query.list());
			if(showList.size() > 0)
				return Action.SUCCESS;
			else 
				return Action.ERROR;
		}catch(HibernateException e){
			System.err.println(e);
			return Action.ERROR;
		}catch(Exception e){
			System.err.println(e);
			return Action.ERROR;
		}finally{
			session.close();
		}
	}

	/**
	 * @return the showList
	 */
	public List<Pay> getShowList() {
		return showList;
	}

	/**
	 * @param showList the showList to set
	 */
	public void setShowList(List<Pay> showList) {
		this.showList = showList;
	}
}
