<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー編集</title>
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div class="header">
		<h3>ユーザー編集</h3>
	</div>
		<div class="main-form">
		<c:if test="${ not empty EditErrorMessages }">
			<div class="errorMessages">
				<ul>
					<c:forEach items="${EditErrorMessages}" var="message">
						<li><c:out value="${message}" />
					</c:forEach>
				</ul>
			</div>
			<c:remove var="EditErrorMessages" scope="session" />
		</c:if>
		<div class="cp_iptxt">
			<form action="useredit" method="post">
				<input name="id" value="${edituser.id}" id="id" type="hidden" />
				<!-- プルダウンに -->
				<div class="cp_ipselect cp_sl01">
					<label>支店名</label>
					<select name="branchId" id="branchId" required>
						<option value="" hidden>--------</option>
						<c:forEach items="${branchlist}" var="branchname">
							<c:choose>
								<c:when  test="${branchname.branchId==edituser.branchId}">
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
					<label>役職/所属名</label> <select name="divisionRoleId" id="divisionRoleId" required>
						<option value="" hidden>--------</option>
						<c:forEach items="${divrollist}" var="divrolname">
							<c:choose>
								<c:when test="${divrolname.divisionRoleId==edituser.divisionRoleId}">
									<option value="${divrolname.divisionRoleId}" selected>${divrolname.divisionRoleName}</option>
								</c:when>
								<c:otherwise>
									<option value="${divrolname.divisionRoleId}">${divrolname.divisionRoleName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
				<label>ログインID (半角英数字で6文字以上20文字以下)</label>
					<input type="text" name="loginId" id="loginId" value="${edituser.loginId}" /><br />
				<label>氏名 (全角10文字以内)</label>
					<input type="text" name="name" id="name" value="${edituser.name}" />
				<label>パスワード (半角英数字記号可で6文字以上20文字以下)</label>
					<input name="password" type="password" id="password" /><br />
				<label>パスワード(確認用)</label>
					<input name="verifypass" type="password" id="verifypass" placeholder="コピペはしないでください" /> <br /> <br />
				<input type="submit" value="変更" />
				<input type="reset" value="リセット" />
				<a href="./" class="btn-square">戻る</a>
			</form>
		</div>
	</div>
</body>
</html>