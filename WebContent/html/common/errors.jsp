
<c:if test="${not empty requestScope.error}">
	<fmt:message key="${requestScope.error}" bundle="${messages}"/>	
</c:if>

