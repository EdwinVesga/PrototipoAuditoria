package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ResultadoDAO;
import model.Resultado;
import dao.UsuarioDAO;
import dao.CuestionarioDAO;
import model.Usuario;
import model.Cuestionario;
import dao.PreguntaDAO;
import model.Pregunta;



@WebServlet("/AdminInterventor")
public class AdminInterventor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioDAO usuarioDAO;
	private CuestionarioDAO cuestionarioDAO;
	private ResultadoDAO resultadoDAO;
	private PreguntaDAO preguntaDAO;

	public void init() {
		try {
			preguntaDAO = new PreguntaDAO();
			cuestionarioDAO = new CuestionarioDAO();
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
          case "resultados":
              resultados(request, response);
              break;
          case "insertar":
            insertar(request, response);
            break;
          case "cuestionario":
            cuestionario(request, response);
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
  		response.sendRedirect("/Interventor.jsp");
  	}

  private void resultados (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
    HttpSession sesion = request.getSession();
    String idcuest = request.getParameter("id_cuest");
	  List<Pregunta> lista =preguntaDAO.listarPreguntas(idcuest);
    int contador=1;
	  for(Pregunta preg : lista) {
		  String respuesta=request.getParameter("respuesta_res"+contador);
	      int idpregunta = preg.getId_preg();
	      String idusuario=request.getParameter("id_usu");
	      Resultado resultado = new Resultado(respuesta,idpregunta,idusuario);
	      resultadoDAO.insertar(resultado);
        contador=contador+1;
	  }
    sesion.invalidate();
	  response.sendRedirect("/index.jsp");
	}

    private void cuestionario (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
          HttpSession sesion = request.getSession();
          String codigo;
          codigo = request.getParameter("codigo");
          Cuestionario cuestionario = cuestionarioDAO.buscarCuestionario(codigo);
          if((cuestionario != null) && (sesion.getAttribute("cuestionario")==null)){
        	  List<Pregunta> lista =preguntaDAO.listarPreguntas(""+cuestionario.getId_cuest());
              sesion.setAttribute("cuestionario", cuestionario);
              sesion.setAttribute("listapreguntas", lista);
							sesion.setAttribute("show", "#cuestionario_read");
          }
					else{
						sesion.setAttribute("show", "#jumbo");
					}
        response.sendRedirect("/Interventor.jsp");
    	}

    private void insertar (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
    		//mostrar(request, response);
        String respuesta=request.getParameter("respuesta_res");
        int idpregunta= Integer.parseInt(request.getParameter("id_preg"));
        String idusuario=request.getParameter("id_usu");
        Resultado resultado = new Resultado(respuesta,idpregunta,idusuario);
        resultadoDAO.insertar(resultado);
    		response.sendRedirect("/Interventor.jsp");
    	}
    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
    		cuestionarioDAO.eliminar(request.getParameter("id_cuest"));
    		response.sendRedirect("/Interventor.jsp");

    	}

}
