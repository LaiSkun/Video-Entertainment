package com.poly.servlet.user;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poly.common.PageInfo;
import com.poly.common.PageType;
import com.poly.common.SessionUtils;
import com.poly.dao.FavoriteDao;
import com.poly.entity.Favorite;
import com.poly.entity.User;
import com.poly.entity.Video;

/**
 * Servlet implementation class LikeVideoServlet
 */
@WebServlet("/LikeVideo")
public class LikeVideoServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!SessionUtils.isLogin(request)) {
			response.sendRedirect("Login");
			return;
		}
		String page = request.getParameter("page");
		String videoId = request.getParameter("videoId");
		
		if(videoId == null) {
			response.sendRedirect("/Homepage");
			return;
		}
		try {
			FavoriteDao dao = new FavoriteDao();
			Favorite fav = new Favorite();
			Video video = new Video();
			video.setVideoId(videoId);
			fav.setVideo(video);
			
			String username = SessionUtils.getLoginUsername(request);
			User user = new User();
			user.setUsername(username);
			fav.setUser(user);
			fav.setLikeDate(new Date());
			
			dao.insert(fav);
			request.setAttribute("message", "Video is added to Favorite");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", e.getMessage());
		}
		if(page == null) {
			page= "/Homepage";
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
