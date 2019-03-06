<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="model.Usuario"%>
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
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="static/css/auditor.css">
<script>
$(document).ready(function() {

  	var showitem = "${sessionScope.show}"
		$('.derecha').hide();
		$(showitem).toggle();
    $('#auditoria').click(function() {
			 $('.derecha').hide();
       $('#auditoria_read').toggle();
    });
		$('#agregarcuest').click(function() {
			$('.derecha').hide();
			$('#formulario_cuest').toggle();
		});
		$('#agregarpreg').click(function() {
			$('.derecha').hide();
			$('#formulario_preg').toggle();
		});
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
            <a class="navbar-brand" href="#"><b><%=
            "Bienvenido "+usuario.getNombres_usu()+" "+usuario.getApellidos_usu()
            %></b></a>
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

<div class="col-lg-2 col-md-2 col-sm-3 col-xs-12">

    <ul class="nav nav-pills nav-stacked" style="border-right:1px solid black">
        <!--<li class="nav-header"></li>-->
        <li><a href="#" id="auditoria"><i class="fa fa-dashboard"></i> Auditorias </a></li>
        <li><a href="/AdminAuditoria?action=reporte" id="reportes"><i class="fa fa-line-chart"></i> Reportes </a></li>
    </ul>
</div><!-- /span-3 -->
<div class="col-lg-10 col-md-10 col-sm-9 col-xs-12">

    <!-- Right -->
		<!-- Auditorias -->
		<%
		CuestionarioDAO cuestionarioDAO = new CuestionarioDAO();
		List<Cuestionario> listaCuestionarios = cuestionarioDAO.listarCuestionarios();
		session.setAttribute("listacuest",listaCuestionarios);
		%>
    <div class="panel panel-info derecha" id="auditoria_read">
        <div class="panel-heading">
					<div class="row align-items-center">
					<div class="col-xs-8">
            <h3 class="panel-title"><b>AUDITORIAS ACTIVAS</b></h3>
					</div>
					<div class="col-xs-2">
						<a class="btn icon-btn btn-info" id="agregarcuest"><span class="glyphicon btn-glyphicon glyphicon-plus img-circle text-info"></span>Auditoria</a>
					</div>
					<div class="col-xs-2">
						<a class="btn icon-btn btn-info" id="agregarpreg"><span class="glyphicon btn-glyphicon glyphicon-plus img-circle text-info"></span>Pregunta</a>
					</div>
				</div>
        </div>
				<div class="panel-body">
        <ul class="list-group">
					<c:forEach items="${sessionScope.listacuest}" var="cuestionario">
            <li class="list-group-item">
                <div class="row toggle" id="dropdown-detail-1" data-toggle="detail-1">
                    <div class="col-xs-8">
											<h5><b> Auditoria	<c:out value="${cuestionario.id_cuest}"/> - Codigo Cuestionario: <c:out value="${cuestionario.codigo_cuest}"/>
										   </b>
										 </h5>
										</div>
										<div class="col-xs-2">
											<form action="/AdminAuditoria" method="get">
												<input type="hidden" name="action" value="buscar" />
												<input type="hidden" name="idcuest" value="<c:out value="${cuestionario.id_cuest}"/>" />
												<input type="hidden" name="codigocuest" value="<c:out value="${cuestionario.codigo_cuest}"/>" />
											<button type="submit" class="btn icon-btn btn-info">
												<span class="glyphicon btn-glyphicon glyphicon-search img-circle text-info">
												</span>Buscar
											</button>
										 </form>
										</div>
										<div class="col-xs-2">
											<form action="/AdminAuditoria" method="get">
												<input type="hidden" name="action" value="eliminar" />
												<input type="hidden" name="id_cuest" value="<c:out value="${cuestionario.id_cuest}"/>" />
											<button type="submit" class="btn icon-btn btn-info">
												<span class="glyphicon btn-glyphicon glyphicon-trash img-circle text-info">
												</span>Eliminar
											</button>
										 </form>
									</div>
                </div>
                <div id="detail-1">
                    <hr></hr>
                    <div class="container">
                        <div class="fluid-row">
                            <div class="col-xs-1">
                                Empresa:
                            </div>
                            <div class="col-xs-5">
                                ${cuestionario.empresa_cuest}
                            </div>
                            <div class="col-xs-1">
                                Dominio:
                            </div>
                            <div class="col-xs-5">
                                ${cuestionario.dominio_cuest}
                            </div>
                            <div class="col-xs-1">
                                Proceso:
                            </div>
                            <div class="col-xs-5">
                                ${cuestionario.proceso_cuest}
                            </div>
                            <div class="col-xs-1">
                                Objetivo:
                            </div>
                            <div class="col-xs-5">
                                ${cuestionario.objetivo_cuest}
                            </div>
                        </div>
                    </div>
                </div>
            </li>
						</c:forEach>
        </ul>
			</div>
			</div>

			<!-- Formulario Cuestionarios -->

			<div class="panel panel-primary derecha" id="formulario_cuest">
					<div class="panel-heading">
							<h3 class="panel-title"><b>CREAR AUDITORIA</b></h3>
					</div>
					<div class="panel-body">
			  <form action="/CuestionarioFormulario" method="post">
				<div class="form-row">
					<div class="form-group col-xs-6">
						<label for="codigo">Codigo:</label>
						<input type="text" class="form-control" name="codigo" id="codigo">
					</div>
					<div class="form-group col-xs-6">
				    <label for="empresa">Empresa:</label>
				    <input type="text" class="form-control" name="empresa" id="empresa">
				  </div>
				</div>
				<div class="form-row">
					<div class="form-group col-xs-6">
				    <label for="dominio">Dominio:</label>
				    <input type="text" class="form-control" name="dominio" id="dominio">
				  </div>
					<div class="form-group col-xs-6">
				    <label for="proceso">Proceso:</label>
				    <input type="text" class="form-control" name="proceso" id="proceso">
				  </div>
				</div>
				<div class="form-row">
					<div class="form-group col-xs-6">
				    <label for="objetivo">Objetivo:</label>
				    <input type="text" class="form-control" name="objetivo" id="objetivo">
				  </div>
					<div class="clearfix visible-xs-block">
					</div>
				</div>
				<div class="form-row">
					<div class="form-group text-center">
			  		<button type="submit" class="btn btn-primary">Agregar</button>
					</div>
			  </div>
			</form>
		</div>
		</div>

		<!-- Formulario Preguntas -->
		<div class="panel panel-primary derecha" id="formulario_preg">
			<div class="panel-heading">
					<h3 class="panel-title"><b>AGREGAR PREGUNTA</b></h3>
			</div>
			<div class="panel-body">
				<form action="/PreguntaFormulario" method="post">
					<div class="form-row">
						<div class="form-group col-xs-3">
							<label for="sel1">Seleccione la Auditoria:</label>
							<select class="form-control" name="id_cuest" id="sel1" required>
								<c:forEach items="${sessionScope.listacuest}" var="cuestionario">
	 							 <option value="<c:out value="${cuestionario.id_cuest}"/>"><c:out value="${cuestionario.id_cuest}"/> - <c:out value="${cuestionario.codigo_cuest}"/>
								 </option>
								</c:forEach>
 							</select>
						</div>
						<div class="form-group col-xs-9">
						  <label for="pregunta">Pregunta:</label>
						  <textarea class="form-control" name="descripcion" rows="5" id="pregunta"></textarea>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group text-center">
				  		<button type="submit" class="btn btn-primary">Agregar</button>
						</div>
				  </div>
				</form>
			</div>
		</div>

		<!-- Detalles Cuestionario -->

		<div class="panel panel-info derecha" id="detalles_read">
				<div class="panel-heading">
					<div class="row align-items-center">
					<div class="col-xs-10">
						<h3 class="panel-title"><b> Auditoria	<c:out value="${sessionScope.cuestemp.id_cuest}"/> - Codigo Cuestionario: <c:out value="${sessionScope.cuestemp.codigo_cuest}"/>
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
															${sessionScope.cuestemp.empresa_cuest}
													</div>
													<div class="col-xs-1">
															Dominio:
													</div>
													<div class="col-xs-5">
															${sessionScope.cuestemp.dominio_cuest}
													</div>
													<div class="col-xs-1">
															Proceso:
													</div>
													<div class="col-xs-5">
															${sessionScope.cuestemp.proceso_cuest}
													</div>
													<div class="col-xs-1">
															Objetivo:
													</div>
													<div class="col-xs-5">
															${sessionScope.cuestemp.objetivo_cuest}
													</div>
											</div>
									</div>
					</li>
					<c:set var="contador" value="${1}"/>
					<c:forEach items="${sessionScope.listapreg}" var="pregunta">
						<li class="list-group-item">
                <div class="row toggle" id="dropdown-detail-1" data-toggle="detail-1">
                    <div class="col-xs-10">
											<h5><b>Pregunta	<c:out value="${contador}"/></b></h5>
										</div>
										<form action="/AdminPreguntas" method="get">
											<input type="hidden" name="action" value="eliminar" />
											<input type="hidden" name="idcuest" value="<c:out value="${sessionScope.cuestemp.id_cuest}"/>" />
											<input type="hidden" name="codigocuest" value="<c:out value="${sessionScope.cuestemp.codigo_cuest}"/>" />
											<input type="hidden" name="id_preg" value="<c:out value="${pregunta.id_preg}"/>" />
										<button type="submit" class="btn icon-btn btn-info">
											<span class="glyphicon btn-glyphicon glyphicon-trash img-circle text-info">
											</span>Eliminar
										</button>
									 </form>
                </div>
                    <hr></hr>
                    <div class="container">
                        <div class="form-row">
                            <div class="form-group col-xs-12 text-justify">
                                ${pregunta.descripcion_preg}
                            </div>
                        </div>
                    </div>
            </li>
						<c:set var="contador">${contador+1}</c:set>
					</c:forEach>
				</ul>
			</div>
			</div>

			<!-- Reportes -->

			<div class="panel panel-success derecha" id="reportes_read">
					<div class="panel-heading">
						<div class="row align-items-center">
						<div class="col-xs-10">
							<h3 class="panel-title">
								<b>
									REPORTE RESUMEN
							  </b>
							</h3>
						</div>
					</div>
					</div>
					<div class="panel-body">
						<table class="table">
						  <thead>
						    <tr>
									<th scope="col">#</th>
						      <th scope="col">Interventor</th>
						      <th scope="col">Codigo Auditoría</th>
						      <th scope="col"># Sí</th>
									<th scope="col"># No</th>
						    </tr>
						  </thead>
						  <tbody>
								<c:set var="contador" value="${1}"/>
								<c:forEach items="${sessionScope.listareportes}" var="reporte">
						    <tr>
						      <th scope="row">${contador}</th>
						      <td>${reporte.interventor_reporte}</td>
						      <td>${reporte.codigo_reporte}</td>
						      <td>${reporte.positive}</td>
									<td>${reporte.negative}</td>
						    </tr>
								<c:set var="contador">${contador+1}</c:set>
							</c:forEach>
						  </tbody>
						</table>
					</div>
					</div>
</div>
</body>
</html>
