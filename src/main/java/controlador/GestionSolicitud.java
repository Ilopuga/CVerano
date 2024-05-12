package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import modelo.Actividad;
import modelo.Administrador;
import modelo.Solicitud;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import dao.DaoActividad;
import dao.DaoAdministrador;
import dao.DaoSolicitud;

/**
 * Servlet implementation class GestionActividad
 */
@MultipartConfig
public class GestionSolicitud extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestionSolicitud() {
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
		String dni = request.getParameter("dni");
		int cod_actividad = Integer.parseInt(request.getParameter("cod_actividad"));
		String nombre = request.getParameter("nombre");
		String apellido1 = request.getParameter("apellido1");
		String apellido2 = request.getParameter("apellido2");
		String email = request.getParameter("email");
		String direccion = request.getParameter("direccion");
		int telefono = Integer.parseInt(request.getParameter("telefono"));
		String f_nacimiento = request.getParameter("f_nacimiento");
		
	
		int opcion = Integer.parseInt(request.getParameter("op"));

		if(opcion == 1) {
		try {
            DaoSolicitud dao = new DaoSolicitud();
            if (dao.dniExiste(dni)) {
                response.sendRedirect("error.html?error=1");
            } else {
        	            Solicitud s = new Solicitud(dni, cod_actividad, nombre, apellido1, apellido2, email, direccion,telefono, f_nacimiento); 
        	                dao.insertar(s);
        	            response.sendRedirect("buscador_solicitud.html?dni="+dni);
        	        }
        	    } catch (SQLException e) {
        	        e.printStackTrace();
        	        response.sendRedirect("error.html?error=2");
        	    }
		}
		if (opcion == 2) {
			//Añado los campos que son para el admin para el modificar, op 2
			int num_sorteo = Integer.parseInt(request.getParameter("num_sorteo"));
			boolean seleccionado = Boolean.parseBoolean(request.getParameter("seleccionado"));
			boolean pago = Boolean.parseBoolean(request.getParameter("pago"));
			String estado = request.getParameter("estado");
			int id = Integer.parseInt(request.getParameter("id"));
			try {
	            Solicitud s = new Solicitud(dni, cod_actividad, nombre, apellido1, apellido2, email, direccion,telefono, f_nacimiento, num_sorteo, seleccionado, pago, estado); 
				int idInt = id;
				s.setId(idInt);
				s.actualizar();
				response.sendRedirect("admin/list_solicitud.html");

	        	    } catch (SQLException e) {
	        	        e.printStackTrace();
	        	        response.sendRedirect("error.html?error=2");
	        	    }
			
		}
        	}
        }