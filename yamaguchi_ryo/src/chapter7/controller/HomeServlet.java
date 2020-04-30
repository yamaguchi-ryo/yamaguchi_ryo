package chapter7.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chapter7.beans.User;
import chapter7.exception.NoRowsUpdatedRuntimeException;
import chapter7.service.UserService;
import chapter7.service.UserlistService;

@WebServlet(urlPatterns = { "/index.jsp" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		List<User> userList = new UserlistService().getUsers();
		request.setAttribute("userlist", userList);
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		User status = fillUserStatus(request);

		try {
			new UserService().change(status);
		} catch(NoRowsUpdatedRuntimeException e) {
			request.getRequestDispatcher("./").forward(request, response);
			return;
		}

		List<User> userList = new UserlistService().getUsers();
		request.setAttribute("userlist", userList);
		response.sendRedirect("./");
	}

	private User fillUserStatus(HttpServletRequest request) throws IOException, ServletException {
		User userStatus = new User();
		userStatus.setId(Integer.parseInt(request.getParameter("id")));
		if(request.getParameter("userStopOrActive").equals("活動中")) {
			userStatus.setUserStopOrActive(0);
		} else {
			userStatus.setUserStopOrActive(1);
		}
		return userStatus;
	}
}