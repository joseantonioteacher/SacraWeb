package com.sacra.ecommerce.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sacra.ecommerce.model.User;
import com.sacra.ecommerce.service.UserService;
import com.sacra.ecommerce.service.impl.MockUserServiceImpl;
import com.sacra.ecommerce.util.PasswordEncryptionUtil;
import com.sacra.ecommerce.web.util.CookieManager;
import com.sacra.ecommerce.web.util.LocaleManager;
import com.sacra.ecommerce.web.util.SessionManager;
import com.sacra.ecommerce.web.util.WebConstants;
import com.sacra.exceptions.MyCompanyException;

/**
 * Servlet para Autentification.
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {    

	private static Logger logger = LogManager.getLogger(UserServlet.class.getName());

	private UserService userService = null;

	public UserServlet() {
		super();
		userService = new MockUserServiceImpl();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter(ParameterNames.ACTION);

		String target = null;
		boolean redirect = false;		

		if (Actions.SIGN_IN.equalsIgnoreCase(action)) {
			String userName = request.getParameter(ParameterNames.USER);
			String password = request.getParameter(ParameterNames.PASSWORD);

			boolean hasErrors = false;

			if (StringUtils.isEmpty(userName)) {
				hasErrors = true;
				request.setAttribute(AttributeNames.ERROR_USER, Errors.REQUIRED_FIELD);
			}
			if (StringUtils.isEmpty(password)) {
				hasErrors = true;
				request.setAttribute(AttributeNames.ERROR_PASSWORD, Errors.REQUIRED_FIELD);
			}

			if (hasErrors) {
				// Si hay errores.. se los muestro y no continuo
				target = ViewsPaths.SIGN_IN;
			} else {
				// Si los parametros están bien, sigo...
				try {
					User user = userService.findUserById(userName);		

					if (user==null) {
						request.setAttribute(AttributeNames.ERROR, Errors.USER_NOT_FOUND);
						target = ViewsPaths.SIGN_IN;
					} else {				
						if (!PasswordEncryptionUtil.checkPassword(password,user.getEncryptedPassword())) {
							request.setAttribute(AttributeNames.ERROR, Errors.INCORRECT_PASSWORD);			
							target = ViewsPaths.SIGN_IN;
						} else {
							SessionManager.set(request, SessionAttributeNames.USER, user);
							CookieManager.addCookie(response, "login", user.getEmail(), "/", 7*24*60*60);
							target = ViewsPaths.INDEX;
							// Explicar edirect aqui vs forward
							redirect = true;
						}
					}

				} catch (Exception e) {    			
					logger.error(e.getMessage(), e);
					request.setAttribute(AttributeNames.ERROR, Errors.GENERIC_ERROR);
					target = ViewsPaths.SIGN_IN;
				}
			}
		} else if (Actions.SIGN_OUT.equalsIgnoreCase(action)) {
			SessionManager.set(request, SessionAttributeNames.USER, null);
			target = ViewsPaths.ROOT_CONTEXT;
			redirect = true;
		} else if (Actions.CHANGE_LOCALE.equalsIgnoreCase(action)) {
			String localeName = request.getParameter(ParameterNames.LOCALE); 
			// Recordar que hay que validar... lo que nos envian, incluso en algo como esto.
			// Buscamos entre los Locale soportados por la web:
			List<Locale> results = LocaleManager.getMatchedLocales(localeName);
			Locale newLocale = null;  
			if (results.size()>0) {
				newLocale = results.get(0);
			} else {
				logger.warn("Request locale not supported: "+localeName);
				newLocale = LocaleManager.getDefault();
			}

			SessionManager.set(request, WebConstants.USER_LOCALE, newLocale);			
			CookieManager.addCookie(response, WebConstants.USER_LOCALE, newLocale.toString(), "/", 365*24*60*60);

			if (logger.isDebugEnabled()) {
				logger.debug("Locale changed to "+newLocale);
			}

			target = request.getContextPath(); // Ejercicio: como hacer que siga en la misma URL		
			redirect = true;

		} else {
			logger.error("Unknown action: "+action);
			target = ViewsPaths.ROOT_CONTEXT;
			redirect = true;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Action "+action+" processed: target = "+target+", redirect = "+redirect);
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
