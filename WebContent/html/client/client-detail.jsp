<%@include file="/html/common/header.jsp"%>	
<%@page import="com.sacra.ecommerce.model.*,com.sacra.ecommerce.service.*,com.sacra.ecommerce.service.impl.*,java.util.* " %>
<html>
	<body>		
		<%
			String id = request.getParameter("id");			
			ClienteService clienteService = new ClienteServiceImpl();
			Cliente c = clienteService.findById(id);		
		%>	
		<h3><%=c.getNombre()%></h3>
		<img src="/SacraWeb/images/shippers/<%=id%>-large.jpg" width="240" height="160" alt="<%=c.getNombre()%>"></img>
		<br/>
		<p>Telefono: <%=c.getTelefono()%></p>
<%@include file="/html/common/footer.jsp"%>