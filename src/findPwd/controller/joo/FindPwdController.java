package findPwd.controller.joo;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import findPwd.dao.joo.FindPwdDao;


@WebServlet("/findPwd")
public class FindPwdController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getContextPath()+"/findPwd/findPwd.jsp");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("id");
		String email=req.getParameter("email");
		int n =FindPwdDao.getDao().searchPwd(id, email);
		if(n>0) {
			String temPwd=FindPwdDao.getDao().getTemPwd();
			FindPwdDao.getDao().updateTemPwd(id, temPwd);

			String host="smtp.naver.com";
			String user="suizka81@naver.com";
			String password="wnGUSwn12!";

			Properties props=new Properties();
			props.put("mail.smtp.host",host);
			props.put("mail.smtp.port",587);
			props.put("mail.smtp.auth",true);

			Session session=Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user, password);
				}
			});

			try{
				MimeMessage message= new MimeMessage(session);
				message.setFrom(new InternetAddress(user));
				message.addRecipient(Message.RecipientType.TO,new InternetAddress(email));
				message.setSubject("임시비밀번호입니다.");
				message.setText(temPwd);
				Transport.send(message);

			}catch(MessagingException me) {
				me.printStackTrace();
			}
			req.setAttribute("id", email);
			req.setAttribute("code", "success");
			req.getRequestDispatcher("/findPwd/result.jsp").forward(req, resp);
		}else {
			req.setAttribute("code", "fail");
			req.getRequestDispatcher("/findPwd/result.jsp").forward(req, resp);
		}

	}
}
