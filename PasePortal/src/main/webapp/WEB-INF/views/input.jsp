<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<link href="./images/favicon.ico" type="image/x-icon" rel="icon" />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
	integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
	crossorigin="anonymous"></script>

<!-- <link rel="stylesheet" href="./css/jquery-ui.css">
<script src="./js/jquery-1.12.4.js"></script>
<script src="./js/jquery-ui.js"></script> -->

<script
	src="https://code.jquery.com/jquery-1.12.4.js"
	type="text/javascript"></script>
<script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"
	type="text/javascript"></script>



<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Portal</title>
<style type="text/css">
/* Style inputs with type="text", select elements and textareas */
/* input[type=text], select, textarea {
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
} */

table {
	background-color: transparent;
	border-collapse: collapse;
	table-layout: fixed;
	width: 100%;
}

table td {
	width: 70px;
	word-wrap: break-word;
	text-align: justify;
}

.ui-accordion .ui-accordion-header {
	display: block;
	cursor: pointer;
	position: relative;
	margin: 2px 0 0 0;
	padding: .5em .5em .5em .7em;
	font-size: 100%;
	background: #4caf50;
}
/* Style the submit button with a specific background color etc */
/* input[type=submit] {
	background-color: #4CAF50;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-weight: 600;
}

/* Style the reset button with a specific background color etc */
/* input[type=button] {
	background-color: #4CAF50;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-weight: 600;
} */ */

/* When moving the mouse over the submit button, add a darker green color */
input[type=submit]:hover {
	background-color: #45a049;
}

/* Add a background color and some padding around the form */
.container {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 70px;
	width: 100px;
	margin: 0 auto;
}

.ui-accordion .ui-accordion-content {
	padding: 1em 2.2em;
	border-top: 0;
	overflow: auto;
	height: auto !important;
}

.h3 {
	background-color: green;
}

.error {
	color: #ff0000;
	font-style: italic;
}




</style>
</head>
   <body>
   <div id="container" align="center">
     <form method="post" action="peds" commandName="Patent">
         Company Url/Domain: <input type = "text" name = "last_name" required="required"/>
         <input type = "submit" value = "Search" style="height: 25px"/>
      </form>
      </div>
      <div>
      <h2>
      Contact Information:
      </h2>
      <table>
			</table>
      </div>
      <div>
      <h2>
      Funding Information:
      </h2>
      <table>
			</table>
      </div>
   </body>
</html>