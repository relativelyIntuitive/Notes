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
    <title>Search</title>
</head>
<body>
	<div class="row">
		<div class="col">
			<div class="container">
				<c:choose>
					<c:when test="${userId != null}">
						<a href="/account/${userId}/edit"><c:out value="${user.username}"/></a>
						<br/>
						<a href="/logout">Log out</a>
					</c:when>
					<c:otherwise>
						<a href="/registration">Login/Register</a>
					</c:otherwise>
				</c:choose>
				<br/>		
				<a href="/">Home</a>
			</div>
		</div>
	</div>
	<br/>
	
	<div class="row">
		<div class="col">
			<div class="container">
			    <h1>Search!</h1>
			    <p class="text-danger"><c:out value="${error}"/></p>
			    <p class="text-danger"><c:out value="${success}"/></p>
			    <form action="/search" method="post">
				    <p>
				        <label>Query:</label>
				        <input type="text" name="query"/>
				    </p>
				    <input type="submit" value="Search"/>
				</form>	
			</div>
		</div>
	</div>
	<br/>
	
	<div class="row">
		<div class="col">
			<div class="container">			
				<h1>Results for: "${queryCap}"</h1>
				<c:choose>
					<c:when test="${!mwDictResults.get(1).containsKey('isOffensive')}">
						<h5>Error retrieving isOffensive data!</h5>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${mwDictResults.get(0).get('isOffensive').equals('is considered offensive!')}">
								<h5 class="text-danger"><c:out value='"${queryCap}" ${mwDictResults.get("isOffensive")}'/></h5>
							</c:when>
							<c:otherwise>
								<h5 class="text-success"><c:out value="${queryCap} ${mwDictResults.get('isOffensive')}"/></h5>								
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
				<h3>Merriam-Webster results:</h3>
				<c:choose>
					<c:when test="${!mwDictResults.get(0).containsKey('shortDefs')}">
						<h5>Error retrieving Merriam-Webster definitions!</h5>
					</c:when>
					<c:otherwise>
						<h5>Merriam-Webster definitions:</h5>
						<ol>
							<c:forEach var="entry" items="${mwDictResults}">
								<!-- try without pre??? -->
								<li><pre><c:out value="${entry}"/></pre></li>
							</c:forEach>
						</ol>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	<br/>

	<div class="row">
		<div class="col">
			<div class="container">			
				<h3>Comments:</h3>
				<c:choose>
					<c:when test="${comments.size() > 0}">
						<ul>
							<forEach var="comment" items="${comments}"> 
								<li>${comment}</li>
							</forEach>
						</ul>
					</c:when>
					<c:otherwise>
						<p>No comments for "${query}" yet! Be the first!</p>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	<br/>
</body>
</html>