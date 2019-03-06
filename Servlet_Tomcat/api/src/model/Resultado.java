package model;

public class Resultado {

	int id_res;
	String respuesta_res;
	int id_preg;
	String id_usu;

	public int getId_res() {
		return id_res;
	}
	public void setId_res(int id_res) {
		this.id_res = id_res;
	}
	public String getRespuesta_res() {
		return respuesta_res;
	}
	public void setRespuesta_res(String respuesta_res) {
		this.respuesta_res = respuesta_res;
	}
	public int getId_preg() {
		return id_preg;
	}
	public void setId_preg(int id_preg) {
		this.id_preg = id_preg;
	}
	public String getId_usu() {
		return id_usu;
	}
	public void setId_usu(String id_usu) {
		this.id_usu = id_usu;
	}
	@Override
	public String toString() {
		return "Resultado [id_res=" + id_res + ", respuesta_res=" + respuesta_res + ", id_preg=" + id_preg + ", id_usu="
				+ id_usu + "]";
	}
	public Resultado(int id_res, String respuesta_res, int id_preg, String id_usu) {
		super();
		this.id_res = id_res;
		this.respuesta_res = respuesta_res;
		this.id_preg = id_preg;
		this.id_usu = id_usu;
	}
	public Resultado( String respuesta_res, int id_preg, String id_usu) {
		super();
		this.respuesta_res = respuesta_res;
		this.id_preg = id_preg;
		this.id_usu = id_usu;
	}

}
