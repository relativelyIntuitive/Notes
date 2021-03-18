<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>     
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page isErrorPage="true" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">    
    <title>Delete Account?</title>
</head>
<body>
	<div class="row">
		<div class="col">
			<div class="container">
				<c:choose>
					<c:when test="${userId != null}">
						<a href="/account/${userId}/edit"><c:out value="${user.username}"/></a>
						<br/>
						<a href="/logout">Log out</a>
					</c:when>
					<c:otherwise>
						<a href="/registration">Login/Register</a>
					</c:otherwise>
				</c:choose>
				<br/>		
				<a href="/">Home</a>
			</div>
		</div>
	</div>
	<br/>
	
	<div class="row">
		<div class="col">
			<div class="container">
				<form action="/account/${userId}/delete" method="post">
					<h1>Delete Account</h1>
					<p>Do you really want to delete your account?</p>
					<p>This action can not be undone!</p>
				    <input type="hidden" name="_method" value="delete">
			    	<input type="submit" value="DELETE" class="bth btn-danger">
			    	<a href="/account/${userId}/edit" class="btn btn-success">No!</a>
				</form>
			</div>
		</div>
	</div>
	<br/>
</body>
</html>