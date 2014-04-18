<html>
<head>
<title>Common Remote Pool</title>
<link rel="shortcut icon" href="image/favicon.ico" type="image/x-icon" />
 </head>
<body>
	<h2>Welcome to Common Remote Pool!</h2>
Apache common object pool can help user to manager resource, but if use it , you only can share resources in one machine, So we use the object pool and resteasy framework to constructor one web service to provide distributed pool to share resources between multiple machines. The web common remote pool provide one default implement which can add any json string to pool and borrow json resource from it.What's more, the resource object factory's class can be uploaded into the web service. In short, the project is restful style web object pool for global access.


<%@ include file="initial.jsp" %>
<%@ include file="usage.jsp" %>
<%@ include file="managerusage.jsp" %>

<br>

</body>
</html>

