package com.sacra.ecommerce.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sacra.ecommerce.model.Transportista;
import com.sacra.ecommerce.service.Results;
import com.sacra.ecommerce.service.TransportistaService;
import com.sacra.ecommerce.service.impl.TransportistaServiceImpl;
import com.sacra.ecommerce.web.config.ConfigurationManager;
import com.sacra.ecommerce.web.config.ConfigurationParameterNames;
import com.sacra.ecommerce.web.util.WebUtils;

/**
 * Controller para acciones sobre transportistas.
 */
@WebServlet("/transportista")
public class TransportistaServlet extends HttpServlet {    

	private static Logger logger = LogManager.getLogger(TransportistaServlet.class.getName());
	
	/**
	 * Tamaño de la página de resultados
	 */
	// Pregunta clase: ¿Como harias para que pudiese cambiarlo el usuario?
	private static int pageSize = Integer.valueOf(
					ConfigurationManager.getInstance().getParameter(
								ConfigurationParameterNames.RESULTS_PAGE_SIZE_DEFAULT)); 
	
	private static int pagingPageCount = Integer.valueOf(
			ConfigurationManager.getInstance().getParameter(
						ConfigurationParameterNames.RESULTS_PAGING_PAGE_COUNT)); 
	
	
	private TransportistaService transportistaService = null;

	public TransportistaServlet() {
		super();
		transportistaService = new TransportistaServiceImpl();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter(ParameterNames.ACTION);

		String target = null;
		boolean redirect = false;		

		if (Actions.SEARCH.equalsIgnoreCase(action)) {
			
			String nombre = request.getParameter(ParameterNames.NOMBRE);			
			nombre = StringUtils.trimToEmpty(nombre);
			
			// Validaciones
			boolean hasErrors = false;
			// if ... setAttribute del error(es)
			// ...
			if (hasErrors) {
				// forward a la misma view para que muestre los errores
				// y no se ejecuta la funcionalidad
				target = ViewsPaths.TRANSPORTISTA_SEARCH;
			} else {
			 	try {
			 		
			 		// Pagina solicitada por el usuario (o por defecto la primera
			 		// cuando todavia no ha usado el paginador)
					int page = WebUtils.
							getPageNumber(request.getParameter(ParameterNames.PAGE), 1);
					
					
			 		// Invocamos la funcionalidad en la capa de negocio
			 		Results<Transportista> results = transportistaService
			 				.findByNombre(nombre, (page-1)*pageSize+1, pageSize);
			 		
			 		// Resultados de la busqueda (siempre preparar comodos para renderizar)
					request.setAttribute(AttributeNames.TRANSPORTISTAS, results.getPage());
					request.setAttribute(AttributeNames.TOTAL, results.getTotal());

					// Datos para paginacion															
					// (Calculos aqui, datos comodos para renderizar)
					int totalPages = (int) Math.ceil((double)results.getTotal()/(double)pageSize);
					int firstPagedPage = Math.max(1, page-pagingPageCount);
					int lastPagedPage = Math.min(totalPages, page+pagingPageCount);
					request.setAttribute(ParameterNames.PAGE, page);
					request.setAttribute(AttributeNames.TOTAL_PAGES, totalPages);
					request.setAttribute(AttributeNames.FIRST_PAGED_PAGES, firstPagedPage);
					request.setAttribute(AttributeNames.LAST_PAGED_PAGES, lastPagedPage);
					
					
					// Parametros de busqueda actuales
					request.setAttribute(ParameterNames.NOMBRE, nombre);

					
					target = ViewsPaths.TRANSPORTISTA_SEARCH;
				} catch (Exception e) {    			
					logger.error(e.getMessage(), e);
					request.setAttribute(AttributeNames.ERROR, Errors.GENERIC_ERROR);
					target = ViewsPaths.TRANSPORTISTA_SEARCH;
				}
			}
			
		} else if (Actions.DETAIL.equalsIgnoreCase(action)) {
			
			String idPValue= request.getParameter(ParameterNames.ID);			
			try {
				Long id = Long.valueOf(idPValue);
				Transportista t = transportistaService.findById(id);
				request.setAttribute(AttributeNames.TRANSPORTISTA, t);				
				target = ViewsPaths.TRANSPORTISTA_DETAIL;				
			} catch (NumberFormatException nfe) {
				logger.error(idPValue, nfe);
				// request.setAttribute(AttributeNames.ERROR, Errors.NUMBER_FORMAT_ERROR);
				request.setAttribute(AttributeNames.ERROR, Errors.UNKNOWN_ID);				
				target = ViewsPaths.TRANSPORTISTA_SEARCH;
			} catch (Exception e) {    			
				logger.error(e.getMessage(), e);
				request.setAttribute(AttributeNames.ERROR, Errors.GENERIC_ERROR);
				target = ViewsPaths.TRANSPORTISTA_SEARCH;
			}				
		//} else if (Actions.....equalsIgnoreCase(action)) {
			
		//} else if (Actions.....equalsIgnoreCase(action)) {			
			
		} else {
			logger.warn("Security issue or bug: Unknown action: "+action);
			target = request.getContextPath();
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
