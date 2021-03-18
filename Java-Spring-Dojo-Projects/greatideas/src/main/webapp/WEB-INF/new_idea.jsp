<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>     
<%@ page isErrorPage="true" %>

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
	<div class="col">
		<div class="container">
		    <h1>Create a new idea</h1>
		    <form:form action="/ideas/new" method="post" modelAttribute="idea">
			    <p>
			        <form:label path="content">Content:</form:label>
			        <form:errors path="content"/>
			        <form:input path="content"/>
			    </p>
		        <form:hidden path="user" value="${user.id}"/>
			    <input type="submit" value="Create"/>
			</form:form>	
		</div>
	</div>
</body>
</html>