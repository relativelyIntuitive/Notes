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
    <h1><strong>Task: ${task.content}</strong></h1>
	<div class="row">
		<div class="col">
			<p>Created By:</p>
		</div>
		<div class="col">
			<p>${task.creator.name}</p>
		</div>
	</div>
	<div class="row">
		<div class="col">
			<p>Assignee:</p>
		</div>
		<div class="col">
			<p>${task.assignee.name}</p>
		</div>
	</div>
	<div class="row">
		<div class="col">
			<p>Priority:</p>
		</div>
		<div class="col">
			<p>${task.priority}</p>
		</div>
	</div>

	<c:if test="${userId == task.creator.id}">
		<div class="row">
			<div class="col">
				<a href="/tasks/${task.id}/edit">Edit</a>
			</div>
			<div class="col">
				<form action="/tasks/${task.id}/delete" method="post">
				    <input type="hidden" name="_method" value="delete">
			        <input type="submit" value="Delete">
	    		</form>
	    	</div>
		</div>
	</c:if>
	<c:if test="${userId == task.assignee.id}">
		<div class="row">
			<div class="col">
				<form:form action="/tasks/${task.id}/complete" method="post" modelAttribute="task">
			    	<input type="hidden" name="_method" value="put">
			        <form:hidden path="completed" value="${true}"/>
			        <form:hidden path="priority" value="${task.priority}"/>
			        <form:hidden path="content" value="${task.content}"/>
			        <form:hidden path="creator" value="${task.creator.id}"/>
			        <form:hidden path="assignee" value="${task.assignee.id}"/>
				    <input type="submit" value="Complete"/>
				</form:form>
			</div>
		</div>
	</c:if>
</body>
</html>