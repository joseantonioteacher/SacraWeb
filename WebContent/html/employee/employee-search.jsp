<%@include file="/html/common/header.jsp"%>
<%@ page import="com.sacra.ecommerce.model.*, com.sacra.ecommerce.service.*, com.sacra.ecommerce.service.impl.*, java.util.*" %>

            <% 
            String nombreEmpleado = request.getParameter("nombre");
            if(nombreEmpleado==null) {nombreEmpleado="";}
            %>
        <h3>Busqueda</h3>
        <form action="/SacraWeb/EmployeeServlet" method="post">
            <input type="text" name="nombre" placeholder="Nombre del empleado"> <br>
            <input type="submit" value="submit">
        </form>
        <ul>
         	<% 	if (nombreEmpleado!=null && nombreEmpleado!=""){
                		List<Empleado> empleados = (List<Empleado>) request.getAttribute("empleados");
                        for (Empleado e: empleados){
                            %><li><a href="/SacraWeb/html/employee/employee-details.jsp?id=<%=e.getId()%>"><%=e.getNombre()%></a></li><%
                        }
                }
         	else{
         		%><p><%=request.getAttribute("error")%></p><%
         	}
      		%>
            </ul>
<%@include file="/html/common/footer.jsp"%>