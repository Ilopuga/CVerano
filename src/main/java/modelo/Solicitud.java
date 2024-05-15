package modelo;

import java.sql.SQLException;

import com.google.gson.Gson;

import dao.DaoActividad;
import dao.DaoAdministrador;
import dao.DaoSolicitud;

/**
 * Clase que representa una solicitud. Los atributos relevantes para una
 * solicitud son: id (como identificador único), dni, cod_actividad (relacionado
 * con clase actividad nombre, primer apellido, segundo apellido, email,
 * dirección completa, teléfono, fecha de nacimiento número para el sorteo,
 * seleccionado, pagado y estado de la solicitud.
 * 
 * @author Inma López Ugalde
 * @version 15/05/2024
 * @see <a href="http://IP...">Aplicación</a>
 */
public class Solicitud {
	private int id;
	private String dni;
	private int cod_actividad;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String email;
	private String direccion;
	private int telefono;
	private String f_nacimiento;
	private int num_sorteo;
	private boolean seleccionado;
	private boolean pago;
	private String estado;

	/**
	 * Constructor por defecto.
	 */
	public Solicitud() {

	}

	/**
	 * Constructor para instanciar una solictud completa. Será utilizado por el
	 * administrador para listar solcitudes con la totalidad de los datos y poder
	 * gestionar cualquiera.
	 * 
	 * @param id            Identificador único de la solicitud.
	 * @param dni           Documento Nacional de Identidad del solicitante.
	 * @param cod_actividad Código de la actividad a la que se postula la solicitud.
	 * @param nombre        Nombre del solicitante.
	 * @param apellido1     Primer apellido del solicitante.
	 * @param apellido2     Segundo apellido del solicitante.
	 * @param email         Dirección de correo electrónico del solicitante.
	 * @param direccion     Dirección física del solicitante.
	 * @param telefono      Número de teléfono.
	 * @param f_nacimiento  Fecha de nacimiento.
	 * @param num_sorteo    Número asignado para entrar en el sorteode plazas.
	 * @param seleccionado
	 *                      <ul>
	 *                      <li>true: El solicitante ha sido seleccionado para la
	 *                      actividad.</li>
	 *                      <li>false: El solicitante no ha sido seleccionado para
	 *                      la actividad.</li>
	 *                      </ul>
	 * @param pago
	 *                      <ul>
	 *                      <li>true: El solicitante ha realizado el pago.</li>
	 *                      <li>false: El solicitante no ha realizado el pago.</li>
	 *                      </ul>
	 * @param estado        Estado de la solicitud (ejemplo: "Recibida",
	 *                      "Seleccionado", "Reserva", "Anulada").
	 */
	public Solicitud(int id, String dni, int cod_actividad, String nombre, String apellido1, String apellido2,
			String email, String direccion, int telefono, String f_nacimiento, int num_sorteo, boolean seleccionado,
			boolean pago, String estado) {
		super();
		this.id = id;
		this.dni = dni;
		this.cod_actividad = cod_actividad;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.email = email;
		this.direccion = direccion;
		this.telefono = telefono;
		this.f_nacimiento = f_nacimiento;
		this.num_sorteo = num_sorteo;
		this.seleccionado = seleccionado;
		this.pago = pago;
		this.estado = estado;
	}

