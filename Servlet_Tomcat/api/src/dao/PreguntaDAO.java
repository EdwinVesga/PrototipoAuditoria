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

import model.Cuestionario;
import model.Pregunta;

public class PreguntaDAO {
	
	private DataSource ds;
	public PreguntaDAO() throws SQLException {
		try {
			Context envContext = new InitialContext();
		    this.ds = (DataSource)envContext.lookup("java:/comp/env/jdbc/ConexionDB");
		}catch(NamingException e) {
			e.printStackTrace();
		}

	}
	
	public void insertar(Pregunta pregunta) throws SQLException {

		try(Connection conn = ds.getConnection()) {
			String query = "INSERT INTO pregunta (descripcion_preg, id_cuest) VALUES (?,?)";
			try(PreparedStatement statement = conn.prepareStatement(query)){
				statement.setString(1, pregunta.getDescripcion_preg());
				statement.setInt(2, pregunta.getId_cuest());
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Pregunta> listarPreguntas(String idcuest) throws SQLException {
		
		List<Pregunta> listaPreguntas = new ArrayList<Pregunta>();
		try(Connection conn = ds.getConnection()){
			String sql = "SELECT * FROM pregunta WHERE id_cuest=?";
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setInt(1, Integer.parseInt(idcuest));
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {
					int id_preg = resultSet.getInt("id_preg");
					String descripcion_preg = resultSet.getString("descripcion_preg");
					int id_cuest = resultSet.getInt("id_cuest");
					Pregunta pregunta = new Pregunta(id_preg,descripcion_preg,id_cuest);
					listaPreguntas.add(pregunta);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return listaPreguntas;
		
	}
	
	public void eliminar(String id) throws SQLException {
		try(Connection conn = ds.getConnection()) {
			String sql = "DELETE FROM pregunta WHERE id_preg = ?";
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, id);
			    statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
