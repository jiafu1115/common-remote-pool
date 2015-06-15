Apache common object pool can help user to manager resource, but if use it , you will find you can only share resources in one machine,not between multi-process or multi-machine. So we use the object pool to constructor one web service to provide distributed pool to share resources between multiple machines. The web common remote pool provide one default implement which can add any json string to pool and borrow json resource from it.What's more, the resource object factory's class can be uploaded into the web service. In short, the project is restful style web object pool for global access.

# How to initial it #

1. mvn tomcat7:run to start the webservice;

2. If you need define your implement, you should <a href='upload.jsp'>upload</a>  your resource factory class , if you have no need, omit this step.

![http://ww2.sinaimg.cn/mw690/7d9d794bgw1efwghdt6dtj211h0if0w1.jpg](http://ww2.sinaimg.cn/mw690/7d9d794bgw1efwghdt6dtj211h0if0w1.jpg)



# How to use it #



## http request style ##

<ul>
<li>borrow the object from pool</li>
</ul>
<pre>
Request:<br>
GET http://localhost:8080/common-remote-pool/service/object/borrow<br>
<br>
Response: has resource<br>
response code:200<br>
response body: json<br>
example: {"domain":"10.224.64.225","user":"6731"}<br>
<br>
Response: no resource<br>
response code:404<br>
<br>
</pre>
<ul>
<li>return resource to pool</li>
</ul>
<pre>
Request:<br>
POST http://localhost:8080/common-remote-pool/service/object/return<br>
<br>
body: json<br>
example:{"domain":"10.224.64.225","user":"6731"}<br>
<br>
Response:<br>
response code:204<br>
</pre>
<ul>
<li>get borrowed resouce number</li>
</ul>
<pre>
Request:<br>
GET http://localhost:8080/common-remote-pool/service/object/active<br>
<br>
Response:<br>
response code:200<br>
response body: number<br>
</pre>

<ul>
<li>add resource to pool</li>
</ul>
<pre>
Request:<br>
POST http://localhost:8080/common-remote-pool/service/object/add<br>
<br>
body: json<br>
example: {"domain":"10.224.64.225","user":"6731"} or [{"domain":"10.224.64.225","user":"6731"},{"domain":"10.224.64.13","user":"6732"}]<br>
<br>
Response:<br>
response code:200<br>
</pre>

<ul>
<li>flush all resource</li>
</ul>
<pre>
Request:<br>
GET http://localhost:8080/common-remote-pool/service/object/drain<br>
<br>
Response:<br>
response code:200<br>
</pre>

<ul>
<li>get idle resource number</li>
</ul>
<pre>
Request:<br>
GET http://localhost:8080/common-remote-pool/service/object/idle<br>
<br>
Response:<br>
response code:200<br>
response body: number<br>
</pre>

<ul>
<li>get pools resource amount info</li>
</ul>
<pre>
Request:<br>
GET http://localhost:8080/common-remote-pool/service/object/idle<br>
<br>
Response:<br>
response code:200<br>
response body: {"idleNumber":3,"borrowedNumber":3,"totalNumber":6}<br>
</pre>
<ul>
<li>query current enabled resource factory</li>
</ul>
<pre>
Request:<br>
GET http://localhost:8080/common-remote-pool/service/object/getFactory<br>
<br>
Response:<br>
response code:200<br>
response content: such as com.googlecode.common.remote.pool.resource.DefaultResourceFactory<br>
</pre>
<ul>
<li>list all resource in pool</li>
</ul>
<pre>
Request:<br>
GET http://localhost:8080/common-remote-pool/service/object/list<br>
<br>
Response:<br>
response code:200<br>
response content: "no any resource" or {file=1.txt, owner=jiafu}:2014-04-28 14:29:34<br>
</pre>

## client style ##

> <br>
<blockquote>The client's code amount is so small that you can copy the <a href='http://code.google.com/p/common-remote-pool/source/browse/common-remote-pool/src/main/java/com/googlecode/common/remote/pool/client/CommonRemotePoolClient.java'>code</a> directly.</blockquote>

<blockquote><p>
<pre><code>	com.googlecode.common.remote.pool.client.CommonRemotePoolClient.CommonRemotePoolClient(String)
<br>
	com.googlecode.common.remote.pool.client.CommonRemotePoolClient.borrowObject(Class&lt;T&gt;)
<br>
	com.googlecode.common.remote.pool.client.CommonRemotePoolClient.returnObject(Object)
<br>
	com.googlecode.common.remote.pool.client.CommonRemotePoolClient.addObject(Object...)
<br>
</code></pre></blockquote>

<blockquote><p>
Dependence:</blockquote>

<pre><code>        &lt;dependency&gt;
<br>
	  &lt;groupId&gt;org.jboss.resteasy&lt;/groupId&gt;
<br>
	  &lt;artifactId&gt;resteasy-jackson-provider&lt;/artifactId&gt;
<br>
	  &lt;version&gt;2.3.1.GA&lt;/version&gt;
<br>
        &lt;/dependency&gt;
<br>
        &lt;dependency&gt;
<br>
	  &lt;groupId&gt;org.jboss.resteasy&lt;/groupId&gt;
<br>
	  &lt;artifactId&gt;resteasy-client&lt;/artifactId&gt;
<br>
	  &lt;version&gt;3.0.2.Final&lt;/version&gt;
<br>
        &lt;/dependency&gt;
<br>
</code></pre>



<h1>How to manage resource</h1>

you can manage resource by default implement.<br>
<br>
<img src='http://ww3.sinaimg.cn/mw690/7d9d794bgw1efwghk78e9j211h0i4wgm.jpg' />
