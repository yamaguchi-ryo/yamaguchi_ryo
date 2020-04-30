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
import chapter7.service.BranchlistService;
import chapter7.service.DivrollistService;
import chapter7.service.UserService;

@WebServlet(urlPatterns = { "/registration" })
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<User> branchList = new BranchlistService().getBranches();
		List<User> divrolList = new DivrollistService().getDivrols();

		request.setAttribute("branchlist", branchList);
		request.setAttribute("divrollist", divrolList);
		request.getRequestDispatcher("registration.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();
		User inputUser = new User();
		HttpSession session = request.getSession();

		if (isValid(request, messages, inputUser) == true) {
			new UserService().register(inputUser);
			response.sendRedirect("./");
		} else {
			session.setAttribute("RegistrationErrorMessages", messages);
			session.setAttribute("inputUser", inputUser);
			response.sendRedirect("registration");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages, User inputUser) {
		String verifyPass = request.getParameter("verifypass");
		inputUser.setLoginId(request.getParameter("loginId"));
		inputUser.setPassword(request.getParameter("password"));
		inputUser.setName(request.getParameter("name"));
		inputUser.setBranchId(Integer.parseInt(request.getParameter("branchId")));
		inputUser.setDivisionRoleId(Integer.parseInt(request.getParameter("divisionRoleId")));

		if (StringUtils.isEmpty(inputUser.getLoginId()) == true) {
			messages.add("ログインIDを入力してください");
		} else {
			if(!inputUser.getLoginId().matches("[0-9a-zA-Z9]{6,20}")) {
				messages.add("ログインIDのフォーマットエラーです:半角英数字6文字以上20文字以下");
			}
		}
		if (StringUtils.isEmpty(inputUser.getName()) == true) {
			messages.add("ユーザー名を入力してください");
		} else {
			if(!inputUser.getName().matches(".{1,10}")) {
				messages.add("ユーザー名のフォーマットエラーです:全角10文字以内");
			}
		}
		if (inputUser.getBranchId() == 0) {
			messages.add("支店名を入力してください");
		}
		if (inputUser.getDivisionRoleId() == 0) {
			messages.add("部署/役職名を入力してください");
		}
		if (inputUser.getPassword().isEmpty()) {
			messages.add("パスワードを入力してください");
		} else {
			if(verifyPass.isEmpty()) {
				messages.add("確認用パスワードを入力してください");
			}
			if(!inputUser.getPassword().matches("[a-zA-Z0-9!-/:-@\\[-`{-~]{6,20}")){
				messages.add("パスワードのフォーマットエラーです:記号を含む全ての半角文字で6文字以上20文字以下");
			}
			if (!inputUser.getPassword().equals(verifyPass)) {
				messages.add("パスワードが一致しません");
			}
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}