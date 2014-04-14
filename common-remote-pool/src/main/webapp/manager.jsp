<html>
<head>
<title>Common Remote Pool</title>
<link rel="shortcut icon" href="image/favicon.ico" type="image/x-icon" />
</head>
<body>
	<h1>Resource Manager</h1>


    <h3>Add Resource</h3>

	<form action="service/object/add" method="post"
		enctype="application/x-www-form-urlencoded">
		<p>
			Json String: <input type="text" name="jsonContent" value="{&quot;file&quot;:&quot;1.txt&quot;,&quot;owner&quot;:&quot;jiafu&quot;}"  size=22/>
   		</p>
		<input type="submit" value="add" />

	 </form>
	 
	 Note: if want to add multiple resource, you can use the format [{"file":"1.txt","owner":"jiafu"},{"file":"2.txt","owner":"jiafu"}]
  
<p>
    <h3>Drain All Resource</h3>

 	<form action="service/object/drain" method="get"
        enctype="application/x-www-form-urlencoded">
         <input type="submit" value="drain" />
    </form>
    
    <h3>Pool Infos</h3>
      
    <input type="button" onclick="window.open('service/object/info')" value="view">

</body>
</html>