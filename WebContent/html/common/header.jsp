<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="com.sacra.ecommerce.model.*" %>
<%@page import="com.sacra.ecommerce.web.util.*" %>

<fmt:setLocale value='${sessionScope["user-locale"]}' scope="session"/>
<fmt:setBundle basename = "resources.Messages" var = "messages" scope="session"/>

 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<!-- imports de css, js -->
		<link rel="stylesheet" type="text/css" href="/SacraWeb/css/sacra.css">
	</head>
	<body>
		<div class="header">

			<a href="/SacraWeb/html/index.jsp">
				<img src="/SacraWeb/images/static/sacra-logo.jpg" height="60" width="60"></img>
				<fmt:message key="Titulo" bundle="${messages}"/>
							
			</a>
			
			<!-- Seleccion de idioma -->
			<div class="languages">			
			<%@include file="/html/user/languages.jsp"%>						
			</div>	
				
			<!-- Carrito -->
			<div class="shopping-cart">			
			<%@include file="/html/cart/shopping-cart-resume.jsp"%>						
			</div>				
				
			<!-- Avatar de usuario -->
			<div class="user-data">			
			<%@include file="/html/user/user-profile.jsp"%>						
			</div>
				
		</div>
		
		<div class="bar">			
			<!-- Barra de navegación, Categorías, o similar -->
			<!-- Pregunta: Por qué no salen del bundle estos literales? De donde deben salir? -->
			<p>
			<a href="/SacraWeb/html/shipper/shipper-search.jsp">Transportistas</a> | Juegos | Hoteles | Vuelos | Música
			 </p>
		</div>
		
		
		<%@include file="/html/common/errors.jsp"%>
		
		<!-- Inicio del frame / tile / sección de contenido -->
		<div class="content">
		