package model;

public class Reporte {
	private String codigo_reporte;
	private String interventor_reporte;
	private int positive;
	private int negative;
	public String getCodigo_reporte() {
		return codigo_reporte;
	}
	public void setCodigo_reporte(String codigo_reporte) {
		this.codigo_reporte = codigo_reporte;
	}
	public String getInterventor_reporte() {
		return interventor_reporte;
	}
	public void setInterventor_reporte(String interventor_reporte) {
		this.interventor_reporte = interventor_reporte;
	}
	public int getPositive() {
		return positive;
	}
	public void setPositive(int positive) {
		this.positive = positive;
	}
	public int getNegative() {
		return negative;
	}
	public void setNegative(int negative) {
		this.negative = negative;
	}
	public Reporte(String codigo_reporte, String interventor_reporte, int positive, int negative) {
		super();
		this.codigo_reporte = codigo_reporte;
		this.interventor_reporte = interventor_reporte;
		this.positive = positive;
		this.negative = negative;
	}
	@Override
	public String toString() {
		return "Reporte [codigo_reporte=" + codigo_reporte + ", interventor_reporte=" + interventor_reporte
				+ ", positive=" + positive + ", negative=" + negative + "]";
	}
	
	
}
