package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CuestionarioDAO;
import dao.PreguntaDAO;
import model.Cuestionario;
import model.Pregunta;



@WebServlet("/AdminPreguntas")
public class AdminPreguntas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PreguntaDAO preguntaDAO;
	private CuestionarioDAO cuestionarioDAO;

	public void init() {
		try {
			preguntaDAO = new PreguntaDAO();
			cuestionarioDAO = new CuestionarioDAO();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");
    		try {
    			switch (action) {
    			case "index":
    				index(request, response);
    				break;
    			case "eliminar":
    				eliminar(request, response);
    				break;
    			default:
    				break;
    			}
    		} catch (SQLException e) {
    			e.getStackTrace();
    		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

  private void index (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
  		//mostrar(request, response);
  		response.sendRedirect("/Auditor.jsp");
  	}
    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
    	HttpSession sesion = request.getSession();	
    	preguntaDAO.eliminar(request.getParameter("id_preg"));
        List<Pregunta> listaPreguntas = preguntaDAO.listarPreguntas(request.getParameter("idcuest"));
        Cuestionario cuestionariotemp = cuestionarioDAO.buscarCuestionario(request.getParameter("codigocuest"));
    	sesion.setAttribute("listapreg",listaPreguntas);
    	sesion.setAttribute("cuestemp",cuestionariotemp);
    	sesion.setAttribute("show", "#detalles_read");
        response.sendRedirect("/Auditor.jsp");
    	}
}
