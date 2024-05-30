package modelo;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import com.google.gson.Gson;

import dao.DaoActividad;
import dao.DaoAdministrador;

/**
 * Clase que representa una administrador en el sistema. Los atributos
 * relevantes para el administrador son: id (como identificador único), usuario
 * y passs.
 * 
 * @author Inma López Ugalde
 * @version 15/05/2024
 * @see <a href="http://IP...">Aplicación</a>
 */
public class Administrador {
	private int id;
	private String usuario;
	private String pass;

	/**
	 * Constructor por defecto.
	 */
	public Administrador() {

	}

	/**
	 * Constructor con todos los parámetros para un Administrador.
	 * 
	 * @param id      El identificador único del administrador.
	 * @param usuario El nombre de usuario del administrador.
	 * @param pass    La contraseña del administrador.
	 */
	public Administrador(int id, String usuario, String pass) {
		this.id = id;
		this.usuario = usuario;
		this.pass = pass;
	}

	/**
	 * Constructor para crear un administrador sin especificar el ID, se asignará en
	 * la base de datos
	 * 
	 * @param usuario El nombre de usuario del administrador.
	 * @param pass    La contraseña del administrador.
	 */
	// otro sin ID
	public Administrador(String usuario, String pass) {
		super();
		this.usuario = usuario;
		this.pass = pass;
	}

	/**
	 * Constructor usado para consultas donde solo tenemos en cuenta el ID y el
	 * usuario.
	 * 
	 * @param id      El identificador único del administrador.
	 * @param usuario El nombre de usuario del administrador.
	 */
	// para consulta
	public Administrador(int id, String usuario) {
		super();
		this.id = id;
		this.usuario = usuario;
	}

	// Getters y setters

	/**
	 * Obtiene el identificador del administrador.
	 * 
	 * @return Identificador del administrador.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Establece el identificador del administrador.
	 * 
	 * @param id Identificador del administrador.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Obtiene el nombre/alias de usuario.
	 * 
	 * @return Nombre/alias
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Establece el nombre/alias de usuario.
	 * 
	 * @param usuario Nombre/alias de usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Obtiene el password.
	 * 
	 * @return Password.
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * Establece el password.
	 * 
	 * @param pass Password.
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * Inserta un nuevo administrador en la base de datos. Utiliza el patrón
	 * Singleton para obtener la instancia de {@link DaoAdministrador} y llama al
	 * método {@code insertar} de esa clase para realizar la inserción en la base de
	 * datos.
	 *
	 * @throws SQLException Si ocurre un error durante el proceso de inserción en la
	 *                      base de datos.
	 */
	public void insertar() throws SQLException {

		DaoAdministrador.getInstance().insertar(this);
	}

	/**
	 * Recupera un administrador de la base de datos utilizando su identificador y
	 * actualiza las propiedades de esta instancia con los valores obtenidos.
	 * Utiliza el patrón Singleton para acceder a {@link DaoAdministrador} y
	 * recupera los detalles del administrador especificado por el id proporcionado.
	 *
	 * @param id Identificador del administrador que se desea recuperar. Este código
	 *           es usado para localizar un administrador específico en la base de
	 *           datos.
	 * @throws SQLException Si ocurre un error durante la recuperación de la
	 *                      actividad desde la base de datos.
	 */
	public void obtenerPorId(int id) throws SQLException {

		DaoAdministrador dao = DaoAdministrador.getInstance();
		Administrador aux = dao.obtenerPorId(id);

		// Establecer propiedades de la actividad actual con los valores del actividad
		// seleccionada
		this.setId(aux.getId());
		this.setUsuario(aux.getUsuario());
		this.setPass(aux.getPass());

	}

	/**
	 * Devuelve una representación JSON del objeto administrador.
	 * 
	 * @return Una cadena JSON que representa este objeto.
	 */
	public String dameJson() {
		String json = "";

		Gson gson = new Gson();

		json = gson.toJson(this);
		return json;

	}

	/**
	 * Actualiza la información del administrador actual en la base de datos. La
	 * operación se realiza a través de la clase {@code DaoAdministrador}, que es
	 * responsable de la lógica de acceso a datos.
	 * 
	 * @throws SQLException Si ocurre un error durante la actualización en la base
	 *                      de datos.
	 */
	public void actualizar() throws SQLException {
		DaoAdministrador.getInstance().actualizar(this);
	}

	/**
	 * Elimina un administrador especificado por el id. La clase
	 * {@code DaoAdministrador} es responsable de manejar todas las interacciones
	 * con la base de datos relacionadas con administradores.
	 *
	 * @param id Identificador de administrador que se quiere eliminar.
	 * @throws SQLException Si ocurre un error durante el proceso de eliminación en
	 *                      la base de datos.
	 */
	public void borrar(int id) throws SQLException {
		DaoAdministrador.getInstance().borrar(id);
	}

	/**
	 * Intenta iniciar sesión verificando la contraseña proporcionada contra la
	 * almacenada en la base de datos. Utiliza el método {@code dao.logueando} de la
	 * clase {@code DaoAdministrador} para verificar si el usuario existe y si la
	 * contraseña proporcionada coincide con la almacenada en la base de datos. Si
	 * la validación es exitosa, actualiza la instancia actual con los datos del
	 * usuario recuperados.
	 *
	 * @param pass La contraseña proporcionada para el inicio de sesión.
	 * @return
	 *         <ul>
	 *         <li>true: si el inicio de sesión es exitoso. Esto ocurre si el
	 *         usuario existe y la contraseña coincide.</li>
	 *         <li>false: si no existe un usuario con la contraseña proporcionada o
	 *         si la contraseña no coincide.</li>
	 *         </ul>
	 * @throws SQLException Si ocurre un error durante la consulta a la base de
	 *                      datos, como problemas de conexión o errores en la
	 *                      ejecución de la consulta.
	 */
	// Inicializamos en false.
	public boolean logueo(String pass) throws SQLException {

		boolean ok = false;

		DaoAdministrador dao = DaoAdministrador.getInstance();
		Administrador aux = dao.logueando(this, pass);

		if (aux != null) {
			ok = true;
			this.setId(aux.getId());
			this.setUsuario(aux.getUsuario());
		}
		return ok;
	}

	/**
	 * Convierte un String proporcionado en su equivalente cifrado usando el
	 * algoritmo MD5. Este método coje la contraseña en texto plano y devuelve su
	 * hash MD5. Es utilizado para generar hashes seguros de contraseñas para
	 * almacenamiento y verificación.
	 *
	 * @param pass La contraseña en texto plano que se desea cifrar.
	 * @return El hash MD5 de la contraseña.
	 * @throws RuntimeException Si el sistema no puede encontrar o usar el algoritmo
	 *                          MD5, salta la excepción para indicar que algo salió
	 *                          mal con el proceso de cifrado.
	 */
	public static String myMD5(String pass) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(pass.getBytes());
			byte[] digest = md.digest();
			BigInteger no = new BigInteger(1, digest);
			String hashtext = no.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Proporciona una representación de la instancia de Administrador. Esta
	 * representación incluye todos los atributos del objeto administrador.
	 * 
	 * @return Una cadena que representa la instancia de Administrador en el
	 *         formato: "Admin [id={id}, usuario={usuario}, pass={pass}]"
	 */
	@Override
	public String toString() {
		return "Admin [id=" + id + ", usuario=" + usuario + ", pass=" + pass + "]";
	}

}
