package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsuarioDAO;
import model.Usuario;



@WebServlet("/Singup")
public class Singup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioDAO usuarioDAO;
	
	public void init() {
		try {
			usuarioDAO = new UsuarioDAO();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = new Usuario(request.getParameter("username"),
				request.getParameter("rol"),
				request.getParameter("nombres"),
				request.getParameter("apellidos"),
				request.getParameter("correo"),
				request.getParameter("celular"),
				request.getParameter("password"));
		try {
			usuarioDAO.insertar(usuario);
		}catch(Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("/index.jsp");
	}

}
