<%@include file="/html/common/header.jsp"%>

<h3><fmt:message key="Autenticarse" bundle="${messages}"/></h3>

<form action="/SacraWeb/user" method="post">
	<input type="hidden" name="action" value="<%=Actions.SIGN_IN%>"/>
	
	<!-- Campo usuario, con sus errores antes  -->
	<c:if test="${not empty requestScope.error_user}">
		<p><fmt:message key="${requestScope.error_user}" bundle="${messages}"/></p>
	</c:if>
	<input type="text" name="user" placeholder="<%=CookieManager.getCookie(request, "login")%>"/>
	<br/>		
	
	<!-- Campo password, con sus errores antes  -->
	<c:if test="${not empty requestScope.error_password}">
		<p><fmt:message key="${requestScope.error_password}" bundle="${messages}"/></p>
	</c:if>		
	<input type="password" name="password" placeholder="<fmt:message key="Password" bundle="${messages}"/>"/>		
  	<br/>
	
	<input type="submit" value="<fmt:message key="Enviar" bundle="${messages}"/>">	
</form>

<%@include file="/html/common/footer.jsp"%>