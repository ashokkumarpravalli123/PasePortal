<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <script
	src='//static.codepen.io/assets/editor/live/console_runner-ce3034e6bde3912cc25f83cccb7caa2b0f976196f2f2d52303a462c826d54a73.js'></script>
<script
	src='//static.codepen.io/assets/editor/live/css_live_reload_init-890dc39bb89183d4642d58b1ae5376a0193342f9aed88ea04330dc14c8d52f55.js'></script>
<meta charset='UTF-8'>
<meta name="robots" content="noindex">
<link rel="shortcut icon" type="image/x-icon"
	href="//static.codepen.io/assets/favicon/favicon-8ea04875e70c4b0bb41da869e81236e54394d63638a1ef12fa558a4a835f1164.ico" />
<link rel="mask-icon" type=""
	href="//static.codepen.io/assets/favicon/logo-pin-f2d2b6d2c61838f7e76325261b7195c27224080bc099486ddd6dccb469b8e8e6.svg"
	color="#111" />
<link rel="canonical" href="https://codepen.io/spenser/pen/wKdzay" />

<link rel='stylesheet prefetch'
	href='//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>
<link rel='stylesheet prefetch'
	href='//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.9.0/bootstrap-table.min.css'>
<link rel='stylesheet prefetch'
	href='https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css'>
 -->
<title>Portal</title>

<style>
body {
	margin: 2rem;
}

/* th {
  background-color: white;
}
tr:nth-child(odd) {
  background-color: grey;
}
th, td {
  padding: 0.5rem;
}
td:hover {
  background-color: lightsalmon;
} */

.paginate_button {
	border-radius: 0 !important;
}

   /* table {
    height: 300px; 
    float:center;
  display: inline-block;
  overflow-y: scroll;
} */

th, td {
    text-align: left;
    padding: 8px;
}

tr:nth-child(even){background-color: #f2f2f2} 
</style>


</head>
<body>
	<div id="container" align="center">
     <form method="post" action="results" commandName="Company">
         Company Url/Domain: <input type = "text" name = "companyUrl" required="required"/>
         <input type = "submit" value = "Search" style="height: 25px"/><br>
         <label for="Note" style="color: #FF0000; font-size: 15px;">Note:
				Give Company Url Mandatory...</label>
      </form>
      </div>
     <div>
      <h2 style="color: #45a049;" >
      <b style="font-size: 15px">Funding Rounds:</b>&nbsp;&nbsp;
       <c:if test="${!empty suggest}">
      <b style="font-size: 15px">Total Funding: </b><b style="font-size: 15px">${suggest}</b>
      </c:if>
      <c:if test="${!empty error}">
      <b style="font-size: 15px">Note: </b><b style="font-size: 15px">${error}</b>
      </c:if>
      </h2>
      <c:if test="${!empty bo.crunchbaseFundingsList}">
      <table border="1" style="height: 300px;width:100%;display: inline-block;overflow-y: scroll;font-family: sans-serif;font-size: 100%" align="center">
      <thead>
						<tr>
							<th>Announced Date</th>
							<th>Funding Type</th>
							<th>No.Of Investors</th>
							<th>Money Raised($)</th>
							<th>Investors</th> 
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${bo.crunchbaseFundingsList}" var="temp" varStatus="status">
							<tr>
							<td>${temp.announcedOn}</td>
							<td>${temp.fundingType}</td>
							<td>${temp.noOfInvestors}</td>
							<td>${temp.moneyRaisedUSD}</td>
							 <td>${temp.investorNames}</td>
						</c:forEach>
					</tbody>
			</table>
			</c:if>
      </div>
   </body>
	<!-- <script
		src='//static.codepen.io/assets/common/stopExecutionOnTimeout-b2a7b3fe212eaa732349046d8416e00a9dec26eb7fd347590fbced3ab38af52e.js'></script>
	<script
		src='//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script
		src='//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>
	<script
		src='https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js'></script> -->
	
</body>
</html>