package com.poly.servlet.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poly.common.EmailUtils;
import com.poly.common.PageInfo;
import com.poly.common.PageType;
import com.poly.dao.UserDao;
import com.poly.domain.Email;
import com.poly.entity.User;

/**
 * Servlet implementation class ForgotPasswordServlet
 */
@WebServlet("/ForgotPassword")
public class ForgotPasswordServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_FORGOT_PASSWORD_PAGE);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String emailAddress = request.getParameter("email");
			String username = request.getParameter("username");
			
			UserDao dao = new UserDao();
			User user = dao.findByUserIdAndEmail(username, emailAddress);
			
			if(user == null) {
				request.setAttribute("error", "Username or email are incorrect");
			}else {
				Email email = new Email();
				email.setFrom("truongdqps19980@fpt.edu.vn");
				email.setFromPassword("Takeeru230220");
				email.setTo(emailAddress);
				email.setSubject("Forgot Password Function !!");
				StringBuilder sb = new StringBuilder();
				sb.append("Dear ").append(username).append("<br>");
				sb.append("You are used the forgot password function. <br> ");
				sb.append("Your password is <b>" ).append(user.getPassword()).append("</b>");
				sb.append("Regared <br>");
				sb.append("Administrator");
				
				email.setContent(sb.toString());
				EmailUtils.send(email);
				request.setAttribute("message", "Email sent to the email Address. Please check and get your password");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_FORGOT_PASSWORD_PAGE);
	}

}
