<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="true" import="model.Usuario"%>
<%@ page import="model.Cuestionario"%>
<%@ page import="model.Pregunta"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="dao.CuestionarioDAO"%>
<%@ page import="dao.PreguntaDAO"%>
<%
Usuario usuario = (Usuario)session.getAttribute("usuario");

%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css" rel="stylesheet">
  <script>
  $(document).ready(function() {

    	var showitem = "${sessionScope.show}"
  		$('.derecha').hide();
  		$(showitem).toggle();

  });
  </script>
<body>
  <div id="top-nav" class="navbar navbar-inverse navbar-static-top">
      <div class="container-fluid">
          <div class="navbar-header">
              <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
              </button>
          </div>

          <div class="navbar-collapse collapse">
              <ul class="nav navbar-nav navbar-right">
                  <li class="dropdown">
                      <a class="dropdown-toggle" role="button" data-toggle="dropdown" href="#"><i class="fa fa-user-circle"></i> <%=
              			usuario.getId_usu()
             				%> <span class="caret"></span></a>
                      <ul id="g-account-menu" class="dropdown-menu" role="menu">
                          <li><a href="#"><i class="fa fa-user-secret"></i> Perfil </a></li>
                      </ul>
                  </li>
                  <li><a href="/Logout?"><i class="fa fa-sign-out"></i> Logout</a></li>
              </ul>
          </div>
      </div>
      <!-- /container -->
  </div>

  <!-- /Header -->

  <!-- Main -->

  <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">

    <!-- Right -->

    <div class="jumbotron derecha" id="jumbo">
      <div class="container">
        <h1>Bienvenido <c:out value="${sessionScope.usuario.nombres_usu}" />!</h1>
        <p class="text-justify">Este prototipo se desarrolla con el fin de hacer mas fácil la labor de la auditoria de
sistemas apoyando las herramientas tecnológicas con las que se cuenta en las
compañías. Para participar en la auditoria debe ingresar el código del cuestionario a resolver.</p>
        <p>
        <form action="/AdminInterventor" method="get">
          <div class="form-row">
            <div class="form-group">
          <label for="cod">Código:</label>
          <input type="hidden" name="action" value="cuestionario"/>
          <input type="text" name="codigo" required id="cod"/>
          <button class="btn btn-primary btn-lg" type="submit">Buscar</button></p>
        </div>
        </div>
        </form>
      </div>
    </div>

    <!-- Detalles Cuestionario -->
    <div class="col-lg-2 col-md-2 col-sm-3 col-xs-12">
    </div><!-- /span-3 -->
    <div class="col-lg-8 col-md-8 col-sm-7 col-xs-12 derecha" id="cuestionario_read">
    <div class="panel panel-primary">
        <div class="panel-heading">
          <div class="row align-items-center">
          <div class="col-xs-10">
            <h3 class="panel-title"><b> AUDITORIA	- <c:out value="${sessionScope.cuestionario.empresa_cuest}"/>
             </b></h3>
          </div>
        </div>
        </div>
        <div class="panel-body">
        <ul class="list-group">
          <li class="list-group-item">
                  <div class="container">
                      <div class="fluid-row">
                          <div class="col-xs-1">
                              Empresa:
                          </div>
                          <div class="col-xs-5">
                              ${sessionScope.cuestionario.empresa_cuest}
                          </div>
                          <div class="col-xs-1">
                              Dominio:
                          </div>
                          <div class="col-xs-5">
                              ${sessionScope.cuestionario.dominio_cuest}
                          </div>
                          <div class="col-xs-1">
                              Proceso:
                          </div>
                          <div class="col-xs-5">
                              ${sessionScope.cuestionario.proceso_cuest}
                          </div>
                          <div class="col-xs-1">
                              Objetivo:
                          </div>
                          <div class="col-xs-5">
                              ${sessionScope.cuestionario.objetivo_cuest}
                          </div>
                      </div>
                  </div>
          </li>
          <c:set var="contador" value="${1}"/>
          <form action="AdminInterventor" method="get">
          <input type="hidden" name="action" value="resultados"/>
          <input type="hidden" name="id_cuest" value="${sessionScope.cuestionario.id_cuest}"/>
          <input type="hidden" name="id_usu" value="${sessionScope.usuario.id_usu}"/>
          <c:forEach items="${sessionScope.listapreguntas}" var="pregunta">
             <li class="list-group-item">
                 <div class="row toggle" id="dropdown-detail-1" data-toggle="detail-1">
                     <div class="col-xs-10">
 											<h5><b>Pregunta	<c:out value="${contador}"/></b></h5>
 										</div>
                 </div>
                     <hr></hr>
                     <div class="container">
                         <div class="form-row">
                             <div class="form-group col-xs-8 text-justify">
                                <b> ${pregunta.descripcion_preg} </b>
                             </div>
                             <div class="form-group col-xs-2">
                             <label class="radio-inline">
                               <input type="radio" name="respuesta_res<c:out value="${contador}"/>" value="Si">Si
                             </label>
                           </div>
                             <div class="form-group col-xs-2">
                               <label class="radio-inline">
                                 <input type="radio" name="respuesta_res<c:out value="${contador}"/>" value="No" checked>No
                               </label>
                             </div>
                         </div>
                     </div>
             </li>
            <c:set var="contador">${contador+1}</c:set>
          </c:forEach>
          <li class="list-group-item">
          <div class="form-row">
						<div class="form-group text-center">
				  		<button type="submit" class="btn btn-primary"> Enviar </button>
						</div>
				  </div>
         </li>
          </form>
        </ul>
      </div>
      </div>
</div>
</div>
</body>
</html>
