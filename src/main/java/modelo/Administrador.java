package modelo;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import com.google.gson.Gson;

import dao.DaoAdministrador;

public class Administrador {
	private int id;
	private String usuario;
	private String pass;

	public Administrador() {

	}

	public Administrador(int id, String usuario, String pass) {
		this.id = id;
		this.usuario = usuario;
		this.pass = pass;
	}

	// otro sin ID
	public Administrador(String usuario, String pass) {
		super();
		this.usuario = usuario;
		this.pass = pass;
	}

	// para consulta
	public Administrador(int id, String usuario) {
		super();
		this.id = id;
		this.usuario = usuario;
	}

	// Getters y setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void insertar() throws SQLException {

		DaoAdministrador.getInstance().insertar(this);
	}

	public void obtenerPorId(int id) throws SQLException {

		DaoAdministrador dao = DaoAdministrador.getInstance();
		Administrador aux = dao.obtenerPorId(id);

		// Establecer propiedades de la actividad actual con los valores del actividad
		// seleccionada
		this.setId(aux.getId());
		this.setUsuario(aux.getUsuario());
		this.setPass(aux.getPass());

	}


	public String dameJson() {
		String json = "";

		Gson gson = new Gson();

		json = gson.toJson(this);
		return json;

	}

	public void actualizar() throws SQLException {
		DaoAdministrador.getInstance().actualizar(this);
	}

	public void borrar(int id) throws SQLException {
		DaoAdministrador.getInstance().borrar(id);
	}
	
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
	

	@Override
	public String toString() {
		return "Admin [id=" + id + ", usuario=" + usuario + ", pass=" + pass + "]";
	}

}
