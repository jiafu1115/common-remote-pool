<html>
<head>
<script src="scripts/jquery-1.11.0.js"></script>
<title>Common Remote Pool</title>
<link rel="shortcut icon" href="image/favicon.ico" type="image/x-icon" />
<link type="text/css" rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/jquery-ui.css" type="text/css" />

<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="scripts/jquery.easy-confirm-dialog.js"></script>
</head>

<script>
$(function(){
$("#drain").easyconfirm({locale: {title: 'Are you sure?',  width: '110', button: ['No','Yes']}});
});
</script>

<script>
$(function(){
$("#clearlog").easyconfirm({locale: {title: 'Are you sure?',  width: '110', button: ['No','Yes']}});
});
</script>

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
        $.ajax({
        type: "GET",
        url: "service/object/info",
        success: function(data) {
            $("#infodiv").html(JSON.stringify(data));
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
    $("#listPool").click(function(){
        $.ajax({
        type: "GET",
        url: "service/object/list",
        success: function(data) {
            $("#listPoolDiv").html(data);
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
    $("#listAdded").click(function(){
        $.ajax({
        type: "GET",
        url: "service/object/listAdd",
        success: function(data) {
            $("#listAddedDiv").html(data);
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
    $("#loged").click(function(){
        $.ajax({
        type: "GET",
        url: "service/object/log",
        success: function(data) {
            $("#logedDiv").html(data);
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
    $("#logedSort").click(function(){
        $.ajax({
        type: "GET",
        url: "service/object/logsort",
        success: function(data) {
            $("#logedSortDiv").html(data);
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
$(document).ready(function(){
  $("#drain").click(function(){
      $.get("service/object/drain",function(data,status){
          alert(data);
     });
  });
});
</script>

<script>
$(document).ready(function(){
  $("#clearlog").click(function(){
      $.get("service/object/clearlog",function(data,status){
          alert(data);
     });
  });
});
</script>

<body>
	<h2>Resource Manager</h2>
<p>
<hr>
    <h3>Add Resource</h3>

	<form id="addForm"	enctype="application/x-www-form-urlencoded">
		<p>
			Json String: <input type="text" name="jsonContent" value="{&quot;file&quot;:&quot;1.txt&quot;,&quot;owner&quot;:&quot;jiafu&quot;}"  size=26/>
   	 <p>
    	 Format:
    	 <ul>
   	 	 <li>add single resource, you can use the format <i> <code> {"file":"1.txt","owner":"jiafu"} </code> </i></li>
    	 <li>add multiple resource, you can use the format <i> <code> [{"file":"1.txt","owner":"jiafu"},{"file":"2.txt","owner":"jiafu"}] </code></i></li>
		</ul>
  		    <button>add</button>
 	 </form>



<p>
<hr>
    <h3>Drain All Resource</h3>

    <button id="drain" >drain</button>
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

<p>
<hr>
    <h3>Objects Added Into Pool</h3>

	<button id="listAdded">listAdd</button>
	<p>
	<div id="listAddedDiv"></div>

<p>
<hr>
    <h3>View Operation History</h3>

	<button id="loged">log</button>
	<p>
	<div id="logedDiv"></div>

<p>
<hr>
    <h3>View Resources Usage</h3>

	<button id="logedSort">log</button>
	<p>
	<div id="logedSortDiv"></div>

<p>
<hr>
    <h3>Clean Operation History</h3>

    <button id="clearlog" >clear</button>
<p>

</body>
</html>