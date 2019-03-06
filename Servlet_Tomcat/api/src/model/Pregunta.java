package model;

public class Pregunta {

	int id_preg;
	String descripcion_preg;
	int id_cuest;

	public int getId_preg() {
		return id_preg;
	}
	public void setId_preg(int id_preg) {
		this.id_preg = id_preg;
	}
	public String getDescripcion_preg() {
		return descripcion_preg;
	}
	public void setDescripcion_preg(String descripcion_preg) {
		this.descripcion_preg = descripcion_preg;
	}
	public int getId_cuest() {
		return id_cuest;
	}
	public void setId_cuest(int id_cuest) {
		this.id_cuest = id_cuest;
	}

	public Pregunta(String descripcion_preg, int id_cuest) {
		super();
		this.descripcion_preg = descripcion_preg;
		this.id_cuest = id_cuest;
	}
	@Override
	public String toString() {
		return "Pregunta [id_preg=" + id_preg + ", descripcion_preg=" + descripcion_preg + ", id_cuest=" + id_cuest
				+ "]";
	}
	public Pregunta(int id_preg, String descripcion_preg, int id_cuest) {
		super();
		this.id_preg = id_preg;
		this.descripcion_preg = descripcion_preg;
		this.id_cuest = id_cuest;
	}
	
}
