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
		<c:if test="${ not empty RegistrationErrorMessages }">
			<div class="errorMessages">
				<ul>
					<c:forEach items="${RegistrationErrorMessages}" var="message">
						<li><c:out value="${message}" />
					</c:forEach>
				</ul>
			</div>
			<c:remove var="RegistrationErrorMessages" scope="session" />
		</c:if>
		<div class="cp_iptxt">
			<form action="registration" method="post" name="regi_form" onSubmit="return canSubmit()">
				<!-- プルダウンに -->
				<div class="cp_ipselect cp_sl01">
					<label>支店名</label> <select name="branchId" id="branchId" required>
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
					<label>役職/所属名</label> <select name="divisionRoleId"
						id="divisionRoleId" required>
						<option value="" hidden>--------</option>
						<c:forEach items="${divrollist}" var="divrolname">
							<c:choose>
								<c:when
									test="${divrolname.divisionRoleId==inputUser.divisionRoleId}">
									<option value="${divrolname.divisionRoleId}" selected>${divrolname.divisionRoleName}</option>
								</c:when>
								<c:otherwise>
									<option value="${divrolname.divisionRoleId}">${divrolname.divisionRoleName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
				<label>ログインID (半角英数字で6文字以上20文字以下)</label> <input type="text"
					name="loginId" id="loginId" value="${inputUser.loginId}" required
<<<<<<< HEAD
					onChange="return inputLoginIdCheck(this.value)" /><br />
				<div class = "error_area" id="id_error" style="display: none;"><p>入力エラーです。</p></div>

				<br /> <label>氏名 (全角10文字以内)</label> <input type="text" name="name"
					id="name" value="${inputUser.name}" required
					onChange="return inputNameCheck(this.value)" />
				<div class = "error_area" id="name_error" style="display: none;"><p>入力エラーです。</p></div>

				<br /> <label>パスワード (半角英数字記号可で6文字以上20文字以下)</label> <input
					name="password" type="password" id="password" required
					onChange="return inputPassCheck(this.value),verifyChecks()" />
				<div class = "error_area" id="password_error" style="display: none;"><p>入力エラーです。</p></div>

				<br /> <label>パスワード(確認用)</label> <input name="verifypass"
					type="password" id="verifypass" placeholder="コピペはしないでください" required onChange="return verifyChecks();" /><br />
				<div class = "error_area" id="verifypass_error" style="display: none;"><p>パスワードが一致しません。</p></div>

=======
					onChange="return inputLoginIdCheck(regi_form.loginId.value)" /><br />
				<p id="id_error" style="display: none;">入力エラーです。</p>
				<br /> <label>氏名 (全角10文字以内)</label> <input type="text" name="name"
					id="name" value="${inputUser.name}" required
					onChange="return inputNameCheck(regi_form.name.value)" />
				<p id="name_error" style="display: none;">入力エラーです。</p>
				<br /> <label>パスワード (半角英数字記号可で6文字以上20文字以下)</label> <input
					name="password" type="password" id="password" required
					onChange="return inputPassCheck(regi_form.password.value),verifyChecks()" />
				<p id="password_error" style="display: none;">入力エラーです。</p>
				<br /> <label>パスワード(確認用)</label> <input name="verifypass"
					type="password" id="verifypass" placeholder="コピペはしないでください" required onChange="return verifyChecks();" /><br />
				<p id="verifypass_error" style="display: none;">パスワードが一致しません。</p>
>>>>>>> 81ea4bbb504106228511d6e2ef54a2c47c9a0163
				<br />
				<br /> <input type="submit" value="登録" /> <a href="./"
					class="btn-square">戻る</a>
			</form>
		</div>
	</div>
	<script type="text/javascript">

		function inputNameCheck(value){
			const namePattern = /^.{1,10}$/g;
<<<<<<< HEAD
			if (value != "" && !value.match(namePattern)) {
=======
			if (value == "") {
				document.getElementById("name_error").style.display = "none";
			} else if (!value.match(namePattern)) {
>>>>>>> 81ea4bbb504106228511d6e2ef54a2c47c9a0163
				document.getElementById("name_error").style.display = "block";
			} else {
				document.getElementById("name_error").style.display = "none";
			}
		}

		function inputLoginIdCheck(value){
			const loginIdPattern = /^[0-9a-zA-Z9]{6,20}$/g;
<<<<<<< HEAD
			if (value != "" && !value.match(loginIdPattern)) {
=======
			if (value == "") {
				document.getElementById("id_error").style.display = "none";
			} else if (!value.match(loginIdPattern)) {
>>>>>>> 81ea4bbb504106228511d6e2ef54a2c47c9a0163
				document.getElementById("id_error").style.display = "block";
			} else {
				document.getElementById("id_error").style.display = "none";
			}
		}

		function inputPassCheck(value){
			const passPattern = /^[a-zA-Z0-9!-/:-@\\[-`{-~]{6,20}$/g;
<<<<<<< HEAD
			if (value != "" && !value.match(passPattern)) {
=======
			if (value == "") {
				document.getElementById("password_error").style.display = "none";
			} else if (!value.match(passPattern)) {
>>>>>>> 81ea4bbb504106228511d6e2ef54a2c47c9a0163
				document.getElementById("password_error").style.display = "block";
			} else {
				document.getElementById("password_error").style.display = "none";
			}
		}

		function verifyChecks() {//本番or確認用が変更されたときに動作する
			//本番or確認用どちらかが空欄だった時はエラーを非表示に
			if(document.getElementById("password").value == ""||document.getElementById("verifypass").value == ""){
				document.getElementById("verifypass_error").style.display = "none";
				return;
			}
			//どちらも埋まっている場合に一致確認
			if (document.getElementById("verifypass").value !== document.getElementById("password").value) {
				document.getElementById("verifypass_error").style.display = "block";
			} else {
				document.getElementById("verifypass_error").style.display = "none";
			}
		}

		function canSubmit(){//submit押下時、エラーが出ていないかの確認を行う。
			if(
				document.getElementById("name_error").style.display == "none" &&
				document.getElementById("id_error").style.display == "none" &&
				document.getElementById("password_error").style.display == "none" &&
				document.getElementById("verifypass_error").style.display == "none"
			) {
				return true;
			} else {
				window.alert("入力にエラーがあります。");
<<<<<<< HEAD
				document.getElementById("name").style.backgroundColor = "red";
				document.getElementById("loginId").style.backgroundColor = "red";
				document.getElementById("password").style.backgroundColor = "red";
				document.getElementById("verifypass").style.backgroundColor = "red";
=======
>>>>>>> 81ea4bbb504106228511d6e2ef54a2c47c9a0163
				return false;
			}
		}
	</script>
</body>
</html>