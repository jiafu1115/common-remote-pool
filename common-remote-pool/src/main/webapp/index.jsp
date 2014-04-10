<html>
<body>
	<h2>Welcome to Common Remote Pool!</h2>
	Welcome to Common Remote Pool !

<h3>How to initial it</h3>

<p>
<li> <a href="upload.jsp">upload</a>  your resource factory class.

<p>
<h3>How to use it</h3>

<li>borrow the object from pool

<pre>
Request:
        GET http://localhost:8080/common-remote-pool/service/object/borrow

Response:
	response code:200
	response body: json
	example: {"domain":"10.224.64.225","user":"6731","outboundProxy":"sip:10.224.64.225;","password":"6731"}
</pre>
<li>return resource to pool
<pre>
Request:
	GET http://localhost:8080/common-remote-pool/service/object/return

	body: json
	example: {"domain":"10.224.64.225","user":"6731","outboundProxy":"sip:10.224.64.225;","password":"6731"}

Response:
	response code:200
</pre>

<li>get active resource had be borrowed
<pre>
Request:
	GET http://localhost:8080/common-remote-pool/service/object/active

Response:
	response code:200
	response body: number
</pre>


</body>
</html>
