<html>
<head>
<script src="scripts/jquery-1.11.0.js"></script>
<title>Common Remote Pool</title>
<link rel="shortcut icon" href="image/favicon.ico" type="image/x-icon" />
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<script>
$(function(){
    $("#addForm").submit(function(){
        $.ajax({
        type: "POST",
        url: "service/object/add",
        data: $("#addForm").serialize(),
        success: function(data) {
		 		alert("SUCCESS");
        },
        error: function() {
	 		alert("FAIL");
    }
        });

        return false;

    });
});
</script>

<script>
$(function(){
  $("#view").click(function(){
      $.get("service/object/info",function(data,status){
         $("#infodiv").html(JSON.stringify(data));
     });
  });
});
</script>

<script>
$(function(){
  $("#view").click(function(){
      $.get("service/object/info",function(data,status){
         $("#infodiv").html(JSON.stringify(data));
     });
  });
});
</script>

<script>
$(function(){
  $("#listPool").click(function(){
      $.get("service/object/list",function(data,status){
         $("#listPoolDiv").html(data);
     });
  });
});
</script>

<script>
$(document).ready(function(){
  $("#drain").click(function(){
      $.get("service/object/drain",function(data,status){
          alert(data);
     });
  });
});
</script>

<body>
	<h1>Resource Manager</h1>
<p>
<hr>
    <h3>Add Resource</h3>

	<form id="addForm"	enctype="application/x-www-form-urlencoded">
		<p>
			Json String: <input type="text" name="jsonContent" value="{&quot;file&quot;:&quot;1.txt&quot;,&quot;owner&quot;:&quot;jiafu&quot;}"  size=22/>
   		</p>
		<input type="submit" value="add"  />

	 </form>

	 Note: if want to add multiple resource, you can use the format [{"file":"1.txt","owner":"jiafu"},{"file":"2.txt","owner":"jiafu"}]

<p>
<hr>
    <h3>Drain All Resource</h3>

    <button id="drain">drain</button>
<p>
<hr>
    <h3>Pool Infos</h3>

	<button id="view">view</button>
	<p>
	<div id="infodiv"></div>

<p>
<hr>
    <h3>Objects In Pool</h3>

	<button id="listPool">list</button>
	<p>
	<div id="listPoolDiv"></div>

<hr>
<%@ include file="counter.jsp" %>
</body>
</html>