<html>
<body>
	<h1>JAX-RS Upload Form</h1>

	<form action="service/file/upload" method="post" enctype="multipart/form-data">

		<p>
			Select a file : <input type="file" name="uploadedFile" size="50" />
		</p>

		<input type="submit" value="Upload Files" />
	</form>

</body>
</html>