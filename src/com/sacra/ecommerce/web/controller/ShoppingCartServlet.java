package com.sacra.ecommerce.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sacra.ecommerce.web.model.ShoppingCart;
import com.sacra.ecommerce.web.util.SessionManager;


@WebServlet("/ShoppingCartServlet")
public class ShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShoppingCartServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*String productId = request.getParameter(.productId..)
		Producto p = findBuyId		
		
		ShoppingCartLine l = new ShoppingCartLine();
		l.setProducto(p);
		ShoppingCart c = (ShoppingCart) SessionManager.get(request, SessionAttributeNames.SHOPPING_CART);
		if (c==null) {
			c = new ShoppingCart();
			SessionManager.set(request, SessionAttributeNames.SHOPPING_CART, c);			
		}
		
		c.add(l);
		//forward("")
		 * 
		 */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
