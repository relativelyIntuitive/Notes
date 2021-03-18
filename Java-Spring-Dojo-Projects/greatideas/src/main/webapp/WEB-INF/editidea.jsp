<%@ page isErrorPage="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>     
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


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
    <h1><strong>${idea.content}</strong></h1>
	<form:form action="/ideas/${idea.id}/edit" method="post" modelAttribute="idea">
    	<input type="hidden" name="_method" value="put">
	    <p>
	        <form:label path="content">Content:</form:label>
	        <form:errors path="content"/>
	        <form:input path="content"/>
	    </p>
      <!--!  <form:hidden path="id" value="${idea.id}"/> -->
        <form:hidden path="user" value="${idea.user.id}"/>
	    <input type="submit" value="Update"/>
	</form:form>
	<form action="/ideas/${idea.id}/delete" method="post">
	    <input type="hidden" name="_method" value="delete">
        <input type="submit" value="Delete">
    </form>
</body>
</html>