	/**
	 * Constructor para una solicitud completa sin identificador.
	 * Será utilizado por el usuario externo para añadir una nueva solicitud. Aunque el usuario no pueda añadir/modificar ciertos campos
	 * si incluye todos, ya que inmediatamente después de añadir, coge ese registro y lo muestra en la página del
	 * buscador. Mostrará todos a modo informativo
	 * 
	 * @param id            Identificador único de la solicitud.
	 * @param dni           Documento Nacional de Identidad del solicitante.
	 * @param cod_actividad Código de la actividad a la que se postula la solicitud.
	 * @param nombre        Nombre del solicitante.
	 * @param apellido1     Primer apellido del solicitante.
	 * @param apellido2     Segundo apellido del solicitante.
	 * @param email         Dirección de correo electrónico del solicitante.
	 * @param direccion     Dirección física del solicitante.
	 * @param telefono      Número de teléfono.
	 * @param f_nacimiento  Fecha de nacimiento.
	 * @param num_sorteo    Número asignado para entrar en el sorteode plazas.
	 * @param seleccionado
	 *                      <ul>
	 *                      <li>true: El solicitante ha sido seleccionado para la
	 *                      actividad.</li>
	 *                      <li>false: El solicitante no ha sido seleccionado para
	 *                      la actividad.</li>
	 *                      </ul>
	 * @param pago
	 *                      <ul>
	 *                      <li>true: El solicitante ha realizado el pago.</li>
	 *                      <li>false: El solicitante no ha realizado el pago.</li>
	 *                      </ul>
	 * @param estado        Estado de la solicitud (ejemplo: "Recibida",
	 *                      "Seleccionado", "Reserva", "Anulada").
	 */
	// Otro listar una vez añadida. Sin ID 
	// El user externo añade solicitud. De aquí cogeré el el registro y lo muestro a modo informativo
	// Como indicando "Solicitud recibida" pondré eso en estado.
	public Solicitud(String dni, int cod_actividad, String nombre, String apellido1, String apellido2, String email,
			String direccion, int telefono, String f_nacimiento, int num_sorteo, boolean seleccionado, boolean pago,
			String estado) {
		super();
		this.dni = dni;
		this.cod_actividad = cod_actividad;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.email = email;
		this.direccion = direccion;
		this.telefono = telefono;
		this.f_nacimiento = f_nacimiento;
		this.num_sorteo = num_sorteo;
		this.seleccionado = seleccionado;
		this.pago = pago;
		this.estado = estado;
	}

	// Otro sin id para mandar a la BDD. Campos del formulario para usuario externo
	public Solicitud(String dni, int cod_actividad, String nombre, String apellido1, String apellido2, String email,
			String direccion, int telefono, String f_nacimiento) {
		super();
		this.dni = dni;
		this.cod_actividad = cod_actividad;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.email = email;
		this.direccion = direccion;
		this.telefono = telefono;
		this.f_nacimiento = f_nacimiento;
	}

	/**
	 * Constructor que crea una instancia de Solicitud con los datos proporcionados
	 * por un usuario externo a través de un formulario. El id será añadido en la
	 * base de datos. El resto de campos que falta con referencia a la clase son
	 * añadidos por el administrador de manera interna. No son valores que puedan
	 * manejar los usuarios externos Son valores de administración
	 *
	 * @param dni           Documento Nacional de Identidad del solicitante.
	 * @param cod_actividad Código de la actividad a la que el usuario desea
	 *                      inscribirse.
	 * @param nombre        Nombre del solicitante.
	 * @param apellido1     Primer apellido.
	 * @param apellido2     Segundo apellido.
	 * @param email         Dirección de correo electrónico.
	 * @param direccion     Dirección completa del solicitante.
	 * @param telefono      Número de teléfono.
	 * @param f_nacimiento  Fecha de nacimiento del solicitante, utilizada para
	 *                      verificar la elegibilidad para ciertas actividades.
	 */
	// Otro para el resultado del buscador. Identificativo de solicitud.
	// Formulario usuario. User externo
	public Solicitud(int id, String dni, int cod_actividad, String nombre, String email, int telefono, int num_sorteo,
			boolean pago, boolean seleccionado, String estado) {
		super();
		this.id = id;
		this.dni = dni;
		this.cod_actividad = cod_actividad;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.num_sorteo = num_sorteo;
		this.pago = pago;
		this.seleccionado = seleccionado;
		this.estado = estado;
	}

	/**
	 * Obtiene el identificador de la solicitud
	 * 
	 * @return Identificador único de la solicitud
	 */
	public int getId() {
		return id;
	}

