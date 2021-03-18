<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>     

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">    
    <title>Ideas</title>
</head>
<body>
	<div class="row">
		<div class="col">
			<div class="container">
			    <h1>Register</h1>
			    <form:form action="/registration" method="post" modelAttribute="user">
				    <p>
				        <form:label path="name">Name:</form:label>
				        <form:errors path="name"/>
				        <form:input path="name"/>
				    </p>
				    <p>
				        <form:label path="email">Email:</form:label>
				        <form:errors path="email"/>
				        <form:input path="email"/>
				    </p>
				    <p>
				        <form:label path="password">Password:</form:label>
				        <form:errors path="password"/>
				        <form:input path="password"/>
				    </p>
				    <p>
				        <form:label path="passwordConfirmation">Password Conf:</form:label>
				        <form:errors path="passwordConfirmation"/>
				        <form:input path="passwordConfirmation"/>
				    </p>
				    <input type="submit" value="Register"/>
				</form:form>	
			</div>
		</div>
	
		<div class="col">
			<div class="container">
		    <h1>Login</h1>
		    <form action="/login" method="post">
			    <p>
			        <label>Email:</label>
			        <input type="text" name="email"/>
			    </p>
			    <p>
			        <label>Password:</label>
			        <input type="text" name="password"/>
			    </p>
			    <input type="submit" value="Login"/>
			</form>	
			</div>
		</div>
	</div>
</body>
</html>