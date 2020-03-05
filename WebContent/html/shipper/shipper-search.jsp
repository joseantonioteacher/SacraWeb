<%@include file="/html/common/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.sacra.ecommerce.model.*,com.sacra.ecommerce.service.*,com.sacra.ecommerce.service.impl.*,java.util.* " %>

<!-- Recordad que en el header hay un errors.jsp para los comunes 
     Si no podría colocarse aqui en otro lugar -->
     
<h3><fmt:message key="Transportistas" bundle="${messages}"/></h3>
		
<!-- Formulario de búsqueda -->		
<form action="<%=request.getContextPath()%>/transportista" method="POST">
	<input type="hidden" name="action" value="search"/>
	
	<input type="text" name="nombre" value="${requestScope.nombre}" placeholder='<fmt:message key="transportista.nombre.ejemplo" bundle="${messages}"/>'/>
	<br>  			
	<input type="submit" value='<fmt:message key="Buscar" bundle="${messages}"/>'>
</form>

<!-- Total de resultados  -->
<p>
	<c:if test="${not empty total}">
		<fmt:message key="Encontrados" bundle="${messages}">
			<fmt:param value="${total}"></fmt:param>
		</fmt:message>		
	</c:if>
</p>

<!-- Resultados -->
<c:if test="${not empty transportistas}">
	<!-- Listado -->
	<ul>
		<c:forEach items="${transportistas}" var="tr">
				
			<li><a href="">${tr.nombre}</a></li>			
		</c:forEach>
	</ul>

	<!-- Paginacion  -->
	<p><center>
		
	<c:url var="urlBase" value="/transportista" scope="page">
		<c:param name="action" value="search"/>
		<c:param name="nombre" value="${nombre}"/>
		<!--  y asi todos los parametros de la busqueda anterior ... -->
	</c:url>

	<!-- A la primera pagina -->
	<c:if test="${page > 1}">
		<a href="${urlBase}&page=1">
			<fmt:message key="Primera" bundle="${messages}"/>
		</a>
		&nbsp;&nbsp;
	</c:if>

	<!-- A la anterior pagina -->
	<c:if test="${page > 1}">
		<a href="${urlBase}&page=${page - 1}">
			<fmt:message key="Anterior" bundle="${messages}"/>
		</a>
		&nbsp;&nbsp;
	</c:if>

	<c:if test="${totalPages > 1}">	
	
		<c:forEach begin="${firstPagedPage}" end="${lastPagedPage}" var="i">
			<c:choose>
			  <c:when test="${page != i}">
					&nbsp;<a href="${urlBase}&page=${i}"><b>${i}</b></a>&nbsp;
			  </c:when>
			  <c:otherwise>
					&nbsp;<b>${i}</b>&nbsp;
			  </c:otherwise>
			</c:choose>
		</c:forEach>

	</c:if>

	<!-- A la siguiente página -->	
	<c:if test="${page < totalPages}">
		&nbsp;&nbsp;		
		<a href="${urlBase}&page=${page + 1}">
			<fmt:message key="Siguiente" bundle="${messages}"/>
		</a>			
	</c:if>	
	<!-- A la ultima página -->
	<c:if test="${page != totalPages}">
		&nbsp;&nbsp;<a href="${urlBase}&page=${totalPages}"><fmt:message key="Ultima" bundle="${messages}"/></a>
	</c:if>		

	</center></p>
	
</c:if>

<%@include file="/html/common/footer.jsp"%>