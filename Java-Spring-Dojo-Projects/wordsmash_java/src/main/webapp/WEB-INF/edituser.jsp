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
    <title>Edit Account</title>
</head>
<body>
	<div class="row">
		<div class="col">
			<div class="container">
				<a href="/account/${userId}/edit"><c:out value="${user.username}"/></a>
				<br/>
				<a href="/logout">Log out</a>	
				<br/>
				<a href="/">Home</a>	
			</div>
		</div>
	</div>
	<br/>
	
	<div class="row">
		<div class="col">
			<div class="container">
			    <h1>Edit login for: ${user.username}</h1>
  			    <p class="text-danger"><c:out value="${error}"/></p>		
   			    <p class="text-danger"><c:out value="${success}"/></p>
				<form:form action="/account/${userId}/edit" method="post" modelAttribute="user">
			    	<input type="hidden" name="_method" value="put">
				    <p>
				        <form:label path="email">Email:</form:label>
				        <form:input path="email"/>
				    </p>
			        <form:hidden path="username" value="${user.getUsername()}"/>
			        <form:hidden path="favorites" value="${user.getFavorites()}"/>
			        <form:hidden path="password" value="${user.getPassword()}"/>
			        <form:hidden path="passwordConfirmation" value="${user.getPassword()}"/>
			        <!--<form:hidden path="comments" value="${user.getComments()}"/>
			        <form:hidden path="liked" value="${user.getLiked()}"/>--->
			        <form:errors path="email" element="p" class="text-danger"/>
				    <input type="submit" value="Update"/>
				</form:form>			
			</div>
		</div>
	</div>
	<br/>
	
	<div class="row">
		<div class="col">
			<div class="container">
				<form:form action="/account/${userId}/edit" method="post" modelAttribute="user">
			    	<input type="hidden" name="_method" value="put">
				    <p>
				        <form:label path="password">New Password:</form:label>
				        <input name="password" type="password"/>
				    </p>
				    <p>
				        <form:label path="passwordConfirmation">Confirm New Password:</form:label>
				        <input name="passwordConfirmation" type="password"/>
				    </p>
			        <form:hidden path="username" value="${user.username}"/>
			        <form:hidden path="favorites" value="${user.favorites}"/>
			        <form:hidden path="email" value="${currentEmail}"/>
			        <!--<form:hidden path="comments" value="${user.getComments()}"/>
			        <form:hidden path="liked" value="${user.getLiked()}"/>--->
			        <form:errors path="password" element="p" class="text-danger"/>
			        <form:errors path="passwordConfirmation" element="p" class="text-danger"/>
			    	<input type="submit" value="Update"/>
				</form:form>
			</div>
		</div>
	</div>
	<br/>
	
	<div class="row">
		<div class="col">
			<div class="container">
				<form action="/account/${userId}/delete" method="post">
			    	<input type="submit" value="Delete Account">
				</form>
			</div>
		</div>
	</div>
	<br/>
</body>
</html>