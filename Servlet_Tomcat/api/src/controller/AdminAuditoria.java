package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CuestionarioDAO;
import dao.PreguntaDAO;
import dao.ResultadoDAO;
import dao.UsuarioDAO;
import model.Cuestionario;
import model.Pregunta;
import model.Resultado;
import model.Usuario;
import model.Reporte;


@WebServlet("/AdminAuditoria")
public class AdminAuditoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CuestionarioDAO cuestionarioDAO;
	private PreguntaDAO preguntaDAO;
	private ResultadoDAO resultadoDAO;
	private UsuarioDAO usuarioDAO;

	public void init() {
		try {
			cuestionarioDAO = new CuestionarioDAO();
			preguntaDAO = new PreguntaDAO();
			resultadoDAO = new ResultadoDAO();
			usuarioDAO = new UsuarioDAO();
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
          case "buscar":
            buscar(request, response);
            break;
          case "reporte":
              reporte(request, response);
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

  		response.sendRedirect("/Auditor.jsp");
  	}
  
  private void buscar (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
	  HttpSession sesion = request.getSession();
	  List<Pregunta> listaPreguntas = preguntaDAO.listarPreguntas(request.getParameter("idcuest"));
	  Cuestionario cuestionariotemp = cuestionarioDAO.buscarCuestionario(request.getParameter("codigocuest"));
	  sesion.setAttribute("listapreg",listaPreguntas);
	  sesion.setAttribute("cuestemp",cuestionariotemp);
	  sesion.setAttribute("show", "#detalles_read");
	  response.sendRedirect("/Auditor.jsp");
	}
  
  private void reporte (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		HttpSession sesion = request.getSession();
		List<Cuestionario> listacuestionarios = cuestionarioDAO.listarCuestionarios();
		List<Resultado> listaresultados = resultadoDAO.listarResultados();
		List<Reporte> listareportes = new ArrayList<Reporte>();
		for(Cuestionario cuestionario : listacuestionarios) {
			String interventor = "";
			int positive=0;
				int negative=0;
			List<Pregunta> listapreguntas = preguntaDAO.listarPreguntas(""+cuestionario.getId_cuest());
			for(Pregunta pregunta : listapreguntas) {
				for(Resultado resultado : listaresultados) {
					if(pregunta.getId_preg() == resultado.getId_preg()) {
						if(resultado.getRespuesta_res().equals("Si")) {
							positive=positive+1;
						}else {
							negative=negative+1;
						}
						if(interventor.equals("")) {
							interventor = resultado.getId_usu();
						}
					}
				}
			}
			Usuario user = usuarioDAO.buscar(interventor);
			Reporte reporte = new Reporte(cuestionario.getCodigo_cuest(), ""+user.getNombres_usu()+" "+user.getApellidos_usu(), positive,negative);
			listareportes.add(reporte);
		}
		sesion.setAttribute("listareportes", listareportes);
		sesion.setAttribute("show", "#reportes_read");
		response.sendRedirect("/Auditor.jsp");
	}
    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
    		HttpSession sesion = request.getSession();	
    		cuestionarioDAO.eliminar(request.getParameter("id_cuest"));
    		sesion.setAttribute("show", "#auditoria_read");
    		response.sendRedirect("/Auditor.jsp");
    	}

}
