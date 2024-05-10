package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import modelo.Solicitud;

public class DaoSolicitud {

	public static Connection con = null;
	// Singelton
	private static DaoSolicitud instance = null;

	public DaoSolicitud() throws SQLException {

		this.con = DBConexion.getConexion();

	}

	// Singelton
	public static DaoSolicitud getInstance() throws SQLException {
		if (instance == null) {
			instance = new DaoSolicitud();
		}
		return instance;

	}

	public void insertar(Solicitud s) throws SQLException {
		// Solo pongo los campos que rellenar�n los usuarios externos

		String sql = "INSERT INTO solicitud (dni,cod_actividad,nombre,apellido1,apellido2,email,direccion,telefono,f_nacimiento) VALUES (?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, s.getDni());
		ps.setInt(2, s.getCod_actividad());
		ps.setString(3, s.getNombre());
		ps.setString(4, s.getApellido1());
		ps.setString(5, s.getApellido2());
		ps.setString(6, s.getEmail());
		ps.setString(7, s.getDireccion());
		ps.setInt(8, s.getTelefono());
		ps.setString(9, s.getF_nacimiento());

		int filas = ps.executeUpdate();
		ps.close();

	}

	public Solicitud obtenerPorId(int id) throws SQLException {
		// Aqu� ya incluyo todos los campos. No solo los a�adidos por el usuario externo
		// Para recuperar y modificar el admin
		String sql = "SELECT * FROM solicitud WHERE id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		rs.next();

		Solicitud s = new Solicitud(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5),
				rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10), rs.getInt(11),
				rs.getBoolean(12), rs.getBoolean(13), rs.getString(14));

		return s;
	}
	
	public Solicitud obtenerPorDni(String dni) throws SQLException {
		// Aqu� ya incluyo todos los campos.
		// Este es para listar el formulario enviado y avisar al usuario
		String sql = "SELECT * FROM solicitud WHERE dni=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dni);

		ResultSet rs = ps.executeQuery();

		rs.next();

		Solicitud s = new Solicitud(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5),
				rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10), rs.getInt(11),
				rs.getBoolean(12), rs.getBoolean(13), rs.getString(14));

		return s;
	}

	// Lo pongo private para dar seguridad. Llamar� al ListarJson
	private ArrayList<Solicitud> listar() throws SQLException {
		// Aqu� directamente le paso la query
		PreparedStatement ps = con.prepareStatement("SELECT * FROM solicitud");

		ResultSet rs = ps.executeQuery();

		// el array que devuelvo
		ArrayList<Solicitud> result = null;

		// el puntero est� en el null del principio y con el next va hasta el del final
		while (rs.next()) {
			// inicializar el result
			if (result == null) {

				result = new ArrayList<Solicitud>();
			}
			// solo defino el tipo y la posici�n pero tambi�n puedo poner el nombre, me gu�a
			// mejor
			result.add(new Solicitud(rs.getInt("id"), rs.getString("Dni"), rs.getInt("cod_actividad"),
					rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"), rs.getString("email"),
					rs.getString("direccion"), rs.getInt("telefono"), rs.getString("f_nacimiento"),
					rs.getInt("num_sorteo"), rs.getBoolean("seleccionado"), rs.getBoolean("pago"), rs.getString("estado")));

		}

		return result;

	}

	public String listarJson() throws SQLException {
		// Meto en json lo que me devuelve del m�todo listar
		String json = "";
		// Utilizo esta librer�a
		Gson gson = new Gson();

		json = gson.toJson(this.listar());

		return json;

	}

	public Solicitud actualizar(Solicitud s) throws SQLException {

		String sql = "UPDATE solicitud SET (Dni,cod_actividad,nombre,apellido1,apellido2,email,direccion,telefono,f_nacimiento,num_sorteo,seleccionado,pago,observaciones) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)\" WHERE id = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, s.getDni());
		ps.setInt(2, s.getCod_actividad());
		ps.setString(3, s.getNombre());
		ps.setString(4, s.getApellido1());
		ps.setString(5, s.getApellido2());
		ps.setString(6, s.getEmail());
		ps.setString(7, s.getDireccion());
		ps.setInt(8, s.getTelefono());
		ps.setString(9, s.getF_nacimiento());
		ps.setInt(10, s.getNum_sorteo());
		ps.setBoolean(11, s.isSeleccionado());
		ps.setBoolean(12, s.isPago());
		ps.setString(13, s.getEstado());
		ps.setInt(14, s.getId());// Al final porque es el criterio en el WHERE
		int result = ps.executeUpdate();

		ps.close();
		return s;
	}
	
	public void borrar(int id) throws SQLException {

		String sql = "DELETE FROM solicitud WHERE id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		int filas = ps.executeUpdate();
		ps.close();
	}
	
	public boolean dniExiste(String dni) throws SQLException {
	    String sql = "SELECT COUNT(*) FROM solicitud WHERE dni=?";
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, dni);
	    ResultSet rs = ps.executeQuery();
	    rs.next();
	    return rs.getInt(1) > 0;  // Verifica si hay al menos un registro
	}
	

    
}