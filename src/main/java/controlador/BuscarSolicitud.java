package controlador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Actividad;
import modelo.Solicitud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.DaoActividad;
import dao.DaoSolicitud;

/**
 * Servlet implementation class BuscarActividad
 */
@MultipartConfig
public class BuscarSolicitud extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BuscarSolicitud() {
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

		PrintWriter respuesta = response.getWriter();

		String dni = request.getParameter("dni");
		DaoSolicitud dao;
		try {
			dao = new DaoSolicitud();
			if (dao.dniExiste(dni)) {
				// Json de ese registro
				Solicitud s = new Solicitud();
				try {
					s.obtenerPorDni(dni);
					respuesta.print(s.dameJson());
					System.out.println(s.dameJson());// Comprobar que me llega desde la Bdd. Siguiente HTML
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.sendRedirect("/Verano/error.html");
				}
			} else {
				response.sendRedirect("/Verano/error.html?error=4");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("/Verano/error.html");

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}