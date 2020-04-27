package chapter7.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import chapter7.beans.User;
import chapter7.exception.NoRowsUpdatedRuntimeException;
import chapter7.service.BranchlistService;
import chapter7.service.DivrollistService;
import chapter7.service.UserService;


@WebServlet(urlPatterns = { "/useredit" })
public class UserEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		int editId = Integer.parseInt(request.getParameter("id"));

		User editUser = new UserService().getEditUser(editId);
		List<User> branchList = new BranchlistService().getBranches();
		List<User> divrolList = new DivrollistService().getDivrols();

		request.setAttribute("branchlist", branchList);
		request.setAttribute("divrollist", divrolList);
		request.setAttribute("edituser", editUser);

		request.getRequestDispatcher("useredit.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();
		User inputUser = new User();
		User editUser = getEditUser(request);

		if (isValid(request, messages, inputUser) == true) {

			try {
				new UserService().edit(editUser);
			} catch (NoRowsUpdatedRuntimeException e) {
				messages.add("他の人によって更新されています。最新のデータを表示しました。データを確認してください。");
				session.setAttribute("errorMessages", messages);
				request.setAttribute("edituser", editUser);
				request.getRequestDispatcher("useredit.jsp").forward(request, response);
				return;
			}

			response.sendRedirect("./");
		} else {
			session.setAttribute("errorMessages", messages);
			List<User> branchList = new BranchlistService().getBranches();
			List<User> divrolList = new DivrollistService().getDivrols();

			request.setAttribute("branchlist", branchList);
			request.setAttribute("divrollist", divrolList);
			request.setAttribute("edituser", editUser);

			request.getRequestDispatcher("useredit.jsp").forward(request, response);
		}
	}

	private User getEditUser(HttpServletRequest request)
			throws IOException, ServletException {

		User editUser = new User();
		editUser.setId(Integer.parseInt(request.getParameter("id")));
		editUser.setLoginId(request.getParameter("loginId"));
		editUser.setName(request.getParameter("name"));
		editUser.setBranchId(Integer.parseInt(request.getParameter("branchId")));
		editUser.setDivisionRoleId(Integer.parseInt(request.getParameter("divisionRoleId")));
		editUser.setPassword(request.getParameter("password"));
		return editUser;
	}

	private boolean isValid(HttpServletRequest request, List<String> messages, User inputUser) {
		String loginId = request.getParameter("loginId");
		String name = request.getParameter("name");
		String branchId = request.getParameter("branchId");
		String divisionRoleId = request.getParameter("divisionRoleId");
		String password = request.getParameter("password");
		String verifyPass = request.getParameter("verifypass");

		if (StringUtils.isEmpty(loginId) == true) {
			messages.add("ログインIDを入力してください");
		} else {
			if(!loginId.matches("[0-9a-zA-Z9]{6,20}")) {
				messages.add("ログインIDのフォーマットエラーです。");
			} else {
				inputUser.setLoginId(request.getParameter("loginId"));
			}
		}
		if (StringUtils.isEmpty(name) == true) {
			messages.add("ユーザー名を入力してください");
		} else {
			if(!name.matches(".{1,10}")) {
				messages.add("ユーザー名のフォーマットエラーです。");
			} else {
				inputUser.setName(request.getParameter("name"));
			}
		}
		if (StringUtils.isEmpty(branchId) == true) {
			messages.add("支店名を入力してください");
		} else {
			inputUser.setBranchId(Integer.parseInt(request.getParameter("branchId")));
		}
		if (StringUtils.isEmpty(divisionRoleId) == true) {
			messages.add("部署/役職名を入力してください");
		}else {
			inputUser.setDivisionRoleId(Integer.parseInt(request.getParameter("divisionRoleId")));
		}

		if (!password.equals(verifyPass)) {
			messages.add("パスワードが一致しません");
		}

		if (messages.size() == 0) { //登録漏れがない場合リストの中身が空になるということ
			return true;
		} else {
			return false;
		}
	}
}
