package com.poly.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poly.common.PageInfo;
import com.poly.common.PageType;
import com.poly.dao.FavoriteDao;
import com.poly.dao.VideoDao;
import com.poly.domain.FavoriteReport;
import com.poly.domain.FavoriteUserReport;
import com.poly.entity.Video;

/**
 * Servlet implementation class ReportsManagementServlet
 */
@WebServlet("/ReportsManagement")
public class ReportsManagementServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		reportFavoriteByVideos(request, response);
		reportFavoriteUsersByVideos(request, response);
		PageInfo.prepareAndForward(request, response, PageType.REPORT_MANAGEMENT_PAGE);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	protected void reportFavoriteByVideos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			FavoriteDao dao =new FavoriteDao();
			List<FavoriteReport> list = dao.reportFavoriteByVideo();
			
			request.setAttribute("favList", list);
		} catch (Exception e) {
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}
	protected void reportFavoriteUsersByVideos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String videoUserId = request.getParameter("videoUserId");
			
			VideoDao vdao = new VideoDao();
			List<Video> vList = vdao.findAll(); 
			
			if(videoUserId == null && vList.size() >0) {
				videoUserId = vList.get(0).getVideoId();
			}
			FavoriteDao fdao =new FavoriteDao();
			List<FavoriteUserReport> list = fdao.reportFavoriteUsersByVideo(videoUserId);
			
			request.setAttribute("videoUserId", videoUserId);
			request.setAttribute("vidList", vList);
			request.setAttribute("favUser", list);
		} catch (Exception e) {
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}
}
