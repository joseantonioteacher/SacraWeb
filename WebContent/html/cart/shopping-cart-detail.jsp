<%@include file="/html/common/header.jsp"%>
<%@page import="com.sacra.ecommerce.web.model.*, com.sacra.ecommerce.web.controller.*, com.sacra.ecommerce.web.util.*" %>

<h3><fmt:message key="Carrito" bundle="${messages}"/></h3>

<ul>
<%

	ShoppingCart cart = (ShoppingCart) SessionManager.get(request,SessionAttributeNames.SHOPPING_CART);
	for (ShoppingCartLine l: cart.getLines()) {
		%>
		<li><%=l.getProduct().getNombre()%>: <%=l.getUnits()%></li>
		<% 
	}
%>
</ul>


<%@include file="/html/common/footer.jsp"%>