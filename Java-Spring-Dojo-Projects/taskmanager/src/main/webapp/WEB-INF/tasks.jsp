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
    <h1><strong>Welcome, ${user.name}</strong></h1>
    <p class="text-danger"><c:out value="${error}"/></p>
	<div class="row">
		<div class="col">
			<p><strong>Tasks</strong></p>
		</div>
		<div class="col">
			<a href="/logout">Log out</a>
		</div>
	</div>
	<div class="container">
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">Task:</th>
		      <th scope="col">Creator:</th>
		      <th scope="col">Assignee:</th>
		      <th scope="col">Priority:</th>
		    </tr>
		  </thead>
		  <tbody>
		    <c:forEach items="${tasks}" var="task">
			    <c:if test="${task.completed == false}">
		            <tr>
		                <td><a href="/tasks/${task.id}">${task.content}</a></td>
		                <td>${task.creator.name}</td>
		                <td>${task.assignee.name}</td>
		                <td>${task.priority}</td>
		            </tr>
	            </c:if>
            </c:forEach>
		  </tbody>
		</table>
	</div>
	<a href="/tasks/new">Create task</a>
</body>
</html>