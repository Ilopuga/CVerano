package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import modelo.Solicitud;

/**
 * Clase DAO para manejar las operaciones de base de datos para la entidad Solicitud.
 * Implementa el patrón Singleton para asegurar una única instancia de la conexión a la base de datos.
 */
public class DaoSolicitud {

	public static Connection con = null;
	// Singelton
	private static DaoSolicitud instance = null;
	
	/**
     * Constructor que inicializa la conexión a la base de datos.
     * @throws SQLException Si no puede establecer una conexión con la base de datos.
     */
	public DaoSolicitud() throws SQLException {

		this.con = DBConexion.getConexion();

	}
	
	/**
     * Obtiene la única instancia de DaoSolicitud.
     * @return Instancia única de DaoSolicitud.
     * @throws SQLException Si ocurre un error al obtener la conexión a la base de datos.
     */
	// Singelton
	public static DaoSolicitud getInstance() throws SQLException {
		if (instance == null) {
			instance = new DaoSolicitud();
		}
		return instance;

	}

	/**
     * Inserta una nueva solicitud en la base de datos.
     * @param s Solicitud a insertar.
     * @throws SQLException Si ocurre un error durante la inserción de datos.
     */
	//Pertenece al formulario para usuarios externos. Limitado en campos
	public void insertar(Solicitud s) throws SQLException {
		// Solo pongo los campos que rellenarán los usuarios externos

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
	
	/**
     * Obtiene una solicitud por su ID.
     * @param id ID de la solicitud a buscar.
     * @return Solicitud encontrada.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
	public Solicitud obtenerPorId(int id) throws SQLException {
		// Aquí ya incluyo todos los campos. No solo los añadidos por el usuario externo
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
	
	/**
     * Obtiene una solicitud por DNI.
     * Este método es utilizado principalmente para administración y gestión de solicitudes individuales.
     * @param dni DNI del solicitante.
     * @return Solicitud correspondiente al DNI proporcionado.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
	public Solicitud obtenerPorDni(String dni) throws SQLException {
		// Aquí ya incluyo todos los campos.
		// Este es para listar el formulario enviado
		// O la consulta de estadoc de solicitud de buscador_solicitud.html
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
	//Creo que no lo uso finalmetne
	/*
	public Solicitud buscadorDni(String dni) throws SQLException {
		// Limitado a campos del usuario para comprobar solicitud
		// Este es para listar el formulario enviado y avisar al usuario
		String sql = "SELECT id,dni,cod_actividad, nombre, email, telefono, num_sorteo,seleccionado,pago, estado FROM solicitud WHERE dni=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dni);

		ResultSet rs = ps.executeQuery();

		rs.next();
        // Crear un nuevo objeto Solicitud pero ahora solo con los datos que necesito
		Solicitud s = new Solicitud();
        s.setId(rs.getInt("id"));
        s.setDni(rs.getString("dni"));
        s.setCod_actividad(rs.getInt("cod_actividad"));
        s.setNombre(rs.getString("nombre"));
        s.setEmail(rs.getString("email"));
        s.setTelefono(rs.getInt("telefono"));
        s.setNum_sorteo(rs.getInt("num_sorteo"));
        s.setSeleccionado(rs.getBoolean("seleccionado"));
        s.setPago(rs.getBoolean("pago"));
        s.setEstado(rs.getString("estado"));
        return s;*/
		/*Me recupera todos los campos poruqe me crea un objeto solicitud completo
		 * Solicitud s = new Solicitud(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5),
				rs.getInt(6), rs.getString(7));*/
/*
	}*/

	// Lo pongo private para dar seguridad. Llamaré al ListarJson
	private ArrayList<Solicitud> listar() throws SQLException {
		// Aquí directamente le paso la query
		PreparedStatement ps = con.prepareStatement("SELECT * FROM solicitud");

		ResultSet rs = ps.executeQuery();

		// el array que devuelvo
		ArrayList<Solicitud> result = null;

		// el puntero está en el null del principio y con el next va hasta el del final
		while (rs.next()) {
			// inicializar el result
			if (result == null) {

				result = new ArrayList<Solicitud>();
			}
			// solo defino el tipo y la posición pero también puedo poner el nombre, me guía
			// mejor
			result.add(new Solicitud(rs.getInt("id"), rs.getString("Dni"), rs.getInt("cod_actividad"),
					rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"), rs.getString("email"),
					rs.getString("direccion"), rs.getInt("telefono"), rs.getString("f_nacimiento"),
					rs.getInt("num_sorteo"), rs.getBoolean("seleccionado"), rs.getBoolean("pago"), rs.getString("estado")));

		}

		return result;

	}
	
	/**
     * Serializa y devuelve en formato JSON la lista de todas las solicitudes.
     * @return Una cadena JSON que representa la lista de todas las solicitudes.
     * @throws SQLException Si ocurre un error durante la consulta o la serialización a JSON.
     */
	public String listarJson() throws SQLException {
		// Meto en json lo que me devuelve del método listar
		String json = "";
		// Utilizo esta librería
		Gson gson = new Gson();

		json = gson.toJson(this.listar());

		return json;

	}
	
	/**
     * Actualiza los detalles de una solicitud en la base de datos.
     * @param s Solicitud con los detalles actualizados.
     * @return Solicitud actualizada.
     * @throws SQLException Si ocurre un error durante la actualización.
     */
	public Solicitud actualizar(Solicitud s) throws SQLException {
	    String sql = "UPDATE solicitud SET dni=?, cod_actividad=?, nombre=?, apellido1=?, apellido2=?, email=?, direccion=?, telefono=?, f_nacimiento=?, num_sorteo=?, seleccionado=?, pago=?, estado=? WHERE id = ?";
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
	    ps.setInt(14, s.getId()); // Al final porque es el criterio en el WHERE
	    int result = ps.executeUpdate();
	    ps.close();
	    return s;
	}
	
	/**
     * Elimina una solicitud de la base de datos utilizando su ID como identificador.
     * @param id ID de la solicitud a eliminar.
     * @throws SQLException Si ocurre un error durante el proceso de eliminación.
     */
	public void borrar(int id) throws SQLException {

		String sql = "DELETE FROM solicitud WHERE id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		int filas = ps.executeUpdate();
		ps.close();
	}
	
	/**
	 * Verifica si existe una solicitud con el DNI dado.
	 * Este método consulta para determinar si hay alguna solicitud asociada con el DNI dado.
	 * @param dni DNI a verificar.
	 * @return 
	 * <ul>
	 *     <li>true: si existe una solicitud con ese DNI.</li>
	 *     <li>false: si no existe ninguna solicitud con ese DNI.</li>
	 * </ul>
	 * @throws SQLException Si ocurre un error durante la consulta.
	 */
	//En el formulario solcititu del user externo.
	//Para eitar duplicados
	public boolean dniExiste(String dni) throws SQLException {
	    String sql = "SELECT COUNT(*) FROM solicitud WHERE dni=?";
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, dni);
	    ResultSet rs = ps.executeQuery();
	    rs.next();
	    return rs.getInt(1) > 0;  // Verifica si hay al menos un registro
	}
	
	/**
     * Asigna números de sorteo secuenciales a todas las solicitudes, ordenadas por ID.
     * Este método es utilizado para gestionar el proceso de selección basado en sorteos.
     * @throws SQLException Si ocurre un error durante la actualización de los datos.
     */
	//Una vez controlados por el admin las solictudes recibidas, para un proceso justo
	//Asignamos número según concurrencia
	public void asignarNumeros() throws SQLException {
        // Obtener todas las solicitudes ordenadas por id y ordenadas =
		String sql =  "SELECT id FROM solicitud ORDER BY id";
		PreparedStatement ps = con.prepareStatement(sql);
	    ResultSet rs = ps.executeQuery();


        // Actualizar el campo num_sorteo con un número correlativo
        int contador = 1;
        while (rs.next()) {
            String sqlUpdate = "UPDATE solicitud SET num_sorteo = ? WHERE id = ?";
            PreparedStatement updateStatement = con.prepareStatement(sqlUpdate);
            updateStatement.setInt(1, contador);
            updateStatement.setInt(2, rs.getInt("id"));
            updateStatement.executeUpdate();
            contador++;
        }
    }
}

    
