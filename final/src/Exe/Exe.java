package Exe;
import java.sql.SQLException;

import Model.PersonaDB;
import UI.UI;

public class Exe {
	public static void main(String[] args) throws SQLException {
		new UI(new GestorPersona(new PersonaDB())).setVisible(true); // test
	}
}