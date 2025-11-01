package main;
import java.sql.SQLException;

import Model.PersonaDB;
import UI.UI;

public class Main {
	public static void main(String[] args) throws SQLException {
		new UI(new GestorPersona(new PersonaDB())).setVisible(true); // test 4
	}
}