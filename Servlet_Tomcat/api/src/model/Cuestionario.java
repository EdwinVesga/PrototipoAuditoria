package model;

public class Cuestionario {

	int id_cuest;
	String empresa_cuest;
	String dominio_cuest;
	String proceso_cuest;
	String objetivo_cuest;
	String codigo_cuest;

	public int getId_cuest() {
		return id_cuest;
	}
	public void setId_cuest(int id_cuest) {
		this.id_cuest=id_cuest;
	}
	public String getEmpresa_cuest() {
		return empresa_cuest;
	}
	public void setEmpresa_cuest(String empresa_cuest) {
		this.empresa_cuest = empresa_cuest;
	}
	public String getDominio_cuest() {
		return dominio_cuest;
	}
	public void setDominio_cuest(String dominio_cuest) {
		this.dominio_cuest = dominio_cuest;
	}
	public String getProceso_cuest() {
		return proceso_cuest;
	}
	public void setProceso_cuest(String proceso_cuest) {
		this.proceso_cuest = proceso_cuest;
	}
	public String getObjetivo_cuest() {
		return objetivo_cuest;
	}
	public void setObjetivo_cuest(String objetivo_cuest) {
		this.objetivo_cuest = objetivo_cuest;
	}
	public String getCodigo_cuest() {
		return codigo_cuest;
	}
	public void setCodigo_cuest(String codigo_cuest) {
		this.codigo_cuest = codigo_cuest;
	}
	@Override
	public String toString() {
		return "Cuestionario [id_cuest=" + id_cuest + ", empresa_cuest=" + empresa_cuest + ", dominio_cuest="
				+ dominio_cuest + ", proceso_cuest=" + proceso_cuest + ", objetivo_cuest=" + objetivo_cuest
				+ ", codigo_cuest=" + codigo_cuest + "]";
	}
	public Cuestionario(int id_cuest, String empresa_cuest, String dominio_cuest, String proceso_cuest,
			String objetivo_cuest, String codigo_cuest) {
		super();
		this.id_cuest = id_cuest;
		this.empresa_cuest = empresa_cuest;
		this.dominio_cuest = dominio_cuest;
		this.proceso_cuest = proceso_cuest;
		this.objetivo_cuest = objetivo_cuest;
		this.codigo_cuest = codigo_cuest;
	}
	public Cuestionario(String empresa_cuest, String dominio_cuest, String proceso_cuest,
			String objetivo_cuest, String codigo_cuest) {
		super();
		this.empresa_cuest = empresa_cuest;
		this.dominio_cuest = dominio_cuest;
		this.proceso_cuest = proceso_cuest;
		this.objetivo_cuest = objetivo_cuest;
		this.codigo_cuest = codigo_cuest;
	}

}
