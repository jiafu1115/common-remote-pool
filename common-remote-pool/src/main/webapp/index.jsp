<html>
<head>
<title>Common Remote Pool</title>
<link rel="shortcut icon" href="image/favicon.ico" type="image/x-icon" />
<link type="text/css" rel="stylesheet" href="css/style.css">
 </head>
<body>
	<h2>Welcome to Common Remote Pool!</h2>
<p>
   Apache common object pool can help user to manager resource, but if use it , you only can share resources in one machine, So we use the object pool and resteasy framework to constructor one web service to provide distributed pool to share resources between multiple machines. The web common remote pool provide one default implement which can add any json string to pool and borrow json resource from it.What's more, the resource object factory's class can be uploaded into the web service. In short, the project is restful style web object pool for global access.

<p>
<hr>
<%@ include file="initial.jsp" %>
<hr>
<%@ include file="usage.jsp" %>
<hr>
<%@ include file="managerusage.jsp" %>
<hr>
<%@ include file="counter.jsp" %>


<br>

</body>
</html>