	/**
	 * Establece el identificador de la solicitud
	 * 
	 * @param id Identificador único de la solicitud.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Obtiene el DNI del solicitante
	 * 
	 * @return Documento Nacional de Identidad
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Establece el DNI del solicitante
	 * 
	 * @param dni Documento Nacional de Identidad
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Obtener el código de la actividad elegida en la solicitud
	 * 
	 * @return Código de actividad
	 */
	public int getCod_actividad() {
		return cod_actividad;
	}

	/**
	 * Establecer el código de la actividad elegida
	 * 
	 * @param cod_actividad Código de actividad
	 */
	public void setCod_actividad(int cod_actividad) {
		this.cod_actividad = cod_actividad;
	}

	/**
	 * Obtener el nombre del solicitante
	 * 
	 * @return Nombre del solicitante
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establecer el nombre del solicitante
	 * 
	 * @param nombre Nombre del solicitante
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtener el primer apellido del solicitante
	 * 
	 * @return Primer apellido
	 */
	public String getApellido1() {
		return apellido1;
	}

	/**
	 * Establecer el primer apellido del solicitante
	 * 
	 * @param apellido1 Primer apellido del solicitante
	 */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	/**
	 * Obtener el segundo apellido del solicitante
	 * 
	 * @return Segundo apellido
	 */
	public String getApellido2() {
		return apellido2;
	}

	/**
	 * Establecer el segundo apellido del solicitante
	 * 
	 * @param apellido2 Segundo apellido
	 */
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	/**
	 * Obtener el email de contacto del solicitante
	 * 
	 * @return Email de contacto
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Establecer el email de contacto del solicitante
	 * 
	 * @param email Email de contacto
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Obtener la dirección postal del solicitante
	 * 
	 * @return Dirección postal
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Establecer dirección postal del solicitante
	 * 
	 * @param direccion Dirección postal
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Obtener el télefono de contacto del solicitente
	 * 
	 * @return Teléfono de contacto
	 */
	public int getTelefono() {
		return telefono;
	}

	/**
	 * Establecer el teléfono de contacto del solicitante.
	 * 
	 * @param telefono Teléfono de contacto
	 */
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	/**
	 * Obtener la fecha de nacimiento del solicitante
	 * 
	 * @return Fecha de nacimiento
	 */
	public String getF_nacimiento() {
		return f_nacimiento;
	}

	/**
	 * Establecer la fecha de nacimiento del solicitante.
	 * 
	 * @param f_nacimiento Fecha de nacimiento
	 */
	public void setF_nacimiento(String f_nacimiento) {
		this.f_nacimiento = f_nacimiento;
	}

	/**
	 * Obtiene el número asignado para el sorteo
	 * 
	 * @return Número con el que participa en el sorteo
	 */
	public int getNum_sorteo() {
		return num_sorteo;
	}

	/**
	 * Establece el número para el sorteo
	 * 
	 * @param num_sorteo Número con el que participa en el sorteo
	 */
	public void setNum_sorteo(int num_sorteo) {
		this.num_sorteo = num_sorteo;
	}

	/**
	 * Obtiene el estado de selección del solicitante
	 * 
	 * @return
	 *         <ul>
	 *         <li>true: si el solicitante ha sido seleccionado.</li>
	 *         <li>false: si el solicitante no ha sido seleccionado.</li>
	 *         </ul>
	 */
	public boolean isSeleccionado() {
		return seleccionado;
	}

	/**
	 * Establece el estado de selección del solicitante.
	 * 
	 * @param seleccionado
	 *                     <ul>
	 *                     <li>true: para indicar que el solicitante ha sido
	 *                     seleccionado</li>
	 *                     <li>false: para indicar que el solicitante no ha sido
	 *                     seleccionado</li>
	 *                     </ul>
	 */
	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	/**
	 * Obtiene el estado de pago del solicitante
	 * 
	 * @return
	 *         <ul>
	 *         <li>true: si el solicitante ha pagado</li>
	 *         <li>false: si el solicitante no ha pagado.</li>
	 *         </ul>
	 */
	public boolean isPago() {
		return pago;
	}

