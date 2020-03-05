package com.sacra.ecommerce.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sacra.ecommerce.model.User;
import com.sacra.ecommerce.service.UserService;
import com.sacra.ecommerce.service.impl.MockUserServiceImpl;
import com.sacra.ecommerce.util.PasswordEncryptionUtil;
import com.sacra.ecommerce.web.util.CookieManager;
import com.sacra.ecommerce.web.util.SessionManager;

/**
 * Servlet para Autentification.
 * @deprecated Seria una mala implementacion de MVC.  
 * * @deprecated Seria una mala implementacion de MVC. Mejor UserServlet, o incluso un solo "FrontController".
 */
@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {    
	
	private static Logger logger = LogManager.getLogger(SignInServlet.class.getName());
	
	private UserService userService = null;
	
    public SignInServlet() {
        super();
        userService = new MockUserServiceImpl();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter(ParameterNames.USER);
		String password = request.getParameter(ParameterNames.PASSWORD);
					
		String target = null;
		boolean redirect = false;

		try {
			User user = userService.findUserById(userName);		

			if (user==null) {
				request.setAttribute(AttributeNames.ERROR, AttributeNames.USER_NOT_FOUND_ERROR);
				target = ViewsPaths.SIGN_IN;
			} else {				
				if (!PasswordEncryptionUtil.checkPassword(password,user.getEncryptedPassword())) {
					request.setAttribute(AttributeNames.ERROR, "Contraseña incorrecta");			
					target = ViewsPaths.SIGN_IN;
				} else {
					SessionManager.set(request, SessionAttributeNames.USER, user);
					CookieManager.addCookie(response, "login", user.getEmail(), "/", 7*24*60*60);
					target = ViewsPaths.INDEX;
					// Explicar mejor redirect aqui y por que
					redirect = true;
				}
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			request.setAttribute(AttributeNames.ERROR, e.getMessage());
			target = ViewsPaths.SIGN_IN;
		}
		
		if (redirect) {
			response.sendRedirect(target);
		} else {
			request.getRequestDispatcher(target).forward(request, response);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
