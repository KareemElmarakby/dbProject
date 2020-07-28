<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Registration</title>
</head>
<body>
 <div align="center">
  <h1>User Registration</h1>
  <form action="<%= request.getContextPath() %>/insert" name="test" method="post" onSubmit="return checkPassword()">
   <table style="with: 80%">
    <tr>
     <td>First Name</td>
     <td><input type="text" name="firstName" /></td>
    </tr>
    <tr>
     <td>Last Name</td>
     <td><input type="text" name="lastName" /></td>
    </tr>
    <tr>
     <td>Age</td>
     <td><input type="text" name="age" /></td>
    </tr>
    <tr>
	<tr>
     <td>Gender (m or f)</td>
     <td><input type="text" name="gender" /></td>
    </tr>
    <tr>
    <tr>
     <td>Email (this will be your username)</td>
     <td><input type="text" name="username" /></td>
    </tr>
    <tr>
     <td>Confirm Password</td>
     <td><input type="password" name="password" /></td>
    </tr>
    <tr>
     <td>Confirm Password</td>
     <td><input type="password" name="confirmPassword" /></td>
    </tr>
   </table>
   <input type="submit" value="Submit" />
  </form>
 </div>
</body>
</html>