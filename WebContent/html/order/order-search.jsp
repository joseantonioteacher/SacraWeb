<%@page import="com.sacra.ecommerce.model.*, java.util.*"%>
<%@include file="/html/common/header.jsp"%>
<h3>B�squeda de pedido por Pa�s</h3>
<!-- Formulario de b�squeda -->
<form action="/SacraWeb/OrderSearchServlet" method="POST">
	<input type="text" name="pais" placeholder="Pa�s" /> <br>
	<input type="submit" value="Submit">
</form>
<!-- Busca y muestra los resultados -->
<%	
		List<Pedido> pedidos = (List<Pedido>) request.getAttribute("pedidos");
		
		if (pedidos!=null) {
			%><h3>Resultados de b�squeda:</h3><% 
			for (Pedido p: pedidos){
			
				%>

<p><%=p.getIdPedido()%></p>
<p><%=p.getFechaEnvio()%></p>
<%
			}
		}
			else {
				if (request.getAttribute("error")!=null){
				%>
<p><%=request.getAttribute("error")%></p>
<%
				}
			}
			%>
<%@include file="/html/common/footer.jsp"%>