	/**
	 * Establece el estado de pago del solicitante.
	 * 
	 * @param seleccionado
	 *                     <ul>
	 *                     <li>true: para indicar que el solicitante ha pagado</li>
	 *                     <li>false: para indicar que el solicitante no ha
	 *                     pagado</li>
	 *                     </ul>
	 */
	public void setPago(boolean pago) {
		this.pago = pago;
	}

	/**
	 * Obtiene el estado de la solicitud. Los diferentes estados serán establecidos
	 * a posteriori en el front
	 * 
	 * @return Estado de la solicitud
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Establece el estado de la solicitud. Los diferentes estados serán
	 * establecidos a posteriori en el front
	 * 
	 * @param estado Estado de la solicitud
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Inserta la instancia para solicitud en la base de datos. Utiliza una
	 * instancia de {@code DaoSolicitud} obtenida mediante su método
	 * {@code getInstance} para realizar la operación.
	 * 
	 * @throws SQLException Si ocurre un error durante el proceso de inserción en la
	 *                      base de datos.
	 */
	public void insertar() throws SQLException {
		DaoSolicitud.getInstance().insertar(this);
	}

	/**
	 * Recupera una solicitud de la base de datos utilizando su código
	 * identificativo y actualiza las propiedades de esta instancia con los valores
	 * obtenidos. Utiliza el patrón Singleton para acceder a {@link DaoSolicitud} y
	 * recupera los detalles de la solicitud especificada por el id proporcionado.
	 *
	 * @param id Identificador único de la solicitud que se desea obtener. Este
	 *           código es usado para localizar la solicitud específica en la base
	 *           de datos.
	 * @throws SQLException Si ocurre un error durante la recuperación de la
	 *                      actividad desde la base de datos.
	 */
	public void obtenerPorId(int id) throws SQLException { // para el admin

		DaoSolicitud dao = DaoSolicitud.getInstance();
		Solicitud aux = dao.obtenerPorId(id);

		// Establecer propiedades de la solicitud actual con los valores de la solicitud
		// seleccionada
		this.setId(aux.getId());
		this.setDni(aux.getDni());
		this.setCod_actividad(aux.getCod_actividad());
		this.setNombre(aux.getNombre());
		this.setApellido1(aux.getApellido1());
		this.setApellido2(aux.getApellido2());
		this.setEmail(aux.getEmail());
		this.setDireccion(aux.getDireccion());
		this.setTelefono(aux.getTelefono());
		this.setF_nacimiento(aux.getF_nacimiento());
		this.setNum_sorteo(aux.getNum_sorteo());
		this.setSeleccionado(aux.isSeleccionado());
		this.setPago(aux.isPago());
		this.setEstado(aux.getEstado());
	}

