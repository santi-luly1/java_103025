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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

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

	public PrendaVista(PrendaController prendaController) {
	    this.prendaController = prendaController;
	    initComponents();
	}
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 500, 550);
	    contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);
	    contentPane.setLayout(null);

	    JLabel lblPrenda_ID = new JLabel("ID");
	    lblPrenda_ID.setBounds(26, 72, 84, 17);
	    contentPane.add(lblPrenda_ID);

	    JLabel lblDescripcion_Prenda = new JLabel("Descripción");
	    lblDescripcion_Prenda.setBounds(26, 99, 84, 17);
	    contentPane.add(lblDescripcion_Prenda);

	    JLabel lblTalle_Prenda = new JLabel("Talle");
	    lblTalle_Prenda.setBounds(26, 128, 60, 17);
	    contentPane.add(lblTalle_Prenda);

	    JLabel lblColor_Prenda = new JLabel("Color");
	    lblColor_Prenda.setBounds(26, 157, 60, 17);
	    contentPane.add(lblColor_Prenda);

	    JLabel lblPrecio_Prenda = new JLabel("Precio");
	    lblPrecio_Prenda.setBounds(26, 186, 60, 17);
	    contentPane.add(lblPrecio_Prenda);

	    JLabel lblStock_Prenda = new JLabel("Stock");
	    lblStock_Prenda.setBounds(26, 215, 60, 17);
	    contentPane.add(lblStock_Prenda);

	    fieldID = new JTextField();
	    fieldID.setBounds(108, 68, 114, 21);
	    contentPane.add(fieldID);

	    fieldDescripcion = new JTextField();
	    fieldDescripcion.setBounds(108, 97, 114, 21);
	    contentPane.add(fieldDescripcion);

	    fieldTalle = new JTextField();
	    fieldTalle.setBounds(108, 126, 114, 21);
	    contentPane.add(fieldTalle);

	    fieldColor = new JTextField();
	    fieldColor.setBounds(108, 155, 114, 21);
	    contentPane.add(fieldColor);

	    fieldPrecio = new JTextField();
	    fieldPrecio.setBounds(108, 184, 114, 21);
	    contentPane.add(fieldPrecio);

	    fieldStock = new JTextField();
	    fieldStock.setBounds(108, 213, 114, 21);
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
					Prenda prenda = new Prenda(-1, descripcion, talle, color, precio, stock);// -1 es el id por defecto, pero al llamar agregarPrenda(), este se ecanrgar'a de modificar -1 a su id correspondiente.
					if(prendaController.agregarPrenda(prenda)) { //verifica si se agreg'o la prenda.
						cargarLista();
					} else {
						JOptionPane.showMessageDialog(null, "No se pudo agregar la nueva prenda.");
					}
				} catch (SQLException | PrendaInvalidaException e1) {
					e1.printStackTrace();
				}
	    	}
	    });
	    btnAgregar.setBounds(334, 79, 105, 27);
	    contentPane.add(btnAgregar);

	    JButton btnActualizar = new JButton("Actualizar");
	    btnActualizar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		try {
	    			if (tablePrenda.getSelectedRow() != -1) {
	    				Prenda p = prendaController.obtenerId((int) tablePrenda.getModel().getValueAt(0, 0));
	    				fieldDescripcion.setText(p.getDescripcion());//FIXME: Probablemente los datos tengan que ser cargados al momento de seleccionar la fila, no cuando el bot'on sea pulsado.
	    				fieldTalle.setText(p.getTalle());
	    				fieldColor.setText(p.getColor());
	    				fieldPrecio.setText(String.valueOf(p.getPrecio()));
	    				fieldStock.setText(String.valueOf(p.getStock()));
	    				
	    				String descripcion = fieldDescripcion.getText();
	    				String talle = fieldTalle.getText();
	    				String color = fieldColor.getText();
	    				double precio = Double.parseDouble(fieldPrecio.getText());
	    				int stock = Integer.parseInt(fieldStock.getText());
	    				
	    				VerificarPrenda.validar(descripcion, talle, color, precio, stock);
	    				
	    				p.setDescripcion(descripcion);
	    				p.setTalle(talle);
	    				p.setColor(color);
	    				p.setPrecio(precio);
	    				p.setStock(stock);
	    				
	    				if (prendaController.modificarPrenda(p)) {
	    					cargarLista();
	    				} else {
	    					JOptionPane.showMessageDialog(null, "No se pudo modificar la prenda.");
	    				}
	    			} else {
	    				JOptionPane.showMessageDialog(null, "Debe de seleccionar una persona.");
	    			}
	    		} catch (SQLException | PrendaInvalidaException e1) {
	    			System.out.println(e1.getMessage());
	    		}
	    	}
	    });
	    btnActualizar.setBounds(334, 123, 105, 27);
	    contentPane.add(btnActualizar);

	    JButton btnEliminar = new JButton("Eliminar");
	    btnEliminar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		int filaSeleccionada = tablePrenda.getSelectedRow();
	    		if (filaSeleccionada != -1) {
	    			try {
						if(prendaController.eliminarPrenda((int) tablePrenda.getModel().getValueAt(filaSeleccionada, 0))) {
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
	    			JOptionPane.showMessageDialog(null, "Debe seleccionar una fila.");
	    		}
	    	}
	    });
	    btnEliminar.setBounds(334, 166, 105, 27);
	    contentPane.add(btnEliminar);

	    JButton btnListar = new JButton("Listar");
	    btnListar.setBounds(334, 205, 105, 27);
	    contentPane.add(btnListar);

	    JLabel lblModaUrbana_TituloVentana = new JLabel("MODA URBANA");
	    lblModaUrbana_TituloVentana.setFont(new Font("URW Gothic", Font.BOLD, 20));
	    lblModaUrbana_TituloVentana.setBounds(195, 28, 204, 46);
	    contentPane.add(lblModaUrbana_TituloVentana);

	    String[] columnas = {"ID", "Descripción", "Talle", "Color", "Precio", "Stock"};
	    Object[][] datos = {}; 
	    modelo = new DefaultTableModel(datos, columnas);

	    tablePrenda = new JTable(modelo);

	    JScrollPane scrollPane = new JScrollPane(tablePrenda);
	    scrollPane.setBounds(26, 262, 453, 230);
	    contentPane.add(scrollPane);
	    
	    btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cargarLista();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
		for (Prenda prenda : prendaController.getPrendas()) {
			modelo.addRow(new Object[] { prenda.getId(), prenda.getDescripcion(), prenda.getTalle(), prenda.getColor(), prenda.getPrecio(), prenda.getStock()});
		}
	}
}