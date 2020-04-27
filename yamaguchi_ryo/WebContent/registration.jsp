<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー登録</title>
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div class="header">
		<h3>ユーザー登録</h3>
	</div>
	<div class="main-form">
		<c:if test="${ not empty errorMessages }">
			<div class="errorMessages">
				<ul>
					<c:forEach items="${errorMessages}" var="message">
						<li><c:out value="${message}" />
					</c:forEach>
				</ul>
			</div>
			<c:remove var="errorMessages" scope="session" />
		</c:if>
		<div class="cp_iptxt">
			<form action="registration" method="post">
				<!-- プルダウンに -->
				<div class="cp_ipselect cp_sl01">
					<label>支店名</label>
					<select name="branchId" id="branchId">
						<option value="" hidden>--------</option>
						<c:forEach items="${branchlist}" var="branchname">
							<c:choose>
								<c:when test="${branchname.branchId==inputUser.branchId}">
									<option value="${branchname.branchId}" selected>${branchname.branchName}</option>
								</c:when>
								<c:otherwise>
									<option value="${branchname.branchId}">${branchname.branchName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
				<div class="cp_ipselect cp_sl01">
					<label>役職/所属名</label> <select name="divisionRoleId" id="divisionRoleId">
						<option value="" hidden>--------</option>
						<c:forEach items="${divrollist}" var="divrolname">
							<c:choose>
								<c:when test="${divrolname.divisionRoleId==inputUser.divisionRoleId}">
									<option value="${divrolname.divisionRoleId}" selected>${divrolname.divisionRoleName}</option>
								</c:when>
								<c:otherwise>
									<option value="${divrolname.divisionRoleId}">${divrolname.divisionRoleName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
				<label>ログインID</label>
					<input type="text" name="loginId" id="loginId" value="${inputUser.loginId}" placeholder="半角英数字で6文字以上20文字以下" required /><br />
				<label>氏名</label>
					<input type="text" name="name" id="name" value="${inputUser.name}" placeholder="全角10文字以内" required/>
				<label>パスワード</label>
					<input name="password" type="password" id="password" placeholder="半角英数字記号可で6文字以上20文字以下" required/><br />
				<label>パスワード(確認用)</label>
					<input name="verifypass" type="password" id="verifypass" placeholder="コピペはしないでください" required/> <br /> <br />
				<input type="submit" value="登録" />
				<a href="./" class="btn-square">戻る</a>
			</form>
		</div>
	</div>
</body>
</html>