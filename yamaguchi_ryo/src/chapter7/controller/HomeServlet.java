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
		//userListに対応した支店名と所属/役職名の紐づけ


		request.setAttribute("userlist", userList);

		request.getRequestDispatcher("/home.jsp").forward(request, response);

	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

//		HttpSession session = request.getSession();
		User userStatus = getUserStatus(request);

		try {
			new UserService().change(userStatus);
		} catch(NoRowsUpdatedRuntimeException e) {
//			messages.add("他の人によって更新されています。最新のデータを表示しました。データを確認してください。");
//			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("useredit.jsp").forward(request, response);
			return;
		}

		List<User> userList = new UserlistService().getUsers();

		request.setAttribute("userlist", userList);

		request.getRequestDispatcher("/home.jsp").forward(request, response);

	}

	private User getUserStatus(HttpServletRequest request) throws IOException, ServletException {
		User userStatus = new User();
		userStatus.setId(Integer.parseInt(request.getParameter("id")));
		userStatus.setUserStopOrActive(request.getParameter("userStopOrActive"));
		return userStatus;
	}
}