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
    <title>Tasks</title>
</head>
<body>
    <h1><strong>Edit ${task.content}</strong></h1>
	<form:form action="/tasks/${task.id}/edit" method="post" modelAttribute="task">
    	<input type="hidden" name="_method" value="put">
	    <p>
	        <form:label path="content">Task:</form:label>
	        <form:errors path="content"/>
	        <form:input path="content"/>
	    </p>
	    <p>
	        <form:label path="assignee">Assignee:</form:label>
	        <form:errors path="assignee"/>
	        <form:select path="assignee">
	        	<c:forEach items="${users}" var="user"> 
	        	<form:option value="${user.id}" label="${user.name}"/>
	        	</c:forEach>c:forEach>
	        </form:select>
	    </p>
	    <p>
	        <form:label path="priority">Priority:</form:label>
	        <form:errors path="priority"/>
	        <form:select path="priority">
	        	<form:option value="Low" label="Low"/>
	        	<form:option value="Medium" label="Medium"/>
	        	<form:option value="High" label="High"/>
	        </form:select>
	    </p>
        <form:hidden path="creator" value="${task.creator.id}"/>
	    <input type="submit" value="Edit"/>
	</form:form>
</body>
</html>