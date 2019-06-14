<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ResourceBundle"%>
<html>

<head>
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Login</title>
</head>

<body>

	<form action="login" method="POST">
		<div>
			<c:if test="${param.error}">
				<div>
					<div>Invalid Username or Password</div>
				</div>
			</c:if>
			<div >
				<input name="username" type="text" placeholder="Username">
			</div>
			<div>
				<input name="password" type="password" placeholder="Password"> 
			</div>
			<div>
				<button type="submit">Log In</button>
			</div>
		</div>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
</body>
</html>