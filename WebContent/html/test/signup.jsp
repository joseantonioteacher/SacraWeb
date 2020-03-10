<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="/SacraWeb/js/jquery-3.4.1.min.js"></script>
<script> 
function getProvincias(val) {
	$.ajax({
		type: "GET",
		url: "/SacraWeb/provinciaws",
		data:"m=pais&pais="+val,
		success: function(data){
			console.log(data);
			
			$("#provincia-list").empty();
			// $("#provincia-list").html(data);	 -- si fuese html
			
            for(var i=0;i<data.length;i++){
                $("#provincia-list")
                	.append("<option id='" +data[i].id+"' value='" + data[i].nombre + "'></option>");
            }			
		
			$("#localidad-list").empty();
		}
	});
}

</script>
<title>Insert title here</title>
</head>
<body>

<select name="pais" id="pais-list" onChange="getProvincias(this.value);">
	<option value="-1" label="Seleccione país"/>
	<option value="33" label="Francia" />
	<option value="34" label="España" />
	<option value="35" label="Portugal" />
</select>


<select name="provincia" id="provincia-list" >
	<option value="-1" label="Seleccione país"/>
</select>

<select name="localidad" id="localidad-list" >
	<option value="-1" label="Seleccione provincia"/>
</select>

</body>
</html>