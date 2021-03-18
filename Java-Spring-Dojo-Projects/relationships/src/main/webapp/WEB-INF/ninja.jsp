<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>     

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">    
    <title>New Ninja</title>
</head>
<body>
    <h1>New Ninja</h1>
    <form:form action="/ninjas/new" method="post" modelAttribute="ninja">
	    <p>
	        <form:label path="dojo">Dojo:</form:label>
	        <form:errors path="dojo"/>
	        <form:select path="dojo">
                <c:forEach items="${dojos}" var="dojo">
	        	<form:option value="${dojo}">${dojo.name}</form:option>
                </c:forEach>
            </form:select>
	    </p>
	    <p>
	        <form:label path="firstName">First Name:</form:label>
	        <form:errors path="firstName"/>
            <form:input path="firstName"/>
	    </p>
	    <p>
	        <form:label path="lastName">Last Name:</form:label>
	        <form:errors path="lastName"/>
            <form:input path="lastName"/>
	    </p>
	    <p>
	        <form:label path="age">Age:</form:label>
	        <form:errors path="age"/>
	        <form:input path="age"/>
	    </p>
	    <input type="submit" value="Create"/>
	</form:form>	
</body>
</html>