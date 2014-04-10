<html>
<body>
	<h2>Welcome to Common Remote Pool!</h2>
	Welcome to Common Remote Pool !

<h3>How to initial it</h3>

<p>
<ul>
<li> <a href="upload.jsp">Upload</a>  your resource factory class which should use name: com.googlecode.common.remote.pool.resource.ResourceFactory.the code can refer to <a href="https://code.google.com/p/common-remote-pool/source/browse/common-remote-pool/src/main/java/com/googlecode/common/remote/pool/resource/ResourceFactory.java">example</a></li>
</ul>
<p>
<h3>How to use it</h3>
<ul>
<li>borrow the object from pool</li>
</ul>
<pre>
Request:
        GET http://localhost:8080/common-remote-pool/service/object/borrow

Response:
	response code:200
	response body: json
	example: {"domain":"10.224.64.225","user":"6731","outboundProxy":"sip:10.224.64.225;","password":"6731"}
</pre>
<ul>
<li>return resource to pool</li>
</ul>
<pre>
Request:
	GET http://localhost:8080/common-remote-pool/service/object/return

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


</body>
</html>
