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
    <title>Login/Register</title>
</head>
<body>
	<div class="row">
		<div class="col">
			<div class="container">
				<a href="/">Home</a>				
			</div>
		</div>
	</div>
	<br/>
	
	<div class="row">
		<div class="col">
			<div class="container">
			    <h1>Login</h1>
			    <p class="text-danger"><c:out value="${error}"/></p>
			    <p class="text-danger"><c:out value="${success}"/></p>
			    <form action="/login" method="post">
				    <p>
				        <label>Email:</label>
				        <input type="text" name="email"/>
				    </p>
				    <p>
				        <label>Password:</label>
				        <input type="password" name="password"/>
				    </p>
				    <input type="submit" value="Login"/>
				</form>	
			</div>
		</div>
	</div>
	<br/>
	
	<div class="row">
		<div class="col">
			<div class="container">
			    <h1>Register</h1>
		        <p><strong>&emsp;*Username is permanent!</strong></p>
			    <form:form action="/register" method="post" modelAttribute="newUser">
				    <p>
				        <form:label path="username">Username:</form:label>
				        <form:input path="username"/>
				    </p>
				    <p>
				        <form:label path="email">Email:</form:label>
				        <form:input path="email"/>
				    </p>
				    <p>
				        <form:label path="password">Password:</form:label>
				        <form:input path="password" type="password"/>
				    </p>
				    <p>
				        <form:label path="passwordConfirmation">Confirm Password:</form:label>
				        <form:input path="passwordConfirmation" type="password"/>
				    </p>
			        <form:errors path="username" element="p" class="text-danger"/>
			        <form:errors path="email" element="p" class="text-danger"/>
			        <form:errors path="password" element="p" class="text-danger"/>
			        <form:errors path="passwordConfirmation" element="p" class="text-danger"/>
				    <input type="submit" value="Register"/>
				</form:form>	
			</div>
		</div>
	</div>
	<br/>
</body>
</html>