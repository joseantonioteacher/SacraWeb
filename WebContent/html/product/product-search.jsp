<%@include file="/html/common/header.jsp"%>
<%@ page import="com.sacra.ecommerce.model.*, com.sacra.ecommerce.service.*, com.sacra.ecommerce.service.impl.*, java.util.*" %>

            <% 
            String nombreProducto = request.getParameter("nombre");
            if(nombreProducto==null) {nombreProducto="";}
            %>
        <h3>Busqueda de productos</h3>
        <form action="/SacraWeb/ProductServlet" method="post">
            <input type="text" name="nombre" placeholder="Nombre del producto"> <br>
            <input type="submit" value="submit">
        </form>
        <ul>
         	<% 	if (nombreProducto!=null && nombreProducto!=""){
                		List<Producto> productos = (List<Producto>) request.getAttribute("productos");
                        for (Producto e: productos){
                            %><li><a href="/SacraWeb/html/product/product-details.jsp?id=<%=e.getId()%>"><%=e.getNombre()%></a></li><%
                        }
                }
         	else{
         		%><p><%=request.getAttribute("error")%></p><%
         	}
      		%>
            </ul>
<%@include file="/html/common/footer.jsp"%>