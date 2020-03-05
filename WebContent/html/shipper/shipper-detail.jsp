<%@include file="/html/common/header.jsp"%>	

<%@page import="com.sacra.ecommerce.web.controller.*" %>

<h3>${transportista.nombre}</h3>

<p><fmt:message key="Telefono" bundle="${messages}"/>${transportista.telefono}</p>

<img src="/SacraWeb/images/shippers/${transportista.id}-large.jpg" width="240" height="160" alt="${transportista.nombre}"></img>

<!-- Enlace de vuelta a la busqueda -->
<c:url var="url" value="<%=ViewsPaths.TRANSPORTISTA_SEARCH%>"/>
<p><a href="${url}"><fmt:message key="Volver" bundle="${messages}"/></a></p>
 
<%@include file="/html/common/footer.jsp"%>