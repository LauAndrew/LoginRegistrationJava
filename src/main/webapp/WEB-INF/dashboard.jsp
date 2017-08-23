<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome ${currentUser.firstname}</title>
</head>
<body>
    <h1>Welcome Page <c:out value="${currentUser.firstname}"></c:out></h1>
    
    <p> First Name: <c:out value="${currentUser.firstname}"/></p>
    <p> Last Name: <c:out value="${currentUser.lastname}"/></p>
    <p> Email: <c:out value="${currentUser.username}"/></p>
	<p>Sign up date: <fmt:formatDate pattern="MMMM d, yyyy" value="${currentUser.createdAt}"/></p>
	<p>Last sign in: <fmt:formatDate pattern="MMMM d, yyyy" value="${lastSignIn}"/></p>
    
    
    <form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" />
    </form>


</body>
</html>