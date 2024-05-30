package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Administrador;

import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;

import dao.DaoActividad;
import dao.DaoAdministrador;

/**
 * Servlet implementation class GestionAdmin
 */
@MultipartConfig
public class GestionAdministrador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestionAdministrador() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String usuario = request.getParameter("usuario");
		String pass = request.getParameter("pass");
		String hashedPass = Administrador.myMD5(pass);
		String id = request.getParameter("id");

		try {
			DaoAdministrador dao = new DaoAdministrador();
			if (id == null || id.trim().isEmpty()) {
				if (dao.usuarioExiste(usuario)) {
					response.sendRedirect("admin/error.html?error=2");
				} else {
					Administrador a = new Administrador(usuario, hashedPass);
					dao.insertar(a);
					response.sendRedirect("admin/list_admin.html");
				}
			} else {
				Administrador a = new Administrador(usuario, hashedPass);
				int idInt = Integer.parseInt(id); // Este puede lanzar NumberFormatException
				a.setId(idInt);
				dao.actualizar(a);
				response.sendRedirect("admin/list_admin.html");
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			response.sendRedirect("admin/error.html");
		}
	}
}

/*
 * try { DaoAdministrador dao = new DaoAdministrador(); if
 * (dao.usuarioExiste(usuario)) { // Usuario ya existe, enviar respuesta para
 * alerta //response.setContentType("text/html");
 * //response.sendRedirect("admin/error.html?error=2");
 * 
 * } else { Administrador a = new Administrador(usuario, hashedPass); //Le paso
 * la contraseña hasheada if (id == null || id.trim().isEmpty()) {
 * dao.insertar(a); } else { int idInt = Integer.parseInt(id); a.setId(idInt);
 * a.actualizar(); } response.sendRedirect("admin/list_admin.html"); } } catch
 * (SQLException e) { e.printStackTrace();
 * response.sendRedirect("admin/error.html?error=1"); } }
 */
