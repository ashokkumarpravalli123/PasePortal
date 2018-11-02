<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link href="./images/favicon.ico" type="image/x-icon" rel="icon" />


<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
	integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
	crossorigin="anonymous"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
	

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Portal</title>
<style type="text/css">

/* Style inputs with type="text", select elements and textareas */
input[type=text], select, textarea {
	width: 100%; /* Full width */
	padding: 12px; /* Some padding */
	border: 1px solid #ccc; /* Gray border */
	border-radius: 4px; /* Rounded borders */
	box-sizing: border-box;
	/* Make sure that padding and width stays in place */
	margin-top: 6px; /* Add a top margin */
	margin-bottom: 16px; /* Bottom margin */
	resize: vertical
		/* Allow the user to vertically resize the textarea (not horizontally) */
}

/* Style the submit button with a specific background color etc */
input[type=submit] {
	background-color: #4CAF50;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

/* When moving the mouse over the submit button, add a darker green color */
input[type=submit]:hover {
	background-color: #45a049;
}

/* Add a background color and some padding around the form */
.container {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
	width: 440px;
	margin: 0 auto;
}
</style>
</head>
<body>
	<div class="container">
		<form method="post" action="peds" commandName="Patent" enctype="multipart/form-data">
		    <label name="references">Enter company domain/url</label>
			<%-- <textarea id="sReferences" name="patentNumbers" placeholder="Patent Numbers.." style="height: 100px" required></textarea>
			<center style="color:#FF0000;font-size:20px;font-style:italic">OR</center>
			 <label name="references">Enter Application Number(s)</label>
			<textarea id="sReferences" name="patentNumbers" placeholder="Application Numbers.." style="height: 100px" required></textarea> --%>
			<input type="text" name="url" placeholder="Company domain/url.." style="height: 50px" required/>
			<input type="submit" value="Submit">
		</form>
		<h2 style="color: #45a049;">
			<b>ACP:</b>
		</h2>
		<div id="accordion1">
			<h3>
				<b>1. How would you rate our overall Office infrastructure?</b>
			</h3>
			<div>
				<table>
					<c:forEach var="acpList" items="${bulkSurveyFeedModel.acpList}">
						<tr>
							<td height="50">&#9632; ${acpList.infrastructure}</td>
						</tr>
						<tr>
							<td height="50"><b>(Additional Feedback)</b></td>
						</tr>
						<tr>
							<c:if
								test="${acpList.infrastructureComments ne null and acpList.infrastructureComments ne ''}">
								<td height="50">${acpList.infrastructureComments}</td>
							</c:if>
						</tr>
					</c:forEach>
				</table>
			</div>
	</div>
</body>
</html>