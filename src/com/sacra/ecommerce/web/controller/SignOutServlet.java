package com.sacra.ecommerce.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet para salir de la sesión. 
 * @deprecated Seria una mala implementacion de MVC. Mejor UserServlet, o incluso un solo "FrontController".
 */
@WebServlet("/SignOutServlet")
public class SignOutServlet extends HttpServlet {
   
    public SignOutServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession(true).setAttribute(SessionAttributeNames.USER, null);
		response.sendRedirect(ViewsPaths.ROOT_CONTEXT);
	}
 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
