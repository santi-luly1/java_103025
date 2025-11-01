package modelo;

public class Prenda { //FIXME: Reemplazar cada atributo de esta clase para que corresponda a una prenda.
	private String nombre, apellido, sexo, pais; // "Masculino" | "Femenino"
	private int edad, paisIndex, id; // Para mantener el 'indice del combo
	private boolean estudiante;

	public Prenda(int id, String nombre, String apellido, int edad, boolean estudiante, String sexo, String pais, int paisIndex) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.estudiante = estudiante;
		this.sexo = sexo;
		this.pais = pais;
		this.paisIndex = paisIndex;
	}

	public int getId() 			{ return id; }
	public int getIndex()		{ return id - 1; } // sql comienza por 1, Java por 0.
	public void setId(int id)	{ this.id = id; }
	
	public String getNombre()				{ return nombre; }
	public void setNombre(String nombre)	{ this.nombre = nombre; }
	
	public String getApellido()					{ return apellido; }
	public void setApellido(String apellido)	{ this.apellido = apellido; }
	
	public int getEdad()			{ return edad; }
	public void setEdad(int edad)	{ this.edad = edad; }
	
	public boolean getEstudiante()					{ return estudiante; }
	public void setEstudiante(boolean estudiante)	{ this.estudiante = estudiante; }
	
	public String getSexo() 			{ return sexo; }
	public void setSexo(String sexo)	{ this.sexo = sexo; }
	
	public String getPais() 				{ return pais; }
	public void setPais(String pais)		{ this.pais = pais; }
	public int getPaisIndex() 				{ return paisIndex; }
	public void setPaisIndex(int paisIndex) { this.paisIndex = paisIndex; }
}