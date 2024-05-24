package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Actividad;
import modelo.Administrador;

/**
 * Clase DAO para manejar las operaciones de la base de datos relacionadas con las entidades de tipo {@link Administrador}
 * Implementa el patr�n Singleton para asegurar una �nica instancia de la conexi�n a la base de datos.
 * @author Inma L�pez Ugalde
 * @version 17/05/2024
 * @see <a href="http://IP...">Aplicaci�n</a>
 */
public class DaoAdministrador {

	public static Connection con = null;
	// Singelton
	private static DaoAdministrador instance = null;
	
	/**
     * Constructor privado que inicializa la conexi�n a la base de datos.
     * @throws SQLException Si no puede establecer una conexi�n con la base de datos.
     */
	public DaoAdministrador() throws SQLException {

		this.con = DBConexion.getConexion();

	}
	
	/**
     * Obtiene la �nica instancia de DaoAdministrador.
     * @return la instancia �nica de DaoAdministrador.
     * @throws SQLException Si ocurre un error al obtener la conexi�n a la base de datos.
     */
	// Singelton
	public static DaoAdministrador getInstance() throws SQLException {
		if (instance == null) {
			instance = new DaoAdministrador();
		}
		return instance;

	}
	
	/**
     * Inserta un nuevo administrador en la base de datos.
     * @param ad Administrador a insertar.
     * @throws SQLException Si ocurre un error durante la inserci�n de datos.
     */
	public void insertar(Administrador ad) throws SQLException {
		// Inserto en la bdd con esas variables y conexi�n
		String sql = "INSERT INTO admin (usuario,pass) VALUES (?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, ad.getUsuario());
		ps.setString(2, ad.getPass());

		int filas = ps.executeUpdate();
		ps.close();

	}
	
	/**
     * Obtiene un administrador por su ID.
     * @param id El ID del administrador a buscar.
     * @return El administrador encontrado.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
	public Administrador obtenerPorId(int id) throws SQLException {

		String sql = "SELECT * FROM admin WHERE id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		rs.next();

		Administrador ad = new Administrador(rs.getInt(1), rs.getString(2), rs.getString(3));

		return ad;
	}
	
	/**
     * Verifica las credenciales de un administrador para el proceso de login.
     * @param a Administrador que intenta loguearse.
     * @param pass Contrase�a proporcionada.
     * @return Administrador si las credenciales son correctas, null en caso contrario.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
	public Administrador logueando(Administrador a, String pass) throws SQLException {

		String sql = "SELECT * FROM admin WHERE usuario=? AND pass=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, a.getUsuario());
		ps.setString(2, pass);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
		    Administrador aux = new Administrador(rs.getInt(1), rs.getString(2), rs.getString(3));
		    return aux;
		} else {
		    return null; // Error empty rs
		}
	}

	// Lo pongo private para dar seguridad. Llamar� al ListarJson
	private ArrayList<Administrador> listar() throws SQLException {
		// Aqu� directamente le paso la query
		PreparedStatement ps = con.prepareStatement("SELECT * FROM admin");

		ResultSet rs = ps.executeQuery();

		// el array que devuelvo
		ArrayList<Administrador> result = null;

		// el puntero est� en el null del principio y con el next va hasta el del final
		while (rs.next()) {
			// inicializar el result
			if (result == null) {

				result = new ArrayList<Administrador>();
			}
			// solo defino el tipo y la posici�n pero tambi�n puedo poner el nombre, me gu�a
			// mejor
			result.add(new Administrador(rs.getInt("id"), rs.getString("usuario"), rs.getString("pass")));

		}

		return result;

	}
	
	/**
     * Lista todos los administradores registrados en la base de datos y devuelve los datos en formato JSON.
     * @return Cadena JSON que representa la lista de todos los administradores.
     * @throws SQLException Si ocurre un error durante la consulta o la serializaci�n a JSON.
     */
	public String listarJson() throws SQLException {
		// Meto en json lo que me devuelve del m�todo listar
		String json = "";
		// Utilizo esta librer�a
		Gson gson = new Gson();

		json = gson.toJson(this.listar());

		return json;

	}
	
	/**
     * Actualiza los detalles de un administrador en la base de datos.
     * @param a Administrador con los detalles actualizados.
     * @return Administrador actualizado.
     * @throws SQLException Si ocurre un error durante la actualizaci�n.
     */
	public Administrador actualizar(Administrador a) throws SQLException {

		String sql = "UPDATE admin SET usuario = ?, pass = ? WHERE id = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, a.getUsuario());
		ps.setString(2, a.getPass());
		ps.setInt(3, a.getId()); // Al final porque es el criterio en el WHERE

		int result = ps.executeUpdate();

		ps.close();
		return a;
	}
	
	/**
     * Elimina un administrador de la base de datos usando su ID como clave.
     * @param id ID del administrador a eliminar.
     * @throws SQLException Si ocurre un error durante el proceso de eliminaci�n.
     */
	public void borrar(int id) throws SQLException {

		String sql = "DELETE FROM admin WHERE id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		int filas = ps.executeUpdate();
		ps.close();
	}
	
	/**
	 * Verifica si existe un administrador con el nombre de usuario dado.
	 * Este m�todo consulta la base de datos para comprobar si ya existe un registro con el nombre de usuario especificado.
	 *
	 * @param usuario Nombre de usuario a verificar.
	 * @return 
	 * <ul>
	 *     <li>true: si el usuario existe en la base de datos.</li>
	 *     <li>false: si el usuario no existe en la base de datos.</li>
	 * </ul>
	 * @throws SQLException Si ocurre un error durante la consulta.
	 */
	//COmpruebo antes de a�adir para no sobrescribir
	public boolean usuarioExiste(String usuario) throws SQLException {
	    String sql = "SELECT COUNT(*) FROM admin WHERE usuario=?";
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, usuario);
	    ResultSet rs = ps.executeQuery();
	    rs.next();
	    return rs.getInt(1) > 0;  // Verifica si hay al menos un registro
	}

}
