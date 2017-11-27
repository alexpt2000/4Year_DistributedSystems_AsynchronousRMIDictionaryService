<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>Dictionary Service</h1>

	<form method="post" action="getDefinition">
		<table>
			<tr>
				<td>
					<input type="text" name="txtTitle" placeholder="Enter query here"> 
					<input type="submit" value="Submit">
				</td>
			</tr>

		</table>
	</form>
	
	<div>
	<input type="button" value="Add" onclick="window.location='addDictionary.jsp'">
	</div>
	


</body>
</html>