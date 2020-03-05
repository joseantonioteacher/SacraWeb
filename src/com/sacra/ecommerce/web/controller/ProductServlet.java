package com.sacra.ecommerce.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sacra.ecommerce.model.Producto;
import com.sacra.ecommerce.model.User;
import com.sacra.ecommerce.service.ProductoService;
import com.sacra.ecommerce.service.UserService;
import com.sacra.ecommerce.service.impl.ProductoServiceImpl;
import com.sacra.ecommerce.service.impl.MockUserServiceImpl;
import com.sacra.ecommerce.util.PasswordEncryptionUtil;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	private ProductoService productoService = null;
	
    public ProductServlet() {
        super();
        
        productoService = new ProductoServiceImpl();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombreProducto = request.getParameter("nombre");
		
		String target = null;
		
		try {
			List<Producto> productos = productoService.findByNombre(nombreProducto,1,Integer.MAX_VALUE);			
			if (nombreProducto==null || nombreProducto=="") {
				request.setAttribute("error", "Introduce datos de busqueda");
				target = "/html/product/product-search.jsp";
			} else {				
					request.setAttribute("productos", productos);			
					target = "/html/product/product-search.jsp";
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