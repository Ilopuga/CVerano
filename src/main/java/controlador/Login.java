package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Administrador;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession sesion;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
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
		// recoge el parámetro de action desde la url
		String action = request.getParameter("action");
		// Si es logout
		if ("logout".equals(action)) {
			HttpSession session = request.getSession(false); // pide la sesión
			if (session != null) { // existe
				session.invalidate(); // invalida
			}
			response.sendRedirect("/Verano/index.html"); // Redirigir a la página principal
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String usuario = request.getParameter("usuario");
		String pass = Administrador.myMD5(request.getParameter("pass"));

		Administrador a = new Administrador();
		a.setUsuario(usuario);

		// proteccion

		try {
			if (a.logueo(pass)) {

				sesion = request.getSession();

				sesion.setAttribute("usuario", a.getUsuario());

				response.sendRedirect("admin/gestion.html?usuario=" + usuario + "");

			} else {
				// System.out.println("Fallo login");
				response.sendRedirect("login.html?error=true");
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			response.sendRedirect("login.html?error=true"); // Mensaje de erorr para ambas
		}
	}

}
