<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">    
    <title>View License</title>
</head>
<body>
    <h1>${person.firstName} ${person.lastName}</h1>
    <table class="table">
		<thead>
			<tr>
				<th scope="col">License Number</th>
				<th scope="col">State</th>
				<th scope="col">Expiration Date</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${person.license.number}</td>
				<td>${person.license.state}</td>
				<td>${person.license.expirationDate}</td>
			</tr>
		</tbody>
	</table>
</body>
</html>