<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Gestión de videos</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="css/estilos.css" type="text/css" media="all" />
        <script type="text/javascript" src="./js/encriptar.js"></script>


        <script>
            function calcMD5() {

                document.getElementById('passdword').value = md5(document.getElementById('password').value);

            }

            function aux() {
                //document.getElementById('passdword').value = md5(document.getElementById('password').value);

                // alert(md5("pinga"));
                //  alert('si '+md5("pinga"));
                // alert(document.getElementById('passdword').value);
            }
        </script>

    </head>
    <body>

        <div id="contact-form">  
            <h1>Registro usuarios</h1>
            
            <% 
                String mensaje = (String) session.getAttribute("mensaje");
                if(mensaje != null && mensaje != "") {
                    request.getSession().removeAttribute("mensaje");
            %>
            <h3 class="mensajeError"><%=mensaje%></h3>
            <% } %>
            
            <form  action="http://localhost:8080/Gestion_De_Videos/ServletRegistroUsu" method="POST">  
                <label for="name">Nombre: <span class="required">*</span></label>  
                <input type="text" id="name" name="name" value="" placeholder="" required="required" autofocus="autofocus" />

                <label for="name">Apellidos: <span class="required">*</span></label>  
                <input type="text" id="apellidos" name="apellidos" value="" placeholder="" required="required" autofocus="autofocus" /> 

                <label for="email">Correo Electrónico: <span class="required">*</span></label>  
                <input type="email" id="email" name="email" value="" placeholder="your@email.com" required="required" />  

                <label for="name">Nombre usuario: <span class="required">*</span></label>  
                <input type="text" id="usuario" name="usuario" placeholder="" required="required" autofocus="autofocus" /> 

                <label for="name">Contraseña: <span class="required">*</span></label>  
                <input type="password" id="password" name="password"  placeholder="" required="required" autofocus="autofocus" /> 

                <label for="name">Repetir contraseña: <span class="required">*</span></label>  
                <input type="password" id="repassword" name="repassword" value="" placeholder="" required="required" autofocus="autofocus" /> 

                <input type="submit" value="Registrar" id="submit" onclick= ""/>
            </form>
            <a href="login.jsp">Login</a>
        </div>
    </body>
</html>
