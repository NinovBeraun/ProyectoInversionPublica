package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entidades.Proyecto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ProyectoServlet")
public class ProyectoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private List<Proyecto> listaProyectos = new ArrayList<Proyecto>();
	private int contadorId = 1;

	public ProyectoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String accion = request.getParameter("accion");

		if ("consultar".equals(accion)) {
			Proyecto encontrado = buscarPorId(leerId(request));
			request.setAttribute("consulta", encontrado);

		} else if ("editar".equals(accion)) {
			Proyecto encontrado = buscarPorId(leerId(request));
			request.setAttribute("proyecto", encontrado);
		}

		request.setAttribute("proyectos", listaProyectos);
		request.getRequestDispatcher("proyecto.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String accion = request.getParameter("accion");

		if ("eliminar".equals(accion)) {
			Proyecto encontrado = buscarPorId(leerId(request));
			if (encontrado != null) {
				listaProyectos.remove(encontrado);
			}
			response.sendRedirect("ProyectoServlet");
			return;
		}

		String nombre = request.getParameter("nombreProyecto");
		String descripcion = request.getParameter("descripcionTecnica");
		String fechaInicio = request.getParameter("fechaInicio");
		String fechaCulminacion = request.getParameter("fechaCulminacion");
		String asesor = request.getParameter("asesorTecnico");
		String presupuestoTexto = request.getParameter("presupuestoAsignado");

		String error = validar(nombre, fechaInicio, fechaCulminacion, asesor, presupuestoTexto);

		if (error != null) {
			if ("actualizar".equals(accion)) {
				Proyecto conDatos = new Proyecto();
				conDatos.setId(leerId(request));
				conDatos.setNombreProyecto(nombre);
				conDatos.setDescripcionTecnica(descripcion);
				conDatos.setFechaInicio(fechaInicio);
				conDatos.setFechaCulminacion(fechaCulminacion);
				conDatos.setAsesorTecnico(asesor);
				conDatos.setPresupuestoAsignado(0);
				request.setAttribute("proyecto", conDatos);
			}
			request.setAttribute("error", error);
			request.setAttribute("proyectos", listaProyectos);
			request.getRequestDispatcher("proyecto.jsp").forward(request, response);
			return;
		}

		double presupuesto = Double.parseDouble(presupuestoTexto.trim());

		if ("registrar".equals(accion)) {
			Proyecto nuevo = new Proyecto(contadorId, nombre.trim(), descripcion.trim(), fechaInicio,
					fechaCulminacion, asesor.trim(), presupuesto);
			contadorId++;
			listaProyectos.add(nuevo);

		} else if ("actualizar".equals(accion)) {
			Proyecto existente = buscarPorId(leerId(request));
			if (existente != null) {
				existente.setNombreProyecto(nombre.trim());
				existente.setDescripcionTecnica(descripcion.trim());
				existente.setFechaInicio(fechaInicio);
				existente.setFechaCulminacion(fechaCulminacion);
				existente.setAsesorTecnico(asesor.trim());
				existente.setPresupuestoAsignado(presupuesto);
			}
		}

		response.sendRedirect("ProyectoServlet");
	}


	private int leerId(HttpServletRequest request) {
		try {
			return Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	private Proyecto buscarPorId(int id) {
		for (Proyecto p : listaProyectos) {
			if (p.getId() == id) {
				return p;
			}
		}
		return null;
	}

	private String validar(String nombre, String fechaInicio, String fechaCulminacion, String asesor,
			String presupuestoTexto) {

		if (nombre == null || nombre.trim().isEmpty()) {
			return "El nombre del proyecto es obligatorio.";
		}
		if (nombre.trim().length() < 4 || nombre.trim().length() > 60) {
			return "El nombre del proyecto debe tener entre 4 y 60 caracteres.";
		}

		if (fechaInicio == null || fechaInicio.trim().isEmpty()) {
			return "La fecha de inicio es obligatoria.";
		}
		if (fechaCulminacion == null || fechaCulminacion.trim().isEmpty()) {
			return "La fecha de culminación es obligatoria.";
		}

		try {
			LocalDate inicio = LocalDate.parse(fechaInicio);
			LocalDate fin = LocalDate.parse(fechaCulminacion);
			if (fin.isBefore(inicio)) {
				return "La fecha de culminación no puede ser anterior a la fecha de inicio.";
			}
		} catch (Exception e) {
			return "Las fechas no tienen un formato válido.";
		}

		if (asesor == null || asesor.trim().isEmpty()) {
			return "El asesor técnico es obligatorio.";
		}

		if (presupuestoTexto == null || presupuestoTexto.trim().isEmpty()) {
			return "El presupuesto asignado es obligatorio.";
		}
		try {
			double presupuesto = Double.parseDouble(presupuestoTexto.trim());
			if (presupuesto <= 0) {
				return "El presupuesto debe ser mayor que cero.";
			}
		} catch (NumberFormatException e) {
			return "El presupuesto debe ser un número.";
		}

		return null;
	}
}