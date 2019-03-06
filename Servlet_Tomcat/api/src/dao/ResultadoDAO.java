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

import model.Resultado;

public class ResultadoDAO {

	private DataSource ds;
	public ResultadoDAO() throws SQLException {
		try {
			Context envContext = new InitialContext();
		    this.ds = (DataSource)envContext.lookup("java:/comp/env/jdbc/ConexionDB");
		}catch(NamingException e) {
			e.printStackTrace();
		}

	}

	public void insertar(Resultado resultado) throws SQLException {

		try(Connection conn = ds.getConnection()) {
			String query = "INSERT INTO resultado (respuesta_res, id_preg, id_usu) VALUES (?,?,?)";
			try(PreparedStatement statement = conn.prepareStatement(query)){
				statement.setString(1, resultado.getRespuesta_res());
				statement.setInt(2, resultado.getId_preg());
				statement.setString(3, resultado.getId_usu());
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Resultado> listarResultados() throws SQLException {

		List<Resultado> listaResultados = new ArrayList<Resultado>();
		try(Connection conn = ds.getConnection()){
			String sql = "SELECT * FROM resultado";
			Statement statement = conn.createStatement();
			ResultSet resulSet = statement.executeQuery(sql);
			while (resulSet.next()) {
					int id_resultado = resulSet.getInt("id_res");
					String respuesta = resulSet.getString("respuesta_res");
					int id_preg = resulSet.getInt("id_preg");
					String id_usu = resulSet.getString("id_usu");
					Resultado resultado = new Resultado(id_resultado,respuesta,id_preg,id_usu);
					listaResultados.add(resultado);
				}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return listaResultados;

	}

	public void eliminar(String id) throws SQLException {
		try(Connection conn = ds.getConnection()) {
			String sql = "DELETE FROM resultado WHERE id_res=?";
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, id);
			    statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
