<html>
<head>
<script src="scripts/jquery-1.11.0.js"></script>
<title>Common Remote Pool</title>
<link rel="shortcut icon" href="image/favicon.ico" type="image/x-icon" />
<link type="text/css" rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/blitzer/jquery-ui.css" type="text/css" />


<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="scripts/jquery.easy-confirm-dialog.js"></script>
</head>

<script>
$(function(){
$("#drain").easyconfirm({locale: {title: 'Are you sure?', text='if yes, all data deleted' ,button: ['No','Yes']}});
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
$(document).ready(function(){
  $("#drain").click(function(){
      $.get("service/object/drain",function(data,status){
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
</body>
</html>