<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>     

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">    
    <title>View Product</title>
</head>
<body>
    <h1>${product.name}</h1>
    <div class="row">
    	<div class="col">
    		<div class="container">
	    		<p>Categories:</p>
	    		<ul>
					<c:forEach items="${product.categories}" var="category">
	    				<li>${category.name}</li>
	    			</c:forEach>
	    		</ul>
    		</div>
    	</div>
    	<div class="col">
    		<div class="container">
   			    <form action="/products/${product.id}/add/category" method="post">
					<p>
						<label>Add Category</label>
						<select name="newId">
							<c:forEach items="${categories}" var="category">
								<option value="${category.id}">${category.name}</option>
							</c:forEach>
						</select>
					</p>
				    <input type="submit" value="Add"/>
		    	</form>	
    		</div>
    	</div>
    </div>
</body>
</html>