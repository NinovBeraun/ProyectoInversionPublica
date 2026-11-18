<%@page import="entidades.Proyecto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Proyectos de Inversión Pública</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Comic+Neue:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
    	font-family: 'Comic Neue', 'Comic Sans MS', 'Chalkboard SE', sans-serif;
    	font-size: 1.05rem;
    	background-color: #ffffff;
    	color: #3d3a35;
		}
        h4 {
            font-weight: 700;
            color: #5a4a3a;
        }
        .card {
   		 border: none;
    	 border-radius: 16px;
  		 background-color: #ffffff;
   		 box-shadow: 0 4px 14px rgba(90, 74, 58, 0.12) !important;
		}
        .form-control {
            font-family: inherit;
            border: 2px solid #e6dccb;
            border-radius: 12px;
            background-color: #fffdf8;
        }
        /*.form-control:focus {
            border-color: #7fb08a;
            box-shadow: 0 0 0 0.2rem rgba(127, 176, 138, 0.25);
        }
        .btn {
            font-family: inherit;
            font-weight: 700;
            border-radius: 12px;
        }
        .btn-primary {
            background-color: #5f9c72;
            border-color: #5f9c72;
        }
        .btn-primary:hover {
            background-color: #4e8561;
            border-color: #4e8561;
        }
        .table-light th {
            background-color: #f1e8d8;
        }
    </style>
</head>
<body>

<%
    Proyecto p = (Proyecto) request.getAttribute("proyecto");
    Proyecto consulta = (Proyecto) request.getAttribute("consulta");
    String error = (String) request.getAttribute("error");
    List<Proyecto> lista = (List<Proyecto>) request.getAttribute("proyectos");
    boolean editando = (p != null);
%>

<div class="container mt-5">
    <div class="row">

        <div class="col-md-4">
            <div class="card p-4 shadow-sm">
                <h4 class="mb-3"><%= editando ? "Editar Proyecto" : "Registrar Proyecto" %></h4>

                <% if (error != null) { %>
                    <div class="alert alert-danger"><%= error %></div>
                <% } %>

                <form method="post" action="ProyectoServlet">
                    <input type="hidden" name="accion" value="<%= editando ? "actualizar" : "registrar" %>">
                    <% if (editando) { %>
                        <input type="hidden" name="id" value="<%= p.getId() %>">
                    <% } %>

                    <div class="mb-3">
                        <label for="nombreProyecto" class="form-label">Nombre del proyecto</label>
                        <input id="nombreProyecto" name="nombreProyecto" type="text" class="form-control"
                               required minlength="4" maxlength="60"
                               value="<%= editando ? p.getNombreProyecto() : "" %>">
                    </div>

                    <div class="mb-3">
                        <label for="descripcionTecnica" class="form-label">Descripción técnica</label>
                        <textarea id="descripcionTecnica" name="descripcionTecnica" class="form-control"
                                  rows="3"><%= editando ? p.getDescripcionTecnica() : "" %></textarea>
                    </div>

                    <div class="mb-3">
                        <label for="fechaInicio" class="form-label">Fecha de inicio</label>
                        <input id="fechaInicio" name="fechaInicio" type="date" class="form-control" required
                               value="<%= editando ? p.getFechaInicio() : "" %>">
                    </div>

                    <div class="mb-3">
                        <label for="fechaCulminacion" class="form-label">Fecha de culminación</label>
                        <input id="fechaCulminacion" name="fechaCulminacion" type="date" class="form-control" required
                               value="<%= editando ? p.getFechaCulminacion() : "" %>">
                    </div>

                    <div class="mb-3">
                        <label for="asesorTecnico" class="form-label">Asesor técnico</label>
                        <input id="asesorTecnico" name="asesorTecnico" type="text" class="form-control"
                               placeholder="Nombres y apellidos" required
                               value="<%= editando ? p.getAsesorTecnico() : "" %>">
                    </div>

                    <div class="mb-3">
                        <label for="presupuestoAsignado" class="form-label">Presupuesto asignado</label>
                        <input id="presupuestoAsignado" name="presupuestoAsignado" type="number" class="form-control"
                               step="0.01" min="0.01" required
                               value="<%= editando ? p.getPresupuestoAsignado() : "" %>">
                    </div>

                    <div>
                        <button type="submit" class="btn btn-primary">
                            <%= editando ? "Actualizar" : "Registrar" %>
                        </button>
                        <% if (editando) { %>
                            <a href="ProyectoServlet" class="btn btn-secondary">Cancelar</a>
                        <% } %>
                    </div>
                </form>
            </div>
        </div>

        <div class="col-md-8">

            <% if (consulta != null) { %>
            <div class="card p-4 shadow-sm mb-4">
                <h4 class="mb-3">Detalle del proyecto</h4>
                <p><strong>Id:</strong> <%= consulta.getId() %></p>
                <p><strong>Nombre:</strong> <%= consulta.getNombreProyecto() %></p>
                <p><strong>Descripción técnica:</strong> <%= consulta.getDescripcionTecnica() %></p>
                <p><strong>Fecha de inicio:</strong> <%= consulta.getFechaInicio() %></p>
                <p><strong>Fecha de culminación:</strong> <%= consulta.getFechaCulminacion() %></p>
                <p><strong>Asesor técnico:</strong> <%= consulta.getAsesorTecnico() %></p>
                <p><strong>Presupuesto asignado:</strong> S/ <%= consulta.getPresupuestoAsignado() %></p>
                <div>
                    <a href="ProyectoServlet" class="btn btn-secondary btn-sm">Cerrar</a>
                </div>
            </div>
            <% } %>

            <div class="card p-4 shadow-sm">
                <h4 class="mb-3">Proyectos Disponibles</h4>

                <table class="table table-hover">
                    <thead class="table-light">
                        <tr>
                            <th>Id</th>
                            <th>Nombre</th>
                            <th>Asesor</th>
                            <th>Presupuesto</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%
                    if (lista != null && !lista.isEmpty()) {
                        for (Proyecto x : lista) {
                    %>
                        <tr>
                            <td><%= x.getId() %></td>
                            <td><%= x.getNombreProyecto() %></td>
                            <td><%= x.getAsesorTecnico() %></td>
                            <td>S/ <%= x.getPresupuestoAsignado() %></td>
                            <td>
                                <a href="ProyectoServlet?accion=consultar&id=<%= x.getId() %>"
                                   class="btn btn-sm btn-info">Consultar</a>
                                <a href="ProyectoServlet?accion=editar&id=<%= x.getId() %>"
                                   class="btn btn-sm btn-warning">Editar</a>
                                <form method="post" action="ProyectoServlet" style="display:inline;"
                                      onsubmit="return confirm('¿Seguro que deseas eliminar este proyecto?');">
                                    <input type="hidden" name="accion" value="eliminar">
                                    <input type="hidden" name="id" value="<%= x.getId() %>">
                                    <button type="submit" class="btn btn-sm btn-danger">Eliminar</button>
                                </form>
                            </td>
                        </tr>
                    <%
                        }
                    }
                    %>
                    </tbody>
                </table>
            </div>
        </div>

    </div>
</div>

</body>
</html>