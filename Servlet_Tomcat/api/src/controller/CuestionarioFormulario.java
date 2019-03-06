package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CuestionarioDAO;
import model.Cuestionario;



@WebServlet("/CuestionarioFormulario")
public class CuestionarioFormulario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CuestionarioDAO cuestionarioDAO;

	public void init() {
		try {
			cuestionarioDAO = new CuestionarioDAO();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		Cuestionario cuestionario = new Cuestionario(
				request.getParameter("empresa"),
				request.getParameter("dominio"),
				request.getParameter("proceso"),
				request.getParameter("objetivo"),
				request.getParameter("codigo"));
		try {
			cuestionarioDAO.insertar(cuestionario);
		}catch(Exception e) {
			e.printStackTrace();
		}
		sesion.setAttribute("show", "#auditoria_read");
		response.sendRedirect("/Auditor.jsp");
	}

}
