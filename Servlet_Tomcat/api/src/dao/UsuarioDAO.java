package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import model.Usuario;

public class UsuarioDAO {
	
	private DataSource ds;
	public UsuarioDAO() throws SQLException {
		try {
			Context envContext = new InitialContext();
		    this.ds = (DataSource)envContext.lookup("java:/comp/env/jdbc/ConexionDB");
		}catch(NamingException e) {
			e.printStackTrace();
		}

	}
	
	public Usuario validar(String user, String pass) {
		Usuario usuario=null;
		try(Connection conn = ds.getConnection()){
			String sql = "SELECT * FROM usuario WHERE id_usu=? AND password_usu=?";
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, user);
				statement.setString(2, pass);
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {
						String id = resultSet.getString("id_usu");
						String rolres = resultSet.getString("rol_usu");
						String nombres = resultSet.getString("nombres_usu");
						String apellidos = resultSet.getString("apellidos_usu");
						String correo = resultSet.getString("correo_usu");
						String celular = resultSet.getString("celular_usu");
						String password = resultSet.getString("password_usu");
						usuario = new Usuario(id,rolres,nombres,apellidos,correo,celular,password);
					}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	public Usuario buscar(String user) {
		Usuario usuario=null;
		try(Connection conn = ds.getConnection()){
			String sql = "SELECT * FROM usuario WHERE id_usu=?";
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, user);
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {
						String id = resultSet.getString("id_usu");
						String rolres = resultSet.getString("rol_usu");
						String nombres = resultSet.getString("nombres_usu");
						String apellidos = resultSet.getString("apellidos_usu");
						String correo = resultSet.getString("correo_usu");
						String celular = resultSet.getString("celular_usu");
						String password = resultSet.getString("password_usu");
						usuario = new Usuario(id,rolres,nombres,apellidos,correo,celular,password);
					}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	public void insertar(Usuario usuario) throws SQLException {

		try(Connection conn = ds.getConnection()) {
			String query = "INSERT INTO usuario (id_usu, rol_usu, nombres_usu, apellidos_usu, correo_usu, celular_usu, password_usu) VALUES (?,?,?,?,?,?,?)";
			try(PreparedStatement statement = conn.prepareStatement(query)){
				statement.setString(1, usuario.getId_usu());
				statement.setString(2, usuario.getRol_usu());
				statement.setString(3, usuario.getNombres_usu());
				statement.setString(4, usuario.getApellidos_usu());
				statement.setString(5, usuario.getCorreo_usu());
				statement.setString(6, usuario.getCelular_usu());
				statement.setString(7, usuario.getPassword_usu());
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Usuario> listarUsuarios(String rol) throws SQLException {
		
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		try(Connection conn = ds.getConnection()){
			String sql = "SELECT * FROM usuario WHERE rol_usu = ?";
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, rol);
				ResultSet resulSet = statement.executeQuery();
				while (resulSet.next()) {
					String id = resulSet.getString("id_usu");
					String rolres = resulSet.getString("rol_usu");
					String nombres = resulSet.getString("nombres_usu");
					String apellidos = resulSet.getString("apellidos_usu");
					String correo = resulSet.getString("correo_usu");
					String celular = resulSet.getString("celular_usu");
					String password = resulSet.getString("password_usu");
					Usuario usuario = new Usuario(id,rolres,nombres,apellidos,correo,celular,password);
					listaUsuarios.add(usuario);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return listaUsuarios;
		
	}
	
	public void eliminar(String id) throws SQLException {
		try(Connection conn = ds.getConnection()) {
			String sql = "DELETE FROM usuario WHERE id_usu = ?";
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, id);
			    statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
