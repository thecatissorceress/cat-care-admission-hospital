<%@ page import="java.text.SimpleDateFormat, java.util.*, com.catcareadmission.controller.*,com.catcareadmission.Services.*, com.catcareadmission.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>

<head>
	<title>Admission</title>
	<link type="text/css" rel="stylesheet" href="css/style.css"/>

</head>

<%
 //get the students from the request object (sent by servlet)
 
SimpleDateFormat formatterDate = new SimpleDateFormat("dd/MM/yyyy"); 
SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm a"); 
%>


<body>
	
	<div id="wrapper">
		<div id="header">
		
			<h3>Cat Care Meowspital</h3>
		<input type="submit" value="Logout" onclick="window.location.href='index.jsp';return false;"
	class="add-student-button" style="float:right; margin-top: -25px;"/>	
		</div>
	</div>
	
	<div id="container">
	
		<div id="content">
		
	<input type="submit" value="Submit" onclick="window.location.href='add-admission-form.jsp';return false;"
	class="add-student-button"/>
	<!--  add a search box -->
            <form action="AdmissionControllerServlet" method="GET">
        
                <input type="hidden" name="command" value="SEARCH" />
            
                Search student: <input type="text" name="theSearchName" />
                
                <input type="submit" value="Search" class="add-student-button" />
            
            </form>	
		<table border="1">
		
			<tr>
				<th>Patient ID </th>
				<th>Owner First Name </th>
				<th>Owner Last Name </th>
				<th>Owner Email </th>
				<th>Owner Contact </th>
				<th>Cat Name </th>
				<th>Cat Breed </th>
				<th>Service to Perform </th>
				<th>Admission Date </th>
				<th>Action</th>
		
			</tr>
			<c:forEach var="tempAdmission" items="${ADMISSION_LIST}">
				<!-- Setup link -->
				<c:url var="tempLink" value="AdmissionControllerServlet">
					<c:param name="command" value="LOAD"/>
					<c:param name="patientId" value="${tempAdmission.getPatientId()}" />
				</c:url>
					<c:url var="deleteLink" value="AdmissionControllerServlet">
					<c:param name="command" value="DELETE"/>
					<c:param name="patientId" value="${tempAdmission.getPatientId()}" />
				</c:url>
				<tr>
					
					<td>${tempAdmission.getPatientId()}</td>
					<td>${tempAdmission.getOwnerFirstName()}</td>
					<td>${tempAdmission.getOwnerLastName()}</td>
					<td>${tempAdmission.getOwnerEmail()}</td>
					<td>${tempAdmission.getOwnerContact()}</td>
					<td>${tempAdmission.getCatName()}</td>
					<td>${tempAdmission.getCatBreed()}</td>
					<td>${tempAdmission.getServicetoPerform()}</td>
					<td><fmt:formatDate type="date" dateStyle="long" value="${tempAdmission.getAdmissionDate()}" /></td>
					<td><a href="${tempLink}">Update</a> |  <a href="${deleteLink}" onclick="if (!(confirm('Are you sure you want to delete this record? '))) return false;">Delete</a></td>
				</tr>
				
			</c:forEach>
			
		</table>
		
		</div>
	
	</div>

</body>

</html>