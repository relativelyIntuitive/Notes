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
    <h1><strong>Welcome, ${user.name}</strong></h1>
	<div class="row">
		<div class="col">
			<p><strong>Ideas</strong></p>
		</div>
		<div class="col">
			<a href="/ideas/new">New Idea</a>
		</div>
	</div>
	<div class="container">
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">Idea:</th>
		      <th scope="col">Created By:</th>
		      <th scope="col">Likes</th>
		      <th scope="col">Actions</th>
		    </tr>
		  </thead>
		  <tbody>
		    <c:forEach items="${ideas}" var="idea">
            <tr>
                <td><a href="/ideas/${idea.id}">${idea.content}</a></td>
                <td>${idea.user.name}</td>
                <td>${idea.likes}</td>
                <td>
                	<c:if test = "${idea.likers.contains(user)}">
                    <a href="/ideas/${idea.id}/unlike">Unlike</a>                   	
                    </c:if>
                   	<c:if test = "${!idea.likers.contains(user)}">
                    <a href="/ideas/${idea.id}/like">Like</a>                   	
                    </c:if>
                </td>
            </tr>
            </c:forEach>
		  </tbody>
		</table>
	</div>
</body>
</html>