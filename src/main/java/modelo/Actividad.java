package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import dao.DaoActividad;

/**
 * Clase que representa una actividad en la aplicación. Los atributos relevantes
 * para una actividad son: código de actividad(como identificador único),
 * nombre, lugar, tema, descripción, imagen, fecha de inicio, fecha de fin, edad
 * máxima permitida, edad mínima permitida y número de plazas.
 * 
 * @author Inma López Ugalde
 * @version 15/05/2024
 * @see <a href="http://IP...">Aplicación</a>
 */
public class Actividad {
	private int cod_actividad;
	private String nombreA;
	private String lugar;
	private String tema;
	private String descripcion;
	private String imagen;
	private String f_inicio;
	private String f_fin;
	private int e_min;
	private int e_max;
	private int plazas;

	/**
	 * Constructor por defecto
	 */
	public Actividad() {

	}

	/**
	 * Constructor para una actividad completa. Será utilizado por el administrador
	 * para listar actividades con la totalidad de los datos
	 * 
	 * @param cod_actividad Identificador de la actividad, clave primaria
	 * @param nombreA       Nombre de la actividad
	 * @param lugar         Lugar donde se realiza la actividad
	 * @param tema          Tema de la actividad
	 * @param descripcion   Descripción breve
	 * @param imagen        Nombre del archivo imagen alojado en el servidor
	 * @param f_inicio      Fecha de inicio
	 * @param f_fin         Fecha fin
	 * @param e_min         Edad mínima requerida
	 * @param e_max         Edad máxima permitida
	 * @param plazas        Número de plazas disponibles
	 */
	public Actividad(int cod_actividad, String nombreA, String lugar, String tema, String descripcion, String imagen,
			String f_inicio, String f_fin, int e_min, int e_max, int plazas) {
		this.cod_actividad = cod_actividad;
		this.nombreA = nombreA;
		this.lugar = lugar;
		this.tema = tema;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.f_inicio = f_inicio;
		this.f_fin = f_fin;
		this.e_min = e_min;
		this.e_max = e_max;
		this.plazas = plazas;
	}

	/**
	 * Constructor que se usará para la creación de actividades, ya que el
	 * identificador será asignado por la base de datos.
	 * 
	 * @param nombreA     Nombre de la actividad
	 * @param lugar       Lugar donde se realiza la actividad
	 * @param tema        Tema de la actividad
	 * @param descripcion Descripción breve
	 * @param imagen      Ruta de la imagen
	 * @param f_inicio    Fecha de inicio
	 * @param f_fin       Fecha fin
	 * @param e_min       Edad mínima requerida
	 * @param e_max       Edad máxima permitida
	 * @param plazas      Número de plazas disponibles
	 */
	// Otro sin Cod_actividad para mandar a la BDD
	public Actividad(String nombreA, String lugar, String tema, String descripcion, String imagen, String f_inicio,
			String f_fin, int e_min, int e_max, int plazas) {
		super();
		this.nombreA = nombreA;
		this.lugar = lugar;
		this.tema = tema;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.f_inicio = f_inicio;
		this.f_fin = f_fin;
		this.e_min = e_min;
		this.e_max = e_max;
		this.plazas = plazas;
	}

	/**
	 * Constructor que se utilizará para mostrar las actividades disponibles en la
	 * base de datos. El resultado será mostrado en un select en el formulario de
	 * Solicitud para que el solicitante elija en qué actividad quiere participar.
	 * 
	 * @param tema Tema de la actividad
	 */
	// Para el Select del formulario solicitud
	/*
	 * public Actividad(String tema) { super(); this.tema = tema; }
	 */

	// Empiezan los Getters y Setters

	/**
	 * Obtiene el código identificador de la actividad.
	 * 
	 * @return Código identificador de la actividad.
	 */
	public int getCod_actividad() {
		return cod_actividad;
	}

	/**
	 * Establece el código identificador de la actividad.
	 * 
	 * @param cod_actividad Código identificador para la actividad.
	 */
	public void setCod_actividad(int cod_actividad) {
		this.cod_actividad = cod_actividad;
	}

	/**
	 * Obtiene el nombre de la actividad.
	 * 
	 * @return Nombre de la actividad.
	 */
	public String getNombreA() {
		return nombreA;
	}

	/**
	 * Establece el nombre de la actividad.
	 * 
	 * @param nombreA Nombre para la actividad.
	 */
	public void setNombreA(String nombreA) {
		this.nombreA = nombreA;
	}

	/**
	 * Obtiene el lugar de realización
	 * 
	 * @return Lugar de realización
	 */
	public String getLugar() {
		return lugar;
	}

	/**
	 * Establece el lugar de realización
	 * 
	 * @param lugar Lugar de realización de la actividad
	 */
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	/**
	 * Obtiene el tema de la actividad
	 * 
	 * @return Tema de la actividad
	 */
	public String getTema() {
		return tema;
	}

