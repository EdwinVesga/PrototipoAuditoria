package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PreguntaDAO;
import model.Pregunta;



@WebServlet("/PreguntaFormulario")
public class PreguntaFormulario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PreguntaDAO preguntaDAO;

	public void init() {
		try {
			preguntaDAO = new PreguntaDAO();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				HttpSession sesion = request.getSession();
				Pregunta pregunta = new Pregunta(
				request.getParameter("descripcion"),
				Integer.parseInt(request.getParameter("id_cuest")));
		try {
			preguntaDAO.insertar(pregunta);
		}catch(Exception e) {
			e.printStackTrace();
		}
		sesion.setAttribute("show", "#formulario_preg");
		response.sendRedirect("/Auditor.jsp");
	}

}
