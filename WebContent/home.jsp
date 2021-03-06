<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー管理システム</title>
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div class="header">
		<h3>ユーザー管理システム</h3>
	</div>
	<div class="menu">
	<c:remove var="inputUser" scope="session" />
	<c:remove var="editUser" scope="session" />
		<ul>
			<li><a href="registration">ユーザー登録</a>
		</ul>
	</div>
	<div class="main">
		<table class="itemname">
			<caption>ユーザー一覧</caption>
			<tr class="thead">
				<td style="width: 16%;">ユーザーID</td>
				<td style="width: 20%;">名 前</td>
				<td style="width: 9%;">支店番号</td>
				<td style="width: 11;">所属/役職</td>
				<td style="width: 16%;">登録日</td>
				<td style="width: 16%;">更新日</td>
				<td style="width: 12%;">状態</td>
			</tr>
		</table>
		<div class="scroll">
			<table>
				<c:forEach items="${userlist}" var="user">
					<tr class="users">
						<td style="width: 16%;"><a href="useredit?id=${user.id}"><c:out
									value="${user.loginId}" /></a></td>
						<td style="width: 20%;"><c:out value="${user.name}" /></td>
						<td style="width: 9%;"><c:out value="${user.branchName}" /></td>
						<td style="width: 11%;"><c:out value="${user.divisionRoleName}" /></td>
						<td style="width: 16%;"><fmt:formatDate value="${user.createdDate}"
								pattern="yyyy/MM/dd" /></td>
						<td style="width: 16%;"><fmt:formatDate value="${user.updatedDate}"
								pattern="yyyy/MM/dd" /></td>
						<td style="width: 12%;">
							<form action="index.jsp" method="post" onSubmit="return check()">
								<input name="id" value="${user.id}" id="id" type="hidden" />
<<<<<<< HEAD
								<input type="hidden" name="changeinto"
									id="changeinto"
=======
<<<<<<< HEAD:WebContent/home.jsp
								<input type="hidden" name="changeinto"
									id="changeinto"
=======
								<input type="submit" name="userStopOrActive"
									id="userStopOrActive"
>>>>>>> 8def25b8610c9bc97b1b9c7df76ee2f15dea78d9:yamaguchi_ryo/WebContent/home.jsp
>>>>>>> 81ea4bbb504106228511d6e2ef54a2c47c9a0163
									<c:choose>
										<c:when test="${user.userStopOrActive==0}">
											value="1"
										</c:when>
										<c:otherwise>
											value="0"
										</c:otherwise>
									</c:choose>>
								<input type="submit" name="status"
									id="status"
									<c:choose>
										<c:when test="${user.userStopOrActive==1}">
											value="停止"
										</c:when>
										<c:otherwise>
											value="復活"
										</c:otherwise>
									</c:choose>>
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		function check(){
			if(window.confirm('送信してよろしいですか？')){
				return true;
			}
			else{
				return false;
			}
		}
	</script>
</body>
</html>