	/**
	 * Establece el tema de la actividad
	 * 
	 * @param tema Tema de la actividad
	 */
	public void setTema(String tema) {
		this.tema = tema;
	}

	/**
	 * Obtiene la descripción breve de la actividad
	 * 
	 * @return Descripción de la actividad
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Establece la descripción de la actividad
	 * 
	 * @param descripcion Descripción de la actividad
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Obtiene el nombre del archivo de la imagen alojada en la carpeta del servidor
	 * 
	 * @return Nombre del archivo de imagen
	 */
	public String getImagen() {
		return imagen;
	}

	/**
	 * Establece el nombre del archivo de la imagen que se alojará en la carpeta del
	 * servidor
	 * 
	 * @param imagen Nombre del archivo de imagen
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	/**
	 * Obtiene la fecha de inicio de la actividad
	 * 
	 * @return Fecha de inicio
	 */
	public String getF_inicio() {
		return f_inicio;
	}

	/**
	 * Establece la fecha de inicio de la actividad
	 * 
	 * @param f_inicio Fecha de inicio
	 */
	public void setF_inicio(String f_inicio) {
		this.f_inicio = f_inicio;
	}

	/**
	 * Obtiene la fecha de fin de la actividad
	 * 
	 * @return Fecha fin
	 */
	public String getF_fin() {
		return f_fin;
	}

	/**
	 * Establece la fecha de fin de actividad
	 * 
	 * @param f_fin Fecha fin
	 */
	public void setF_fin(String f_fin) {
		this.f_fin = f_fin;
	}

	/**
	 * Obtiene la edad mínima con la que se puede participar en la actividad
	 * 
	 * @return Edad mínima con la se permie participar
	 */
	public int getE_min() {
		return e_min;
	}

	/**
	 * Establece la edad mínima permitida para participar
	 * 
	 * @param e_min Edad mínínma
	 */
	public void setE_min(int e_min) {
		this.e_min = e_min;
	}

	/**
	 * Obtiene la edad máxima con la que se puede participar en la actividad
	 * 
	 * @return Edad máxima con la se permie participar
	 */
	public int getE_max() {
		return e_max;
	}

	/**
	 * Establece la edad máxima permitida para participar
	 * 
	 * @param e_min Edad máxima
	 */
	public void setE_max(int e_max) {
		this.e_max = e_max;
	}

	/**
	 * Obtiene el número de plazas disponibles en la actividad
	 * 
	 * @return Número de plazas disponibles
	 */
	public int getPlazas() {
		return plazas;
	}

	/**
	 * Establece el número de plazas disponibles para la actividad
	 * 
	 * @param n_plazas Número de plazas
	 */
	public void setPlazas(int n_plazas) {
		this.plazas = n_plazas;
	}

	/**
	 * Inserta la instancia actual de la actividad en la base de datos. Utiliza el
	 * patrón Singleton para obtener la instancia de {@link DaoActividad} y llama al
	 * método {@code insertar} de esa clase para realizar la inserción en la base de
	 * datos.
	 *
	 * @throws SQLException Si ocurre un error durante el proceso de inserción en la
	 *                      base de datos.
	 */
	public void insertar() throws SQLException {

		/*
		 * DaoActividad dao = new DaoActividad(); dao.Insertar(this);
		 */
		// SINGELTON. Llamo al método estático getInstance y desde ahí ya tendría
		// disponible ().todos los métodos
		DaoActividad.getInstance().insertar(this);
	}

	/**
	 * Recupera una actividad de la base de datos utilizando su código
	 * identificativo y actualiza las propiedades de esta instancia con los valores
	 * obtenidos. Utiliza el patrón Singleton para acceder a {@link DaoActividad} y
	 * recupera los detalles de la actividad especificada por el código de actividad
	 * proporcionado.
	 *
	 * @param cod_actividad El código identificativo de la actividad que se desea
	 *                      obtener. Este código es usado para localizar la
	 *                      actividad específica en la base de datos.
	 * @throws SQLException Si ocurre un error durante la recuperación de la
	 *                      actividad desde la base de datos.
	 */
	public void obtenerPorCod_actividad(int cod_actividad) throws SQLException {

		DaoActividad dao = DaoActividad.getInstance();
		Actividad aux = dao.obtenerPorCod_actividad(cod_actividad);

		// Establecer propiedades de la actividad actual con los valores del actividad
		// seleccionada
		this.setCod_actividad(aux.getCod_actividad());
		this.setNombreA(aux.getNombreA());
		this.setLugar(aux.getLugar());
		this.setTema(aux.getTema());
		this.setDescripcion(aux.getDescripcion());
		this.setImagen(aux.getImagen());
		this.setF_inicio(aux.getF_inicio());
		this.setF_fin(aux.getF_fin());
		this.setE_min(aux.getE_min());
		this.setE_max(aux.getE_max());
		this.setPlazas(aux.getPlazas());

	}

