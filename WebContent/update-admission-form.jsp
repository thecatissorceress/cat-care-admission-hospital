<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html>
<head>

<title>Add Admission</title>
<link type="text/css" rel="stylesheet" href="css/style.css"/>
<link type="text/css" rel="stylesheet" href="css/add-student-style.css"/>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script>
            $(function () {
                $("#datepicker").datepicker({dateFormat: 'yy-mm-dd'}).val();
            });
        </script>
</head>
<body>

	<div id="wrapper">
	
		<div id="header">
		
			<h2>Cat Care Meowspital</h2>
		
		</div>
	</div>
	
	<div id="container">
		<h3> Update Admission</h3>
		
		<form autocomplete="off" action="AdmissionControllerServlet" method="GET">
		
		<input type="hidden" name="command" value="UPDATE" />
		
		<input type="hidden" name="patientId" value="${THE_PATIENT.patientId }" />
		
		<table>
		
			<tbody>
			
				<tr>
				<td><label>Owner's First Name: </label></td>
				<td><input type="text" name="ownerFirstName" 
					value="${THE_PATIENT.ownerFirstName}"
				/></td>
				</tr>
				
				<tr>
				<td><label>Owner's Last Name: </label></td>
				<td><input type="text" name="ownerLastName" 
						value="${THE_PATIENT.ownerLastName}"
				/></td>
				</tr>
				
				<tr>
				<td><label>Owner's Email: </label></td>
				<td><input type="text" name="ownerEmail" 
				value="${THE_PATIENT.ownerEmail}"
				/></td>
				</tr>
				
				<tr>
				<td><label>Owner's Contact: </label></td>
				<td><input type="text" name="ownerContact" 
					value="${THE_PATIENT.ownerContact}"
				/></td>
				</tr>
				
				
				<tr>
				<td><label>Cat's Name: </label></td>
				<td><input type="text" name="catName"
				value="${THE_PATIENT.catName}"
				
				 /></td>
				</tr>
				
				
				<tr>
				<td><label>Cat's Breed: </label></td>
				<td><input type="text" name="catBreed"
				value="${THE_PATIENT.catBreed}"
				 /></td>
				</tr>
				
				<tr>
				<td><label>Service to Perform: </label></td>
				<td><input type="text" name="serviceToPerform"
				value="${THE_PATIENT.servicetoPerform}"
				 /></td>
				</tr>
				
				<tr>
				<td><label>Admission Date: </label></td>
				
				<td> <input type="text" name="admissionDate" id="datepicker"
				value="${THE_PATIENT.admissionDate}"
				 /></td>
				</tr>
				
				<tr>
				<td><label></label></td>
				<td><input type="submit" value="Submit"/></td>
				</tr>
			
			</tbody>
		
		</table>
		
		
		<a href="AdmissionControllerServlet"> Back to List</a>
		</form>
	
	</div>


</body>

</html>