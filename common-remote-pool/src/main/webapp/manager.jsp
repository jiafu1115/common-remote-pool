<html>
<body>
	<h1>Resource Manager</h1>


    <h2>Add Resource</h2>

	<form action="service/object/add" method="post"
		enctype="application/x-www-form-urlencoded">
		<p>
			Json Object String: <input type="text" name="jsonContent" />
			<br>
			such as {"config":"1.txt"}
 		</p>
		<input type="submit" value="add" />

	 </form>


<br>
<br>
<br>

    <h2>Drain All Resource</h2>

 	<form action="service/object/drain" method="get"
        enctype="application/x-www-form-urlencoded">
         <input type="submit" value="set" />
    </form>

</body>
</html>