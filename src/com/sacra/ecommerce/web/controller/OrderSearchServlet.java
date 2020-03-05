package com.sacra.ecommerce.web.controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sacra.ecommerce.model.Pedido;
import com.sacra.ecommerce.service.PedidoCriteria;
import com.sacra.ecommerce.service.PedidoService;
import com.sacra.ecommerce.service.impl.PedidoServiceImpl;

/**
 * Servlet implementation class OrderSearchServlet
 */
@WebServlet("/OrderSearchServlet")
public class OrderSearchServlet extends HttpServlet {
	
	private PedidoService pedidoService = null;
	
	
	
    public OrderSearchServlet() {
        super();
         pedidoService = new PedidoServiceImpl();  
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Pedido> pedidos = null;
		PedidoCriteria pc = new PedidoCriteria();
		String pais = request.getParameter("pais");
	    try {	
    	pc.setPaisReceptor(pais);
		if (pais!=null && pais!="") {
			pedidos = pedidoService.findByCriteria(pc,1,10);
			if (pedidos.isEmpty()) {
				request.setAttribute("error", "No hay ning�n pedido fletado a ese pa�s");
			}
			else {
				request.setAttribute("pedidos", pedidos);
			}
		}
		else {
			request.setAttribute("error","No has introducido ning�n pa�s");
		}
		request.getRequestDispatcher("/html/order/order-search.jsp").forward(request, response);
	    }
	    catch (Exception e){
	    	e.printStackTrace();
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
