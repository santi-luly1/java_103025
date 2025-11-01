package controlador;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.PersonaDB;
import Model.Persona;

public class PrendaController {
	private PersonaDB pDB;

	public GestorPersona(PersonaDB pDB) throws SQLException {
		this.pDB = pDB;
	}
	
	public ArrayList<Persona> getPersonas() throws SQLException {
		return pDB.obtenerTodos();
	}

	public void agregarPersona(Persona persona) throws SQLException {
		pDB.insertarPersona(persona);
	}

	public void modificarPersona(Persona persona) throws SQLException {
		pDB.modificarPersona(persona);
	}
	
	public void eliminarPersona(Persona p) throws SQLException {
		pDB.eliminarPersona(p);
	}

	public void eliminarTodo() throws SQLException {
		pDB.eliminarTodo();
	}

	public Persona obtenerIndex(int index) throws SQLException {
		return pDB.obtenerPersona(index);
	}
}