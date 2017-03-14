

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>       
   

        <%
           // String variable=request.getParameter("mensaje");
            
            String variable=  (String) session.getAttribute("mensaje");
          
           //Object variable=request.getParameter("mensaje");
            out.print(variable);

        %>
        
    </body>
</html>
