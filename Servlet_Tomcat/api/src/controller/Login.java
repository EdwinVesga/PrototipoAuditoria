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



@WebServlet("/Login")
public class Login extends HttpServlet {
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
		HttpSession sesion = request.getSession();
        String usu, pass;
        usu = request.getParameter("username");
        pass = request.getParameter("password");
        Usuario usuario = usuarioDAO.validar(usu, pass);
        if((usuario != null) && (sesion.getAttribute("usuario")==null)){
            sesion.setAttribute("usuario", usuario);
            if(usuario.getRol_usu().equals("Auditor"))
            	response.sendRedirect("/Auditor.jsp");
            else {
							sesion.setAttribute("show", "#jumbo");
							response.sendRedirect("/Interventor.jsp");
						}
        }else{
        	response.sendRedirect("/index.jsp");
        	sesion.invalidate();
        }
	}

}
