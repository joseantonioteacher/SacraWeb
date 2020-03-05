<%@page import="java.util.Locale, com.sacra.ecommerce.model.*, com.sacra.ecommerce.web.model.*, com.sacra.ecommerce.web.util.*, com.sacra.ecommerce.web.controller.*" %>

<%

	User user = (User) SessionManager.get(request, SessionAttributeNames.USER);
	if (user!=null) {
		%>
		<p><%=user.getFirstName() + " "+user.getLastName()%>
		<p><a href="/SacraWeb/user?action=sign-out"><fmt:message key="Salir" bundle="${messages}"/></a></p>
		<%
	} else {
		%>
		<a href="/SacraWeb/html/signin/signin.jsp"><fmt:message key="Autenticarse" bundle="${messages}"/></a>
		<%
	}
%>

