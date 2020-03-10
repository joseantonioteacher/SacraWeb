package com.sacra.ecommerce.web.service.json;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.sacra.ecommerce.exceptions.DataException;
import com.sacra.ecommerce.service.ProvinciaService;
import com.sacra.ecommerce.service.impl.MockProvinciaServiceImpl;




@WebServlet("/provinciaws")
public class ProvinciaWebServiceServlet extends HttpServlet {
	private static Logger logger = LogManager.getLogger(ProvinciaWebServiceServlet.class.getName());

	private ProvinciaService provinciaService = null;

	public ProvinciaWebServiceServlet() {
		super();
		provinciaService = new MockProvinciaServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Object result = null;
			
			String method = request.getParameter("m");
			if ("nombre".equalsIgnoreCase(method)) {
				String nombre = request.getParameter("nombre");

				// validaciones 
				// ...
				logger.debug("Buscando provincias con {}", nombre);				
				result = provinciaService.findByNombre(nombre);				
				
			} else if ("pais".equalsIgnoreCase(method)) {				
				String pais = request.getParameter("pais");								
				// validaciones
				// ...
				Long idPais = Long.valueOf(pais);				
				logger.debug("Buscando provincias pais {}",  pais);				
				result = provinciaService.findByPais(idPais);
				
			} else {
				logger.warn("Unknown requested method: {}", method);
			}

			
			// Serializo 
			// MAL, pobrecito GC
			Gson gson = new Gson();				
			String json = gson.toJson(result);				
			logger.debug(json);

			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("ISO-8859-1");
			out.print(json);
			out.flush();		
			
		} catch (DataException de) {
			logger.error(de);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			logger.error(request.getParameterMap());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