	/**
	 * Recupera una solicitud de la base de datos utilizando el parámetro DNI y
	 * muestra las propiedades de esta instancia con los valores obtenidos. El DNI
	 * es usado para localizar una solicitud única, ya que se limita a una solicitud
	 * por DNI. Método necesario para realizar búsquedas de solicitudes por DNI.
	 * Buscador accesible para usuario externo. Utiliza el patrón Singleton para
	 * acceder a {@link DaoSolicitud} y recupera los detalles de la solicitud
	 * especificada por el DNI proporcionado.
	 *
	 * @param dni DNI de la solicitud que se desea buscar.
	 * 
	 * @throws SQLException Si ocurre un error durante la recuperación de la
	 *                      actividad desde la base de datos.
	 */
	public void obtenerPorDni(String dni) throws SQLException {// para el usuaario

		DaoSolicitud dao = DaoSolicitud.getInstance();
		Solicitud aux = dao.obtenerPorDni(dni);

		// Establecer propiedades de la solicitud actual con los valores de la solicitud
		// seleccionada
		this.setId(aux.getId());
		this.setDni(aux.getDni());
		this.setCod_actividad(aux.getCod_actividad());
		this.setNombre(aux.getNombre());
		this.setApellido1(aux.getApellido1());
		this.setApellido2(aux.getApellido2());
		this.setEmail(aux.getEmail());
		this.setDireccion(aux.getDireccion());
		this.setTelefono(aux.getTelefono());
		this.setF_nacimiento(aux.getF_nacimiento());
		this.setNum_sorteo(aux.getNum_sorteo());
		this.setSeleccionado(aux.isSeleccionado());
		this.setPago(aux.isPago());
		this.setEstado(aux.getEstado());
	}
/*
	public void buscadorDni(String dni) throws SQLException {// para el usuaario

		DaoSolicitud dao = DaoSolicitud.getInstance();
		Solicitud aux = dao.buscadorDni(dni);

		// Recupero estos datos y los tengo que volver a mandar
		this.setId(aux.getId());
		this.setDni(aux.getDni());
		this.setCod_actividad(aux.getCod_actividad());
		this.setNombre(aux.getNombre());
		this.setEmail(aux.getEmail());
		this.setTelefono(aux.getTelefono());
		this.setNum_sorteo(aux.getNum_sorteo());
		this.setSeleccionado(aux.isSeleccionado());
		this.setPago(aux.isPago());
		this.setEstado(aux.getEstado());
	}*/

	/**
	 * Serializa la instancia actual en una cadena JSON. Utiliza la biblioteca Gson
	 * para la serialización.
	 *
	 * @return Una cadena en formato JSON que representa la instancia actual.
	 */
	public String dameJson() {
		String json = "";

		Gson gson = new Gson();

		json = gson.toJson(this);
		return json;

	}

	/**
	 * Actualiza los parámetros de la solicitud actual en la base de datos. La
	 * operación se realiza a través de la clase {@code DaoSolicitud}, que es
	 * responsable de la lógica de acceso a datos.
	 * 
	 * @throws SQLException Si ocurre un error durante la actualización en la base
	 *                      de datos.
	 */
	public void actualizar() throws SQLException {

		DaoSolicitud.getInstance().actualizar(this);
	}

	/**
	 * Elimina una solicitud especificada por el identiicador de la base de datos.
	 * La clase {@code DaoSolicitud} es responsable de manejar todas las
	 * interacciones con la base de datos relacionadas con las solicitudes.
	 *
	 * @param id Identificador de la solicitud que se desea eliminar.
	 * @throws SQLException Si ocurre un error durante el proceso de eliminación en
	 *                      la base de datos.
	 */
	public void borrar(int id) throws SQLException {
		DaoSolicitud.getInstance().borrar(id);
	}

	/**
	 * Devuelve una representación de la instancia de Solicitud. Esta representación
	 * incluye todos los detalles del objeto Solicitud.
	 *
	 * @return Una cadena que representa todos los datos del objeto Solicitud en el
	 *         formato: "Solicitud [id={id}, dni={dni},
	 *         cod_actividad={cod_actividad}, nombre={nombre},
	 *         apellido1={apellido1}, apellido2={apellido2}, email={email},
	 *         direccion={direccion}, telefono={telefono},
	 *         f_nacimiento={f_nacimiento}, num_sorteo={num_sorteo},
	 *         seleccionado={seleccionado}, pago={pago}, estado={estado}]"
	 */
	@Override
	public String toString() {
		return "Solicitud [id=" + id + ", dni=" + dni + ", cod_actividad=" + cod_actividad + ", nombre=" + nombre
				+ ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", email=" + email + ", direccion="
				+ direccion + ", telefono=" + telefono + ", f_nacimiento=" + f_nacimiento + ", num_sorteo=" + num_sorteo
				+ ", seleccionado=" + seleccionado + ", pago=" + pago + ", estado=" + estado + "]";
	}

}
