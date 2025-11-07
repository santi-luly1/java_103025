package vista;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import controlador.PrendaController;
import main.PrendaInvalidaException;
import main.VerificarPrenda;
import modelo.Prenda;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JSeparator;

public class PrendaVista extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fieldID;
	private JTextField fieldDescripcion;
	private JTextField fieldTalle;
	private JTextField fieldColor;
	private JTextField fieldPrecio;
	private JTextField fieldStock;
	private JTable tablePrenda;
	private PrendaController prendaController;
	private DefaultTableModel modelo;
	private JTextField fieldBuscarId;
	private JLabel lblActualmenteModificando;

	public PrendaVista(PrendaController prendaController) {
		this.prendaController = prendaController;
		initComponents();
	}

	public PrendaVista() {
		setTitle("MODA URBANA");
		setResizable(false);
		this.prendaController = new PrendaController();
		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 690);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 191, 233));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPrenda_ID = new JLabel("ID");
		lblPrenda_ID.setBounds(26, 248, 84, 17);
		contentPane.add(lblPrenda_ID);

		JLabel lblDescripcion_Prenda = new JLabel("Descripción");
		lblDescripcion_Prenda.setBounds(26, 275, 84, 17);
		contentPane.add(lblDescripcion_Prenda);

		JLabel lblTalle_Prenda = new JLabel("Talle");
		lblTalle_Prenda.setBounds(26, 304, 60, 17);
		contentPane.add(lblTalle_Prenda);

		JLabel lblColor_Prenda = new JLabel("Color");
		lblColor_Prenda.setBounds(26, 333, 60, 17);
		contentPane.add(lblColor_Prenda);

		JLabel lblPrecio_Prenda = new JLabel("Precio");
		lblPrecio_Prenda.setBounds(26, 362, 60, 17);
		contentPane.add(lblPrecio_Prenda);

		JLabel lblStock_Prenda = new JLabel("Stock");
		lblStock_Prenda.setBounds(26, 391, 60, 17);
		contentPane.add(lblStock_Prenda);

		fieldID = new JTextField();
		fieldID.setText("0");
		fieldID.setEditable(false);
		fieldID.setBounds(108, 244, 114, 21);
		contentPane.add(fieldID);

		fieldDescripcion = new JTextField();
		fieldDescripcion.setToolTipText("");
		fieldDescripcion.setBounds(108, 273, 114, 21);
		contentPane.add(fieldDescripcion);

		fieldTalle = new JTextField();
		fieldTalle.setBounds(108, 302, 114, 21);
		contentPane.add(fieldTalle);

		fieldColor = new JTextField();
		fieldColor.setBounds(108, 331, 114, 21);
		contentPane.add(fieldColor);

		fieldPrecio = new JTextField();
		fieldPrecio.setBounds(108, 360, 114, 21);
		contentPane.add(fieldPrecio);

		fieldStock = new JTextField();
		fieldStock.setBounds(108, 389, 114, 21);
		contentPane.add(fieldStock);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String descripcion = fieldDescripcion.getText();
				String talle = fieldTalle.getText();
				String color = fieldColor.getText();
				double precio = Double.parseDouble(fieldPrecio.getText());
				int stock = Integer.parseInt(fieldStock.getText());

				try {
					VerificarPrenda.validar(descripcion, talle, color, precio, stock);
					Prenda prenda = new Prenda(-1, descripcion, talle, color, precio, stock);// -1 es el id por defecto,
																								// pero al llamar
																								// agregarPrenda(), este
																								// se ecanrgar'a de
																								// modificar -1 a su id
																								// correspondiente.
					if (prendaController.agregarPrenda(prenda)) { // verifica si se agreg'o la prenda.
						limpiarCampos();
						cargarLista();
					} else {
						JOptionPane.showMessageDialog(null, "No se pudo agregar la nueva prenda.");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (PrendaInvalidaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		btnAgregar.setBounds(335, 243, 105, 27);
		contentPane.add(btnAgregar);

		JButton btnActualizar = new JButton("Modificar");
		btnActualizar.setEnabled(false);
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = tablePrenda.getSelectedRow();
				try {
					if (filaSeleccionada != -1) {
						String descripcion = fieldDescripcion.getText();
						String talle = fieldTalle.getText();
						String color = fieldColor.getText();
						double precio = Double.parseDouble(fieldPrecio.getText());
						int stock = Integer.parseInt(fieldStock.getText());

						VerificarPrenda.validar(descripcion, talle, color, precio, stock);

						Prenda p = prendaController.obtenerId((int) tablePrenda.getModel().getValueAt(filaSeleccionada, 0)); // se obtine el id desde la celda de la fila seleccionada
						p.setDescripcion(descripcion);
						p.setTalle(talle);
						p.setColor(color);
						p.setPrecio(precio);
						p.setStock(stock);

						if (prendaController.modificarPrenda(p)) { // si se modifica exitosamente
							limpiarCampos();
							cargarLista();
						} else {
							JOptionPane.showMessageDialog(null, "No se pudo modificar la prenda.");
						}
					} else {
						lblActualmenteModificando.setText("");
						JOptionPane.showMessageDialog(null, "Debe de seleccionar una prenda."); // el bot'on normalmente est'a desactivado en estos casos, pero por si alg'un motivo eso falla, esto es un respaldo.
					}
				} catch (SQLException e1) {
					System.out.println(e1.getMessage());
				} catch (PrendaInvalidaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		btnActualizar.setBounds(335, 275, 105, 27);
		contentPane.add(btnActualizar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = tablePrenda.getSelectedRow();
				if (filaSeleccionada != -1) {
					try {
						if (prendaController
								.eliminarPrenda((int) tablePrenda.getModel().getValueAt(filaSeleccionada, 0))) {
							modelo.removeRow(filaSeleccionada);
//							cargarLista(); //Menos carga para MariaDB (?) ya que de todas maneras estamos eliminando la fila, en todo caso, descomentar esta l'inea.
						} else {
							JOptionPane.showMessageDialog(null, "No se pudo eliminar la prenda.");
						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar una fila."); // en caso que el usuario pueda pulsar el bot'on (no deber'ia), que esto no genere un error sino que decirle justamente que seleccione algo
				}
			}
		});
		btnEliminar.setBounds(335, 304, 105, 27);
		contentPane.add(btnEliminar);

		JButton btnBorrarCampos = new JButton("Limpiar");
		btnBorrarCampos.setBounds(335, 333, 105, 27);
		contentPane.add(btnBorrarCampos);

		JLabel lblModaUrbana_TituloVentana = new JLabel("MODA URBANA");
		lblModaUrbana_TituloVentana.setHorizontalAlignment(SwingConstants.CENTER);
		lblModaUrbana_TituloVentana.setFont(new Font("URW Gothic", Font.BOLD, 20));
		lblModaUrbana_TituloVentana.setBounds(144, 26, 204, 46);
		contentPane.add(lblModaUrbana_TituloVentana);

		String[] columnas = { "ID", "Descripción", "Talle", "Color", "Precio", "Stock" };
		Object[][] datos = {};
		modelo = new DefaultTableModel(datos, columnas);

		tablePrenda = new JTable(modelo);

		JScrollPane scrollPane = new JScrollPane(tablePrenda);
		scrollPane.setBounds(26, 435, 453, 230);
		contentPane.add(scrollPane);

		fieldBuscarId = new JTextField();
		fieldBuscarId.setBounds(186, 71, 127, 36);
		contentPane.add(fieldBuscarId);

		JButton btnBuscarId = new JButton("Buscar ID");
		btnBuscarId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(fieldBuscarId.getText());

					if (id > 0) {
						Prenda prenda = prendaController.obtenerId(id);

						if (prenda == null) {
							throw new NullPointerException(); // si el id no existe, tire error, y el error se encarga de mostrarlo al usuario.
						}

						fieldID.setText(String.valueOf(id));
						fieldDescripcion.setText(prenda.getDescripcion());
						fieldTalle.setText(prenda.getTalle());
						fieldColor.setText(prenda.getColor());
						fieldPrecio.setText(String.valueOf(prenda.getPrecio()));
						fieldStock.setText(String.valueOf(prenda.getStock()));

						for (int i = 0; i < tablePrenda.getRowCount(); i++) {
							if ((int) tablePrenda.getValueAt(i, 0) == id) { // si el id de la fila es el id que buscamos, entonces
								limpiarCampos(); // al encontrarlo limpiamos los campos, y los datos se cargar'an.
								tablePrenda.setRowSelectionInterval(i, i);
								break; // salimos del loop, ya que ya encontramos la fila del id.
							}
						}

						btnActualizar.setEnabled(true);
					} else {
						JOptionPane.showMessageDialog(null, "ID inexistente");
					}
				} catch (NumberFormatException e1) { //
					JOptionPane.showMessageDialog(null, "El ID ingresado no es válido");
				} catch (SQLException e1) {
					// TODO: catch para el error sql blah blah.
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(null, "ID inexistente.");
				}
			}
		});
		btnBuscarId.setBounds(196, 108, 105, 27);
		contentPane.add(btnBuscarId);
		
		lblActualmenteModificando = new JLabel("");
		lblActualmenteModificando.setHorizontalAlignment(SwingConstants.CENTER);
		lblActualmenteModificando.setFont(new Font("Dialog", Font.BOLD, 16));
		lblActualmenteModificando.setBounds(144, 173, 204, 46);
		contentPane.add(lblActualmenteModificando);
		
		JButton btnDeseleccionarId = new JButton("Dejar de editar");
		btnDeseleccionarId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablePrenda.clearSelection();
			}
		});
		btnDeseleccionarId.setBounds(186, 134, 127, 27);
		contentPane.add(btnDeseleccionarId);

		btnBorrarCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
			}
		});

		tablePrenda.getSelectionModel().addListSelectionListener(new ListSelectionListener() { // es como el ActionListener en los botones (tiene la aprovaci'on de V'ictor)
			@Override
			public void valueChanged(ListSelectionEvent e) { // evento que se dispara cada que un valor cambia (se cambi'o la fila seleccionada)
				if (!e.getValueIsAdjusting()) { // evita que se dispare dos veces
					int filaSeleccionada = tablePrenda.getSelectedRow();
					
					if (filaSeleccionada != -1) { // si la fila est'a seleccionada
						try {
							int id = (int) tablePrenda.getValueAt(filaSeleccionada, 0);
							Prenda prenda = prendaController.obtenerId(id);

							lblActualmenteModificando.setText("ID seleccionado: " + String.valueOf(id));
							fieldID.setText(String.valueOf(id));
							fieldDescripcion.setText(prenda.getDescripcion());
							fieldTalle.setText(prenda.getTalle());
							fieldColor.setText(prenda.getColor());
							fieldPrecio.setText(String.valueOf(prenda.getPrecio()));
							fieldStock.setText(String.valueOf(prenda.getStock()));

							btnAgregar.setEnabled(false);
							btnActualizar.setEnabled(true);
							btnEliminar.setEnabled(true);
						} catch (SQLException e1) {
							// TODO: catch para el error sql blah blah.
						}
					} else { // la fila deja de estar seleccionada (deja de editar)
						lblActualmenteModificando.setText("");
						btnActualizar.setEnabled(false); // en caso de que por alg'un motivo, el nuevo cambio haya sido que no est'e seleccionando ninguna fila, deshabilita el bot'on modificar.
						btnAgregar.setEnabled(true);
						btnEliminar.setEnabled(false);
					}
				}
			}
		});

		try {
			cargarLista();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void cargarLista() throws SQLException {
		modelo.setRowCount(0);
		for (Prenda prenda : prendaController.getPrendas()) { // recorre la lista de personas y hace una nueva fila para la lista con sus datos correspondientes
			modelo.addRow(new Object[] { prenda.getId(), prenda.getDescripcion(), prenda.getTalle(), prenda.getColor(),
					prenda.getPrecio(), prenda.getStock() });
		}
	}
	
	private void limpiarCampos() {
		fieldBuscarId.setText("");
		fieldID.setText("");
		fieldDescripcion.setText("");
		fieldTalle.setText("");
		fieldColor.setText("");
		fieldPrecio.setText("");
		fieldStock.setText("");
	}
}