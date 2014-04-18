<html>
<head>
<title>Common Remote Pool</title>
<link rel="shortcut icon" href="image/favicon.ico" type="image/x-icon" />
 </head>
<body>

<p>
<h3>How to use it?</h3>

<h4> --- http request style --- </h4>

<ul>
<li>borrow the object from pool</li>
</ul>
<pre>
	Request:
	        GET http://localhost/common-remote-pool/service/object/borrow

	Response: has resource
		response code:200
		response body: json
		example: {"domain":"10.224.64.225","user":"6731"}

	Response: no resource
		response code:404

</pre>
<ul>
<li>return resource to pool</li>
</ul>
<pre>
	Request:
		POST http://localhost/common-remote-pool/service/object/return
 		body: json
		example: {"domain":"10.224.64.225","user":"6731"}

	Response:
		response code:204
</pre>

<ul>
<li>add resource to pool</li>
</ul>
<pre>
	Request:
		POST http://localhost/common-remote-pool/service/object/add
 		body: json
		example: {"domain":"10.224.64.225","user":"6731"} or [{"domain":"10.224.64.225","user":"6731"},{"domain":"10.224.64.13","user":"6732"}]

	Response:
		response code:200
</pre>

<ul>
<li>get borrowed resource number</li>
</ul>
<pre>
	Request:
		GET http://localhost/common-remote-pool/service/object/active

	Response:
		response code:200
		response body: number
</pre>

<ul>
<li>get idle resource number</li>
</ul>
<pre>
	Request:
		GET http://localhost/common-remote-pool/service/object/idle

	Response:
		response code:200
		response body: number
</pre>

<ul>
<li>get pools resource amount info</li>
</ul>
<pre>
	Request:
		GET http://localhost/common-remote-pool/service/object/idle

	Response:
		response code:200
		response body: {"idleNumber":3,"borrowedNumber":3,"totalNumber":6}
</pre>

<ul>
<li>flush all resource</li>
</ul>
<pre>
	Request:
		GET http://localhost/common-remote-pool/service/object/drain

	Response:
		response code:200
 </pre>

<ul>
<li>query current enabled resource factory</li>
</ul>
<pre>
	Request:
		GET http://localhost/common-remote-pool/service/object/getFactory

	Response:
		response code:200
		response content: such as com.googlecode.common.remote.pool.resource.DefaultResourceFactory
 </pre>

<h4> --- Client style --- </h4>
The client's code amount is so small that you can copy the <a href="http://code.google.com/p/common-remote-pool/source/browse/common-remote-pool/src/main/java/com/googlecode/common/remote/pool/client/CommonRemotePoolClient.java">code</a> directly.

<p>
<pre>
com.googlecode.common.remote.pool.client.CommonRemotePoolClient.CommonRemotePoolClient(String)
com.googlecode.common.remote.pool.client.CommonRemotePoolClient.borrowObject(Class<T>)
com.googlecode.common.remote.pool.client.CommonRemotePoolClient.returnObject(Object)
com.googlecode.common.remote.pool.client.CommonRemotePoolClient.addObject(Object...)
</pre>

<p>
Need dependence:
<xmp>
       <dependency>
            <groupId>org.jboss.resteasy</groupId>
            <artifactId>resteasy-jackson-provider</artifactId>
            <version>2.3.1.GA</version>
        </dependency>

        <dependency>
            <groupId>org.jboss.resteasy</groupId>
            <artifactId>resteasy-client</artifactId>
            <version>3.0.2.Final</version>
        </dependency>
</xmp>

</body>
</html>


