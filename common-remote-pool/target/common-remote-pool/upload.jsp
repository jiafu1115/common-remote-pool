<html>

<body>


<script type="text/javascript">  
 
var url="http://localhost:8080/common-remote-pool/service/object/getFactory";
var http_request;
if(window.ActiveXObject){ 
            http_request= new ActiveXObject("Microsoft.XMLHTTP");  
}else if(window.XMLHttpRequest){ 
            http_request= new XMLHttpRequest();  
}  
  
http_request.open("GET", url, true);
http_request.onreadystatechange = processTextResponse;
http_request.send();
    
function processTextResponse() 
{
         if (http_request.readyState == 4) 
 			{
                if (http_request.status == 200) 
  				{
                        var content = http_request.responseText;
                        var errtd = document.getElementById("currentFactory");
                        errtd.innerHTML =content;
                          
                 } 
            } 
 
}
</script> 


	<h1>ResourceFactory Implement Class Customized</h1>
<p>
    <h3>Step 1: Upload ResourceFactory Implement Related Class/Resource</h3>

	<form action="service/file/upload" method="post"
		enctype="multipart/form-data">
		<p>
			* Target full path : <input type="text" name="fileName" value="com.googlecode.common.remote.pool.resource.DefaultResourceFactory.class" size="70"/>
		</p>
		<p>
			* Choose file : <input type="file" name="selectedFile" />
		</p>
		<input type="submit" value="Upload" />
	</form>
 <p>

    <h3>Step 2: Enable Core ResourceFactory Implement Class</h2>



	   <form action="service/file/setFactory" method="post"
        enctype="application/x-www-form-urlencoded">
        <p>
             <input type="text" name="fileName" value="com.googlecode.common.remote.pool.resource.DefaultResourceFactory" size="70"/>
            <p><input type="submit" value="Enable" />
         </p>
    </form>
    
    Current Resource Factory is: <div id="currentFactory"></div> 

	<p>
		<h4>Note: </h4>
		<t>The class should use implement <code>org.apache.commons.pool.PoolableObjectFactory</code>
		,its code example can refer to <a
			href="https://code.google.com/p/common-remote-pool/source/browse/common-remote-pool/src/main/java/com/googlecode/common/remote/pool/resource/DefaultResourceFactory.java">example</a>.What's
		more, you should add followed dependence into your pom.xml:
		<xmp>
<dependency>
  <groupId>commons-pool</groupId>
  <artifactId>commons-pool</artifactId>
  <version>1.6</version>
  <type>jar</type>
</dependency>
		</xmp>
</body>
</html>