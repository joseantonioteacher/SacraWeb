<%@include file="/html/common/header.jsp"%>
<%@ page import="com.sacra.ecommerce.model.*, com.sacra.ecommerce.service.*, com.sacra.ecommerce.service.impl.*, java.util.*" %>
	<div>
        <%    
        Long n = Long.valueOf(request.getParameter("id"));
        ProductoService productoService = new ProductoServiceImpl();
        Producto e = productoService.findById(n); %>
            <h3><%=e.getNombre()%></h3><br>
            <p><strong>ID:&nbsp;</strong><%=e.getId()%></p><br>
            <p><strong>CategoriaID:&nbsp;</strong><%=e.getIdCategoria()%></p><br>
            <p><strong>ProveedorID:&nbsp;</strong><%=e.getIdProveedor()%></p><br>
            <p><strong>En Stock:&nbsp;</strong><%=e.getUnidadesEnStock()%></p><br>
            <p><strong>En envio:&nbsp;</strong><%=e.getUnidadesEnPedido()%></p><br>
            <p><strong>Precio:&nbsp;</strong><%=e.getPrecioUnitario()%></p><br>
             

     </div>       
<%@include file="/html/common/footer.jsp"%>