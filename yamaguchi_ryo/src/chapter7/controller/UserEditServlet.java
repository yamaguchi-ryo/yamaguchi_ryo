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
		HttpSession session = request.getSession();
		List<User> branchList = new BranchlistService().getBranches();
		List<User> divrolList = new DivrollistService().getDivrols();

		session.setAttribute("branchlist", branchList);
		session.setAttribute("divrollist", divrolList);
		request.setAttribute("edituser", editUser);
		request.getRequestDispatcher("useredit.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();
		User editUser = new User();
		HttpSession session = request.getSession();

		if (isValid(request, messages, editUser) == true) {

			try {
				new UserService().edit(editUser);
			} catch (NoRowsUpdatedRuntimeException e) {
				messages.add("他の人によって更新されています。最新のデータを表示しました。データを確認してください。");
				request.setAttribute("EditErrorMessages", messages);
				request.setAttribute("edituser", editUser);
				request.getRequestDispatcher("useredit.jsp").forward(request, response);
				return;
			}

			response.sendRedirect("./");
		} else {
			session.setAttribute("EditErrorMessages", messages);
			session.setAttribute("edituser", editUser);

			response.sendRedirect("useredit.jsp");
		}
	}


	private boolean isValid(HttpServletRequest request, List<String> messages, User editUser) {
		String verifyPass = request.getParameter("verifypass");
		editUser.setId(Integer.parseInt(request.getParameter("id")));
		editUser.setLoginId(request.getParameter("loginId"));
		editUser.setName(request.getParameter("name"));
		editUser.setBranchId(Integer.parseInt(request.getParameter("branchId")));
		editUser.setDivisionRoleId(Integer.parseInt(request.getParameter("divisionRoleId")));
		editUser.setPassword(request.getParameter("password"));

		if (editUser.getLoginId().isEmpty() == true) {
			messages.add("ログインIDを入力してください");
		} else {
			if(!editUser.getLoginId().matches("[0-9a-zA-Z9]{6,20}")) {
				messages.add("ログインIDのフォーマットエラーです:半角英数字6文字以上20文字以下");
			}
		}
		if (editUser.getName().isEmpty() == true) {
			messages.add("ユーザー名を入力してください");
		} else {
			if(!editUser.getName().matches(".{1,10}")) {
				messages.add("ユーザー名のフォーマットエラーです:全角10文字以内");
			}
		}

		if(!editUser.getPassword().isEmpty()) {
			if(verifyPass.isEmpty()) {
				messages.add("確認用パスワードを入力してください");
			}
			if (!editUser.getPassword().matches("[a-zA-Z0-9!-/:-@\\[-`{-~]{6,20}")) {
				messages.add("パスワードのフォーマットエラーです:記号を含む全ての半角文字で6文字以上20文字以下");
			}
			if(!editUser.getPassword().equals(verifyPass)) {
				messages.add("パスワードが一致しません");
			}
		} else {
			if(!verifyPass.isEmpty()) {
				messages.add("パスワードを入力してください");
			}
		}



		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
