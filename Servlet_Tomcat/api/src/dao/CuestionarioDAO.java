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

public class CuestionarioDAO {

	private DataSource ds;

	public CuestionarioDAO() throws SQLException {
		try {
			Context envContext = new InitialContext();
		    this.ds = (DataSource)envContext.lookup("java:/comp/env/jdbc/ConexionDB");
		}catch(NamingException e) {
			e.printStackTrace();
		}

	}

	public Cuestionario buscarCuestionario(String codigo) throws SQLException {

		Cuestionario cuestionario=null;
		try(Connection conn = ds.getConnection()){
			System.out.println("llego aqui");
			String sql = "SELECT * FROM cuestionario WHERE codigo_cuest=?";
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, codigo);
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {
					int id = resultSet.getInt("id_cuest");
					String empresa = resultSet.getString("empresa_cuest");
					String dominio = resultSet.getString("dominio_cuest");
					String proceso = resultSet.getString("proceso_cuest");
					String objetivo = resultSet.getString("objetivo_cuest");
					cuestionario = new Cuestionario(id,empresa,dominio,proceso,objetivo,codigo);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return cuestionario;
	}

	public void insertar(Cuestionario cuestionario) throws SQLException {

		try(Connection conn = ds.getConnection()) {
			String query = "INSERT INTO cuestionario (empresa_cuest, dominio_cuest, proceso_cuest, objetivo_cuest, codigo_cuest) VALUES (?,?,?,?,?)";
			try(PreparedStatement statement = conn.prepareStatement(query)){
				statement.setString(1, cuestionario.getEmpresa_cuest());
				statement.setString(2, cuestionario.getDominio_cuest());
				statement.setString(3, cuestionario.getProceso_cuest());
				statement.setString(4, cuestionario.getObjetivo_cuest());
				statement.setString(5, cuestionario.getCodigo_cuest());
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Cuestionario> listarCuestionarios() throws SQLException {

		List<Cuestionario> listaCuestionarios = new ArrayList<Cuestionario>();
		try(Connection conn = ds.getConnection()){
			String sql = "SELECT * FROM cuestionario";
			Statement statement = conn.createStatement();
			ResultSet resulSet = statement.executeQuery(sql);
			while (resulSet.next()) {
					int id = resulSet.getInt("id_cuest");
					String empresa = resulSet.getString("empresa_cuest");
					String dominio = resulSet.getString("dominio_cuest");
					String proceso = resulSet.getString("proceso_cuest");
					String objetivo = resulSet.getString("objetivo_cuest");
					String codigo = resulSet.getString("codigo_cuest");
					Cuestionario cuestionario = new Cuestionario(id,empresa,dominio,proceso,objetivo,codigo);
					listaCuestionarios.add(cuestionario);
				}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return listaCuestionarios;

	}

	public void eliminar(String id) throws SQLException {
		try(Connection conn = ds.getConnection()) {
			String sql = "DELETE FROM cuestionario WHERE id_cuest = ?";
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setInt(1, Integer.parseInt(id));
			    statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
