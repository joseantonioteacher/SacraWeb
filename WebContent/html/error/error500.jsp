
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value='${sessionScope["user-locale"]}' scope="session"/>
<fmt:setBundle basename = "resources.Messages" var = "messages" scope="session"/>

<!-- Explicar por que no incluir cabeza (header.jsp) y pie (footer.jsp) 
     en error 500 -->
     
<h3><fmt:message key="error.500.title" bundle="${messages}"/></h3>

<p><fmt:message key="error.500.desc" bundle="${messages}"/></p>

<!-- cambiar por c:url -->
<p><a href="/"><fmt:message key="Continuar" bundle="${messages}"/></a></p>

