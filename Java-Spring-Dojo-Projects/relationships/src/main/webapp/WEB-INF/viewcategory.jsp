<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>     

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">    
    <title>View Category</title>
</head>
<body>
    <h1>${category.name}</h1>
    <div class="row">
    	<div class="col">
    		<div class="container">
	    		<p>Products:</p>
	    		<ul>
					<c:forEach items="${category.products}" var="product">
	    				<li>${product.name}</li>
	    			</c:forEach>
	    		</ul>
    		</div>
    	</div>
    	<div class="col">
    		<div class="container">
   			    <form action="/categories/${category.id}/add/product" method="post">
					<p>
						<label>Add Product</label>
						<select name="newId">
							<c:forEach items="${products}" var="product">
								<option value="${product.id}">${product.name}</option>
							</c:forEach>
					</p>
				    <input type="submit" value="Add"/>
		    	</form>	
    		</div>
    	</div>
    </div>
</body>
</html>