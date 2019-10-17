package sessionListener.joo;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.apache.catalina.SessionEvent;

public class SessionIdListener implements HttpSessionAttributeListener {
	public SessionIdListener() {}
	String id;
	private static ArrayList<String> IdList=new ArrayList<String>();
	public static ArrayList<String> getUserId() {
		return IdList;
	}
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		HttpSession session=event.getSession();
	    id=(String)session.getAttribute("id");
		IdList.add(id);
	}
	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		HttpSession session=event.getSession();
		//id=(String)session.getAttribute("id");
		IdList.remove(id);
	}
	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		
		
	}
}
