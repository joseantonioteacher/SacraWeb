<%@page import="com.sacra.ecommerce.web.model.*, com.sacra.ecommerce.web.controller.*, com.sacra.ecommerce.web.util.*" %>
<%

	ShoppingCart c = (ShoppingCart) SessionManager.get(request,SessionAttributeNames.SHOPPING_CART);

%>
<a href="/SacraWeb/html/shopping-cart-detail.jsp">
	<img src="/SacraWeb/images/static/shopping-cart.png" height="40" width="40"></img>
</a>
<p><b><%=c.getLines().size()%><fmt:message key="productos" bundle="${messages}"/></b></p> 
