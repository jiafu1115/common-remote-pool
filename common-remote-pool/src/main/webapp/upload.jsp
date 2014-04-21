<html>
<head>
<title>Common Remote Pool</title>

<link rel="shortcut icon" href="image/favicon.ico" type="image/x-icon" />

<script src="scripts/jquery-1.11.0.js"></script>

</head>
<body>


<script>
window.onload =function(){
    $.ajax({
    type: "GET",
    url: "service/object/getFactory",
    success: function(data) {
            var currentFactory = document.getElementById("currentFactory");
            currentFactory.innerHTML=data;
    },
    error: function() {
 		alert("FAIL");
	}
    });

    return false;
}
</script>

<script>
$(function(){
    $("#enableForm").submit(function(){
        $.ajax({
        type: "POST",
        url: "service/file/setFactory",
        data: $("#enableForm").serialize(),
        success: function(data) {
                var currentFactory = document.getElementById("currentFactory");
                currentFactory.innerHTML =$("#enableForm").serialize().split("=")[1];
        },
        error: function() {
	 		alert("FAIL");
    }
        });

        return false;

    });
});
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

    <h3>Step 2: Enable Core ResourceFactory Implement Class</h3>


	<form id="enableForm" enctype="application/x-www-form-urlencoded">
        <p><input type="text" name="fileName" value="com.googlecode.common.remote.pool.resource.DefaultResourceFactory" size="70"/>
        <p><input type="submit" value="Enable" />
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