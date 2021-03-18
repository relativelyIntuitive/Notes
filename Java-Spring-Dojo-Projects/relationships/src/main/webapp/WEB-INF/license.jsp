<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>     

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">    
    <title>New License</title>
</head>
<body>
    <h1>New License</h1>
    <form:form action="/licenses/new" method="post" modelAttribute="license">
	    <p>
	        <form:label path="person">Person:</form:label>
	        <form:errors path="person"/>
	        <form:select path="person">
                <c:forEach items="${persons}" var="person">
	        	<form:option value="${person}">${person.firstName} ${person.lastName}</form:option>
                </c:forEach>
            </form:select>
	    </p>
	    <p>
	        <form:label path="state">State:</form:label>
	        <form:errors path="state"/>
            <form:input path="state"/>
	    </p>
	    <p>
	        <form:label path="expirationDate">Expiration Date:</form:label>
	        <form:errors path="expirationDate"/>
	        <form:input type="date" path="expirationDate"/>
	    </p>
	    <input type="submit" value="Create"/>
	</form:form>	
</body>
</html>