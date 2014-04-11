<html>
<body>
	<h2>Welcome to Common Remote Pool!</h2>
	Welcome to Common Remote Pool !

<h3>How to initial it?</h3>

<p>
<ul>
<li>If you need define your implement, you should <a href="upload.jsp">upload</a>  your resource factory class , if you have no need, omit this step</li>
</ul>

<p>
<h3>How to use it?</h3>
<ul>
<li>borrow the object from pool</li>
</ul>
<pre>
	Request:
	        GET http://localhost:8080/common-remote-pool/service/object/borrow

	Response: has resource
		response code:200
		response body: json
		example: {"domain":"10.224.64.225","user":"6731","outboundProxy":"sip:10.224.64.225;","password":"6731"}

	Response: no resource
        response code:404

</pre>
<ul>
<li>return resource to pool</li>
</ul>
<pre>
	Request:
		POST http://localhost:8080/common-remote-pool/service/object/return

        response code:204
		body: json
		example: {"domain":"10.224.64.225","user":"6731","outboundProxy":"sip:10.224.64.225;","password":"6731"}

	Response:
		response code:200
</pre>
<ul>
<li>get active resource had be borrowed</li>
</ul>
<pre>
	Request:
		GET http://localhost:8080/common-remote-pool/service/object/active

	Response:
		response code:200
		response body: number
</pre>
<ul>
<li>flush all resource</li>
</ul>
<pre>
    Request:
        GET http://localhost:8080/common-remote-pool/service/object/drain

    Response:
        response code:200
 </pre>

<h3>How to manager resource?</h3>

<p>
<ul>
<li>By default implement, you can <a href="manager.jsp">manager</a> resource</li>
</ul>


</body>
</html>
