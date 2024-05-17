package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Actividad;

/**
 * Clase DAO para manejar las operaciones de base de datos relacionadas con las entidades de tipo {@link Actividad}.
 * Utiliza el patrón Singleton para asegurar una única instancia de conexión y acceso a la base de datos.
 */
public class DaoActividad {

	public static Connection con = null;
	// Singelton
	private static DaoActividad instance = null;
	
	/**
     * Constructor privado que inicializa la conexión a la base de datos.
     * @throws SQLException Si no puede establecer una conexión con la base de datos.
     */
	public DaoActividad() throws SQLException {

		this.con = DBConexion.getConexion();

	}

	/**
     * Método para obtener la instancia única de DaoActividad.
     * @return Instancia única de DaoActividad.
     * @throws SQLException Si ocurre un error al crear la instancia o al obtener la conexión.
     */
	// Singelton
	public static DaoActividad getInstance() throws SQLException {
		if (instance == null) {
			instance = new DaoActividad();
		}
		return instance;

	}
	
	/**
     * Inserta una nueva actividad en la base de datos.
     * @param a Actividad a insertar.
     * @throws SQLException Si ocurre un error durante la inserción de datos.
     */
	public void insertar(Actividad a) throws SQLException {
		// Inserto en la bdd con esas variables y luego hago la conexión con esa query
		String sql = "INSERT INTO actividad (nombreA,lugar,tema,descripcion,imagen,f_inicio,f_fin,e_min,e_max,plazas) VALUES (?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, a.getNombreA());
		ps.setString(2, a.getLugar());
		ps.setString(3, a.getTema());
		ps.setString(4, a.getDescripcion());
		ps.setString(5, a.getImagen());
		ps.setString(6, a.getF_inicio());
		ps.setString(7, a.getF_fin());
		ps.setInt(8, a.getE_min());
		ps.setInt(9, a.getE_max());
		ps.setInt(10, a.getPlazas());

		int filas = ps.executeUpdate();
		ps.close();

	}
	
	/**
     * Obtiene una actividad por el código de actividad.
     * @param cod_actividad El código de la actividad a buscar.
     * @return Actividad encontrada.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
	public Actividad obtenerPorCod_actividad(int cod_actividad) throws SQLException {

		String sql = "SELECT * FROM actividad WHERE cod_actividad=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, cod_actividad);

		ResultSet rs = ps.executeQuery();

		rs.next();

		Actividad a = new Actividad(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
				rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getInt(11));

		return a;
	}
	
	/**
     * Obtiene actividades por el tema seleccionado.
     * @param tema Tema de la actividad a buscar.
     * @return Actividad encontrada.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
	public Actividad obtenerPorTema(String tema) throws SQLException {
		//1 paso. conecto y le paso la query directamente
		// Para actividad.html, me viene por enlace
		PreparedStatement ps = con.prepareStatement("SELECT * FROM actividad WHERE tema=?");
		ps.setString(1, tema);

		ResultSet rs = ps.executeQuery();

		rs.next();

		Actividad at = new Actividad(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
				rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getInt(11));

		return at;
	}
	
	/**
     * Devuelve todos los códigos de actividad de la base de datos.
     * @return Lista de códigos de actividades.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
	//Lista para el select de cod_actividad del formulario
	public ArrayList<Integer> selectCodigo() throws SQLException {
        ArrayList<Integer> codigo = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("SELECT cod_actividad FROM actividad");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            codigo.add(rs.getInt("cod_actividad"));
        }
        return codigo;
    }


	//Lista todas las actividades disponibles, para la tabla
	// Lo pongo private para dar seguridad. Llamaré al ListarJson
	private ArrayList<Actividad> listar() throws SQLException {
		// conecto y le paso la query directamente
		PreparedStatement ps = con.prepareStatement("SELECT * FROM actividad");
		ResultSet rs = ps.executeQuery();

		// el array que devuelvo
		ArrayList<Actividad> result = null;

		// el puntero está en el null del principio y con el next va hasta el del final
		while (rs.next()) {
			// inicializar el result
			if (result == null) {

				result = new ArrayList<Actividad>();
			}
			// solo defino el tipo y la posición pero también puedo poner el nombre, me guía
			// mejor
			result.add(new Actividad(rs.getInt("cod_actividad"), rs.getString("nombreA"), rs.getString("lugar"),
					rs.getString("tema"), rs.getString("descripcion"), rs.getString("imagen"), rs.getString("f_inicio"),
					rs.getString("f_fin"), rs.getInt("e_min"), rs.getInt("e_max"), rs.getInt("plazas")));

		}

		return result;

	}

	/**
     * Lista todas las actividades disponibles en la base de datos y las devuelve en formato JSON.
     * @return Una cadena JSON que representa la lista de todas las actividades.
     * @throws SQLException Si ocurre un error al recuperar los datos o al convertirlos a JSON.
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
     * Actualiza los detalles de una actividad en la base de datos.
     * @param a Actividad con los detalles actualizados.
     * @return Actividad actualizada.
     * @throws SQLException Si ocurre un error durante la actualización.
     */
	public Actividad actualizar(Actividad a) throws SQLException {

		String sql = "UPDATE actividad SET nombreA = ?, lugar = ?, tema = ?, descripcion = ?, imagen = ?, f_inicio = ?, f_fin = ?, e_min = ?, e_max = ?, plazas = ? WHERE cod_actividad = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, a.getNombreA());
		ps.setString(2, a.getLugar());
		ps.setString(3, a.getTema());
		ps.setString(4, a.getDescripcion());
		ps.setString(5, a.getImagen());
		ps.setString(6, a.getF_inicio());
		ps.setString(7, a.getF_fin());
		ps.setInt(8, a.getE_min());
		ps.setInt(9, a.getE_max());
		ps.setInt(10, a.getPlazas());
		ps.setInt(11, a.getCod_actividad()); // Al final porque es el criterio en el WHERE

		int result = ps.executeUpdate();

		ps.close();
		return a;
	}
	
	/**
     * Elimina una actividad de la base de datos utilizando su código de actividad como identificador.
     * @param cod_actividad Código de la actividad a eliminar.
     * @throws SQLException Si ocurre un error durante el proceso de eliminación.
     */
	public void borrar(int cod_actividad) throws SQLException {

		String sql = "DELETE FROM actividad WHERE cod_actividad=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, cod_actividad);
		int filas = ps.executeUpdate();
		ps.close();
	}
	
	/*public ArrayList<Actividad> listarActividades() throws SQLException {
    	// Directamente le paso la query
    	PreparedStatement ps = con.prepareStatement("SELECT cod_actividad, nombreA, tema FROM actividades");
    	ResultSet rs = ps.executeQuery();
    	//Array que devuelve
    	ArrayList<Actividad> actividades = null;
          while (rs.next()) {
        	  //inicializar actividades
        	  if (actividades == null) {
        		  actividades = new ArrayList<Actividad>();
        	  }
                actividades.add(new Actividad(rs.getInt("cod_actividad"), rs.getString("nombreA"), rs.getString("tema")));
            }
        return actividades;
    }*/

}