	/**
	 * Recupera una actividad de la base de datos utilizando su tema y actualiza las
	 * propiedades de esta instancia con los valores obtenidos. Utiliza el patrón
	 * Singleton para acceder a {@link DaoActividad} y recupera los detalles de la
	 * actividad especificada por el tema proporcionado.
	 *
	 * @param tema El tema de la actividad que se desea obtener. Este tema es usado
	 *             para localizar la actividad específica en la base de datos.
	 * @throws SQLException Si ocurre un error durante la recuperación de la
	 *                      actividad desde la base de datos.
	 */
	// Igual que por código pero por tema para listar una a una
	public void obtenerPorTema(String tema) throws SQLException {

		DaoActividad dao = DaoActividad.getInstance();
		Actividad aux = dao.obtenerPorTema(tema);

		// Establezco los valores del actividad seleccionada ayudandome del aux
		this.setCod_actividad(aux.getCod_actividad());
		this.setNombreA(aux.getNombreA());
		this.setLugar(aux.getLugar());
		this.setTema(aux.getTema());
		this.setDescripcion(aux.getDescripcion());
		this.setImagen(aux.getImagen());
		this.setF_inicio(aux.getF_inicio());
		this.setF_fin(aux.getF_fin());
		this.setE_min(aux.getE_min());
		this.setE_max(aux.getE_max());
		this.setPlazas(aux.getPlazas());

	}

	/**
	 * Serializa la instancia actual en una cadena JSON. Este método está diseñado
	 * para reutilización, facilitando la recuperación por 'Cod_actividad' y por
	 * 'tema'. Utiliza la biblioteca Gson para la serialización.
	 *
	 * @return Una cadena en formato JSON que representa la instancia actual.
	 */
	// Reutilizo para recuperar por Cod_actividad y por tema
	public String dameJson() {
		String json = "";

		Gson gson = new Gson();

		json = gson.toJson(this);
		return json;

	}

	/**
	 * Serializa una lista de códigos enteros a una cadena en formato JSON. Este
	 * método es útil para generar la lista de selección de temas para el formulario
	 * público, donde los códigos representan los temas de las actividades. Utiliza
	 * la biblioteca Gson para la serialización.
	 *
	 * @param codigo La lista de códigos enteros que se desea serializar.
	 * @return Una cadena en formato JSON que representa la lista de códigos.
	 */
	// Para la lista del select de temas
	public static String dameJsonCodigo(ArrayList<Integer> codigo) {
		Gson gson = new Gson();
		return gson.toJson(codigo);
	}

	/**
	 * Actualiza la información de la actividad actual en la base de datos. La
	 * operación se realiza a través de la clase {@code DaoActividad}, que es
	 * responsable de la lógica de acceso a datos.
	 * 
	 * @throws SQLException Si ocurre un error durante la actualización en la base
	 *                      de datos.
	 */
	public void actualizar() throws SQLException {
		DaoActividad.getInstance().actualizar(this);
	}

	/**
	 * Elimina la actividad especificada por el código de actividad de la base de
	 * datos. La clase {@code DaoActividad} es responsable de manejar todas las
	 * interacciones con la base de datos relacionadas con las actividades.
	 *
	 * @param cod_actividad El código de la actividad que se desea eliminar.
	 * @throws SQLException Si ocurre un error durante el proceso de eliminación en
	 *                      la base de datos.
	 */
	public void borrar(int cod_actividad) throws SQLException {
		DaoActividad.getInstance().borrar(cod_actividad);
	}

	/**
	 * Devuelve una representación de la instancia de Actividad. Esta representación
	 * incluye todos los detalles del objeto Actividad.
	 * 
	 * @return Una cadena de texto que representa los detalles de la Actividad en el
	 *         formato: "Actividad [cod_actividad={codigo}, nombreA={nombre},
	 *         lugar={lugar}, tema={tema}, descripcion={descripcion},
	 *         imagen={imagen}, f_inicio={fecha_inicio}, f_fin={fecha_fin},
	 *         e_min={edad_minima}, e_max={edad_maxima}, plazas={plazas}]"
	 */
	@Override
	public String toString() {
		return "Actividad [cod_actividad=" + cod_actividad + ", nombreA=" + nombreA + ", lugar=" + lugar + ", tema="
				+ tema + ", descripcion=" + descripcion + ", imagen=" + imagen + ", f_inicio=" + f_inicio + ", f_fin="
				+ f_fin + ", e_min=" + e_min + ", e_max=" + e_max + ", plazas=" + plazas + "]";
	}

}
