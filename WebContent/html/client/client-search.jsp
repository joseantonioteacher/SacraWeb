<%@include file="/html/common/header.jsp"%>
<%@page import="com.sacra.ecommerce.model.*,com.sacra.ecommerce.service.*,com.sacra.ecommerce.service.impl.*,java.util.* " %>
	
	<% 
		String nombreCliente = request.getParameter("busqueda");
		if (nombreCliente==null) {nombreCliente ="";}
	%>
				
	<FORM ACTION="/SacraWeb/SearchServlet" METHOD="POST" >
	            Introduzca el cliente:
	            <BR>
	            <INPUT TYPE="TEXT" NAME="busqueda">
	            <BR>
	            <INPUT TYPE="SUBMIT" value="Submit">
	</FORM>




















	
	<%
		List<Cliente> clientes = (List <Cliente>) request.getAttribute("clientes");		
	%>
	
	<%

		if (nombreCliente!=null & nombreCliente!=""){
			%>
			<h3>Resultados de busqueda:</h3>			<%
			for (Cliente c: clientes) {
				%>
				<ul>
					<li>
						<img src="/SacraWeb/images/clients/2.png" width="40" height="40"></img>							
						<a href="/SacraWeb/html/index.jsp?id=<%=c.getId()%>"><%=c.getNombre()%></a>
					</li>
				</ul>
				<%
			}
		}
		else
		{
			%>
	<% 		
		}			
	%>

<%@include file="/html/common/footer.jsp"%>