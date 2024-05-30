package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import modelo.Solicitud;

/**
 * Clase encargada de establecer y manejar la conexión con la base de datos.
 * Utiliza el patrón Singleton para asegurar que solo exista una instancia de la
 * conexión.
 * 
 * @author Inma López Ugalde
 * @version 15/05/2024
 * @see <a href="http://IP...">Aplicación</a>
 */
public class DBConexion {

	public static final String JDBC_URL = "jdbc:mysql://localhost:3306/verano";
	public static Connection instance = null;

	/**
	 * Obtiene la conexión a la base de datos. Si la instancia no existe, crea una
	 * nueva utilizando las propiedades especificadas.
	 * 
	 * @return La instancia única de {@link Connection} para la base de datos.
	 * @throws SQLException Si ocurre un error al intentar establecer la conexión
	 *                      con la base de datos.
	 */
	public static Connection getConexion() throws SQLException {

		if (instance == null) {
			Properties props = new Properties();
			props.put("user", "root");
			props.put("password", "");
			props.put("charset", "UTF-8");

			instance = DriverManager.getConnection(JDBC_URL, props);
		}
		return instance;

	}

}