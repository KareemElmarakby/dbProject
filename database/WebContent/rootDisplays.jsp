<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="<%= request.getContextPath() %>/displaytrends" name="test2" method="post">
   <table style="with: 80%">
    <tr>
     <td>Who's Cool</td>
     <td><input type="submit" value="cool" name="Submit" /></td>
    </tr>
    <tr>
     <td>Who's New</td>
     <td><input type="submit" value="new" name="Submit" /></td>
    </tr>
    <tr>
     <td>Who's Hot</td>
     <td><input type="submit" value="hot" name="Submit" /></td>
    </tr>
    <tr>
     <td>Who's Top</td>
     <td><input type="submit" value="top" name="Submit" /></td>
    </tr>
    <tr>
     <td>Popular Tags</td>
     <td><input type="submit" value="tags" name="Submit" /></td>
    </tr>
    <tr>
     <td>Common Favorite Comedian</td>
     <td><input type="submit" value="favorite" name="Submit" /></td>
    </tr>
    <tr>
     <td>Most Productive Users</td>
     <td><input type="submit" value="productive" name="Submit" /></td>
    </tr>
    <tr>
     <td>Positive Reviewers</td>
     <td><input type="submit" value="reviewers" name="Submit" /></td>
    </tr>
    <tr>
     <td>Poor Youtubes</td>
     <td><input type="submit" value="poor" name="Submit" /></td>
    </tr>
    <tr>
     <td>Twin Users</td>
     <td><input type="submit" value="twins" name="Submit" /></td>
    </tr>
    </table>
  </form>
</body>
</html>