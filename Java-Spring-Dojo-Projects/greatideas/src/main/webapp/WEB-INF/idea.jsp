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
	<div class="row">
		<div class="col">
			<p>Created By:</p>
		</div>
		<div class="col">
			<p>${idea.user.name}</p>
		</div>
	</div>
	<div class="row">
		<div class="col">
			<p><strong>Users who liked your idea:</strong></p>
		</div>
	</div>
	<div class="row">
		<div class="container">
		    <ul>
			    <c:forEach items="${idea.likers}" var="liker">
	            <li>${liker.name}</li>
	            </c:forEach>
	        </ul>
		</div>
	</div>
	<div class="row">
		<div class="col">
			<a href="/ideas/${idea.id}/edit">Edit</a>
		</div>
	</div>
</body>
</html>