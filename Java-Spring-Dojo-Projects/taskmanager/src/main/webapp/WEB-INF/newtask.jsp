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
    <title>Tasks</title>
</head>
<body>
	<div class="col">
		<div class="container">
		    <h1>Create a new task</h1>
		    <form:form action="/tasks/new" method="post" modelAttribute="task">
			    <p>
			        <form:label path="content">Content:</form:label>
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
		        <form:hidden path="creator" value="${user.id}"/>
			    <input type="submit" value="Create"/>
			</form:form>	
		</div>
	</div>
</body>
</html>