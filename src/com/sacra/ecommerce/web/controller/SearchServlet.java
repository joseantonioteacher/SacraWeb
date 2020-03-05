/**
 * 
 */
package com.sacra.ecommerce.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sacra.ecommerce.model.Cliente;
import com.sacra.ecommerce.service.ClienteService;
import com.sacra.ecommerce.service.impl.ClienteServiceImpl;

/**
 * @author usuario
 *
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet{
	
	private ClienteService clienteService = null;
	
	 public SearchServlet () {
	        super();
	        clienteService = new ClienteServiceImpl();

	    }
	 
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String search = request.getParameter("busqueda");
				
			String target = null;
			
			try {
				List<Cliente> clientes = clienteService.findByNombre(search, 1, 7);		
				if (clientes==null ) {
					request.setAttribute("error", "No se ha encontrado ningun cliente");
					target = "/html/client/client-search.jsp";
				} else {	
					target = "/html/client/client-search.jsp";
					request.setAttribute("clientes", clientes);
				}
					request.getRequestDispatcher(target).forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}
}

