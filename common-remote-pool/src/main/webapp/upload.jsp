<html>
<body>
	<h1>ResourceFactory Implement Class Customized:</h1>


    <h2>Upload ResourceFactory Implement Related Class/Resource</h2>

	<form action="service/file/upload" method="post"
		enctype="multipart/form-data">
		<p>
			full path : <input type="text" name="fileName" />
			<br>such as: com.googlecode.common.remote.pool.resource.ResourceFactory.class
		</p>
		<p>
			Choose the file : <input type="file" name="selectedFile" />
		</p>
		<input type="submit" value="Upload" />
        </form>
<br>
<br>
<br>

    <h2>Enable Core ResourceFactory Implement Class</h2>



	   <form action="service/file/setFactory" method="post"
        enctype="application/x-www-form-urlencoded">
        <p>
            full path : <input type="text" name="fileName" />
            <br>such as com.googlecode.common.remote.pool.resource.ResourceFactory
        </p>
         <input type="submit" value="set" />
    </form>

	<p>
		<br> <br>
		<t>Note: The class should use name:
		com.googlecode.common.remote.pool.resource.ResourceFactory,its code
		can refer to <a
			href="https://code.google.com/p/common-remote-pool/source/browse/common-remote-pool/src/main/java/com/googlecode/common/remote/pool/resource/ResourceFactory.java">example</a>.What's
		more, you should add followed dependence into your pom to write the
		implements class.
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