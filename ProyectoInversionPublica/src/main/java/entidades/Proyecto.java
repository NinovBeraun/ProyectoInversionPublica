package entidades;

public class Proyecto {

	private int id;
	private String nombreProyecto;
	private String descripcionTecnica;
	private String fechaInicio;
	private String fechaCulminacion;
	private String asesorTecnico;
	private double presupuestoAsignado;

	public Proyecto() {
	}

	public Proyecto(int id, String nombreProyecto, String descripcionTecnica, String fechaInicio,
			String fechaCulminacion, String asesorTecnico, double presupuestoAsignado) {
		this.id = id;
		this.nombreProyecto = nombreProyecto;
		this.descripcionTecnica = descripcionTecnica;
		this.fechaInicio = fechaInicio;
		this.fechaCulminacion = fechaCulminacion;
		this.asesorTecnico = asesorTecnico;
		this.presupuestoAsignado = presupuestoAsignado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	public String getDescripcionTecnica() {
		return descripcionTecnica;
	}

	public void setDescripcionTecnica(String descripcionTecnica) {
		this.descripcionTecnica = descripcionTecnica;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaCulminacion() {
		return fechaCulminacion;
	}

	public void setFechaCulminacion(String fechaCulminacion) {
		this.fechaCulminacion = fechaCulminacion;
	}

	public String getAsesorTecnico() {
		return asesorTecnico;
	}

	public void setAsesorTecnico(String asesorTecnico) {
		this.asesorTecnico = asesorTecnico;
	}

	public double getPresupuestoAsignado() {
		return presupuestoAsignado;
	}

	public void setPresupuestoAsignado(double presupuestoAsignado) {
		this.presupuestoAsignado = presupuestoAsignado;
	}
}