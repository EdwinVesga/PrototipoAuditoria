package model;

public class Usuario {

	private String id_usu;
	private String rol_usu;
	private String nombres_usu;
	private String apellidos_usu;
	private String correo_usu;
	private String celular_usu;
	private String password_usu;
	public String getId_usu() {
		return id_usu;
	}
	public void setId_usu(String id_usu) {
		this.id_usu = id_usu;
	}
	public String getRol_usu() {
		return rol_usu;
	}
	public void setRol_usu(String rol_usu) {
		this.rol_usu = rol_usu;
	}
	public String getNombres_usu() {
		return nombres_usu;
	}
	public void setNombres_usu(String nombres_usu) {
		this.nombres_usu = nombres_usu;
	}
	public String getApellidos_usu() {
		return apellidos_usu;
	}
	public void setApellidos_usu(String apellidos_usu) {
		this.apellidos_usu = apellidos_usu;
	}
	public String getCorreo_usu() {
		return correo_usu;
	}
	public void setCorreo_usu(String correo_usu) {
		this.correo_usu = correo_usu;
	}
	public String getCelular_usu() {
		return celular_usu;
	}
	public void setCelular_usu(String celular_usu) {
		this.celular_usu = celular_usu;
	}
	public String getPassword_usu() {
		return password_usu;
	}
	public void setPassword_usu(String password_usu) {
		this.password_usu = password_usu;
	}
	@Override
	public String toString() {
		return "Usuario [id_usu=" + id_usu + ", rol_usu=" + rol_usu + ", nombres_usu=" + nombres_usu
				+ ", apellidos_usu=" + apellidos_usu + ", correo_usu=" + correo_usu + ", celular_usu=" + celular_usu
				+ ", password_usu=" + password_usu + "]";
	}
	public Usuario(String id_usu, String rol_usu, String nombres_usu, String apellidos_usu, String correo_usu,
			String celular_usu, String password_usu) {
		super();
		this.id_usu = id_usu;
		this.rol_usu = rol_usu;
		this.nombres_usu = nombres_usu;
		this.apellidos_usu = apellidos_usu;
		this.correo_usu = correo_usu;
		this.celular_usu = celular_usu;
		this.password_usu = password_usu;
	}
	
	
}
