package vista;
import modelo.Prenda;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Exe.GestorPersona;
import Exe.PersonaInvalidaException;
import Exe.VerificarPersona;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UI extends JFrame {
	private static final long serialVersionUID = 1L;

	private GestorPersona gestor;
	private DefaultTableModel modelo;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldEdad;
	private JTable table;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox<String> comboBoxPais;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	private JCheckBox chckbxEstudiante;

	public UI(GestorPersona gestor) throws SQLException {
		this.gestor = gestor;
		initComponents();
	}

	private void borrar() {
		textFieldNombre.setText("");
		textFieldApellido.setText("");
		textFieldEdad.setText("");
		chckbxEstudiante.setSelected(false);
		comboBoxPais.setSelectedIndex(-1);
		buttonGroup.clearSelection();
	}

	private void cargarTabla() throws SQLException {
		modelo.setRowCount(0);
		for (Persona persona : gestor.getPersonas()) {
			modelo.addRow(new Object[] { persona.getNombre(), persona.getApellido(), persona.getEdad(),
					persona.getEstudiante() ? "Sí" : "No", persona.getSexo(), persona.getPais() });
		}
	}

	private void initComponents() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(42, 63, 60, 17);
		contentPane.add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(42, 92, 60, 17);
		contentPane.add(lblApellido);

		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setBounds(42, 121, 60, 17);
		contentPane.add(lblEdad);

		JLabel lblEstudiante = new JLabel("Estudiante");
		lblEstudiante.setBounds(42, 151, 83, 17);
		contentPane.add(lblEstudiante);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(106, 61, 114, 21);
		contentPane.add(textFieldNombre);

		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(106, 92, 114, 21);
		contentPane.add(textFieldApellido);

		textFieldEdad = new JTextField();
		textFieldEdad.setBounds(106, 119, 37, 21);
		contentPane.add(textFieldEdad);

		chckbxEstudiante = new JCheckBox("");
		chckbxEstudiante.setBounds(116, 147, 37, 25);
		contentPane.add(chckbxEstudiante);

		JLabel lblPais = new JLabel("País");
		lblPais.setBounds(167, 151, 60, 17);
		contentPane.add(lblPais);

		comboBoxPais = new JComboBox<>(new DefaultComboBoxModel<>(
				new String[] { "Argentina", "Brasil", "Chile", "Colombia", "México", "Perú", "Uruguay", "Venezuela" }));
		comboBoxPais.setBounds(200, 146, 110, 26);
		contentPane.add(comboBoxPais);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = textFieldNombre.getText();
				String apellido = textFieldApellido.getText();
				int edad;
				try {
					String txtEdad = textFieldEdad.getText();
					edad = Integer.parseInt((txtEdad != null && !txtEdad.isEmpty()) ? txtEdad : "0");
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "La edad es inválida");
					textFieldEdad.requestFocus();
					return;
				}

				boolean estudiante = chckbxEstudiante.isSelected();
				String sexo = rdbtnMale.isSelected() ? "Masculino" : (rdbtnFemale.isSelected() ? "Femenino" : null);
				String pais = (String) comboBoxPais.getSelectedItem();

				try {
					VerificarPersona.validar(nombre, apellido, edad, sexo, pais);
					Persona persona = new Persona(gestor.getPersonas().size() + 1, nombre, apellido, edad, estudiante, sexo, pais, comboBoxPais.getSelectedIndex());
					gestor.agregarPersona(persona);
					modelo.addRow(new Object[] { nombre, apellido, edad, estudiante ? "Sí" : "No", sexo, pais });
					borrar();
				} catch (PersonaInvalidaException | SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnAgregar.setBounds(42, 208, 106, 27);
		contentPane.add(btnAgregar);

		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrar();
			}
		});
		btnBorrar.setBounds(204, 208, 106, 27);
		contentPane.add(btnBorrar);

		JScrollPane scrollPane = new JScrollPane();
		modelo = new DefaultTableModel();
		modelo.addColumn("Nombre");
		modelo.addColumn("Apellido");
		modelo.addColumn("Edad");
		modelo.addColumn("Estudiante");
		modelo.addColumn("Sexo");
		modelo.addColumn("País");

		cargarTabla();

		scrollPane.setBounds(42, 257, 430, 201);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(modelo);
		scrollPane.setViewportView(table);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					eliminarSeleccionado();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(42, 519, 106, 27);
		contentPane.add(btnEliminar);

		JButton btnEliminarTodo = new JButton("Eliminar Todo");
		btnEliminarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					gestor.eliminarTodo();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				modelo.setRowCount(0);
			}
		});
		btnEliminarTodo.setBounds(184, 519, 134, 27);
		contentPane.add(btnEliminarTodo);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(366, 208, 106, 27);
		contentPane.add(btnSalir);

		JLabel lblGenero = new JLabel("Género");
		lblGenero.setBounds(220, 121, 60, 17);
		contentPane.add(lblGenero);

		rdbtnMale = new JRadioButton("M");
		buttonGroup.add(rdbtnMale);
		rdbtnMale.setBounds(275, 117, 47, 25);
		contentPane.add(rdbtnMale);

		rdbtnFemale = new JRadioButton("F");
		buttonGroup.add(rdbtnFemale);
		rdbtnFemale.setBounds(326, 117, 47, 25);
		contentPane.add(rdbtnFemale);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					openUI2();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnModificar.setBounds(338, 519, 134, 27);
		contentPane.add(btnModificar);

		borrar();
	}

	private void eliminarSeleccionado() throws SQLException {
		int filaSeleccionada = table.getSelectedRow();
		if (filaSeleccionada > -1) {
			gestor.eliminarPersona(gestor.obtenerIndex(filaSeleccionada + 1));
			modelo.removeRow(filaSeleccionada);
		} else {
			JOptionPane.showMessageDialog(this, "Debe seleccionar una fila.");
		}
	}

	private void openUI2() throws SQLException {
		int filaSeleccionada = table.getSelectedRow();

		if (filaSeleccionada != -1) {
			new UI2(gestor, filaSeleccionada + 1).setVisible(true);
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Debe de seleccionar una persona.");
		}
	}
}


