<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>     

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">    
    <title>New Product</title>
</head>
<body>
    <h1>New Product</h1>
    <form:form action="/products/new" method="post" modelAttribute="product">
	    <p>
	        <form:label path="">Name:</form:label>
	        <form:errors path="name"/>
            <form:input path="name"/>
	    </p>
	    <p>
	        <form:label path="description">Description:</form:label>
	        <form:errors path="description"/>
            <form:input path="description"/>
	    </p>
	    <p>
	        <form:label path="price">Price:</form:label>
	        <form:errors path="price"/>
	        <form:input path="price"/>
	    </p>
	    <input type="submit" value="Create"/>
	</form:form>	
</body>
</html>